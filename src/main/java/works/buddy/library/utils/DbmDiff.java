package works.buddy.library.utils;

import liquibase.command.CommandScope;
import liquibase.command.core.InternalDiffChangelogCommandStep;
import liquibase.database.Database;
import liquibase.diff.compare.CompareControl;
import liquibase.diff.output.DiffOutputControl;
import liquibase.diff.output.StandardObjectChangeFilter;
import liquibase.exception.DatabaseException;
import liquibase.integration.commandline.CommandLineUtils;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.resource.FileSystemResourceAccessor;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.stream.Collectors;

@Command(name = "java -jar dbmDiff.jar", version = "Buddy DbmDiff 1.0", mixinStandardHelpOptions = true)
public class DbmDiff implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(DbmDiff.class);

    @Option(names = "--url", description = "The parameter specifies the JDBC database connection URL.")
    private String url;

    @Option(names = "--username", description = "The parameter specifies the username to use for connection to the target database.")
    private String username;

    @Option(names = "--password", description = "The parameter specifies the password to use for connection to the target database.")
    private String password;

    @Option(names = "--driver", description = "The parameter specifies the database driver class.")
    private String driver;

    @Option(names = "--reference-url", defaultValue = "jdbc:mariadb://localhost:3303/library", description = "The parameter specifies the JDBC reference " +
            "database connection URL.")
    private String referenceUrl;

    @Option(names = "--reference-username", defaultValue = "library", description = "The parameter specifies the username to use for connection to the " +
            "reference database.")
    private String referenceUsername;

    @Option(names = "--reference-password", defaultValue = "library", description = "The parameter specifies the password to use for connection to the " +
            "reference database.")
    private String referencePassword;

    @Option(names = {"-f", "--force-recreate-database"})
    private boolean forceRecreateDatabase;

    @Option(names = {"-u", "--update"}, description = "Deploy any changes in the changelog file that have not been deployed.")
    private boolean updateDatabase;

    @Option(names = {"-s", "--skip-recreate-reference-database"}, description = "Do not recreate reference database.")
    private boolean skipRecreateReferenceDatabase;

    @Option(names = {"-n", "--do-not-export"}, description = "Do not export hibernate model to reference database.")
    private boolean doNotExport;

    @Option(names = {"-a", "--append"}, description = "Append OUTPUT_FILE instead overwrite.")
    private boolean append;

    @Option(names = {"-r", "--require-no-changes"}, description = "If any changes detected, return non-zero code")
    private boolean requireNoChanges;

    @Parameters(paramLabel = "<OUTPUT_FILE>", arity = "0..1")
    private String outputFile;

    private final String dialect;

    private final String contexts;

    public DbmDiff(Properties properties) {
        this.url = properties.getProperty("hibernate_connection_url");
        this.username = properties.getProperty("hibernate.connection.username");
        this.password = properties.getProperty("hibernate.connection.password");
        this.driver = properties.getProperty("hibernate.connection.driver_class");
        this.dialect = properties.getProperty("hibernate.dialect");
        this.contexts = properties.getProperty("dbm.contexts");
    }

    @Override
    public void run() {
        var db = new ConnectionInfo(url, username, password, driver);
        var refDb = new ConnectionInfo(referenceUrl, referenceUsername, referencePassword, driver);
        LOG.info("db = {}", db);
        LOG.info("refDb = {}", refDb);
        LOG.info("forceRecreateDatabase = {}", forceRecreateDatabase);
        LOG.info("updateDatabase = {}", updateDatabase);
        LOG.info("skipRecreateReferenceDatabase = {}", skipRecreateReferenceDatabase);
        LOG.info("doNotExport = {}", doNotExport);
        LOG.info("append = {}", append);
        LOG.info("requireNoChanges = {}", requireNoChanges);
        LOG.info("outputFile = {}", outputFile);

        var out = new ByteArrayOutputStream();
        var outputFileArg = requireNoChanges ? null : outputFile;
        try {
            if (forceRecreateDatabase) {
                recreate(db);
            }
            if (!skipRecreateReferenceDatabase) {
                recreate(refDb);
            }
            if (updateDatabase) {
                update(db);
            }
            if (!doNotExport) {
                hbm2ddl(refDb);
            }
            if (!append && outputFile != null) {
                FileUtils.deleteQuietly(new File(outputFile));
            }
            doDiffChangelog(database(refDb), database(db), outputFileArg, out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (outputFileArg == null) {
            System.out.println("\n================================== OUTPUT ==================================");
            System.out.println(out);
        }
        if (requireNoChanges) {
            var count = new ChangeSetCountParser(out).parse();
            if (count > 0) {
                try (var fileStream = new PrintStream(new FileOutputStream("dbmDiff.xml", false))) {
                    fileStream.print(out);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                throw new RuntimeException(String.format("Found %d changes, but no changes required", count));
            }
        }
    }

    private void doDiffChangelog(Database referenceDatabase, Database targetDatabase, String outputFileArg, OutputStream out) throws Exception {
        CompareControl.ComputedSchemas computedSchemas = CompareControl.computeSchemas(null, null, null, null, null, null, null, targetDatabase);
        CompareControl.SchemaComparison[] finalSchemaComparisons = computedSchemas.finalSchemaComparisons;
        DiffOutputControl diffOutputControl = new DiffOutputControl(false, false, false, finalSchemaComparisons);

        var excludeObjects = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/dbm/excludeObjects.txt"), StandardCharsets.UTF_8)).lines().map(String::trim).filter(
                rule -> !(rule.isEmpty() || rule.startsWith("#"))).collect(Collectors.joining(",")).trim();
        if (!excludeObjects.isEmpty()) {
            diffOutputControl.setObjectChangeFilter(new StandardObjectChangeFilter(StandardObjectChangeFilter.FilterType.EXCLUDE, excludeObjects));
        }
        CommandScope command = new CommandScope("internalDiffChangeLog");
        command.addArgumentValue(InternalDiffChangelogCommandStep.REFERENCE_DATABASE_ARG, referenceDatabase).addArgumentValue(
                        InternalDiffChangelogCommandStep.TARGET_DATABASE_ARG, targetDatabase).addArgumentValue(InternalDiffChangelogCommandStep.SNAPSHOT_TYPES_ARG,
                        new Class[0]).addArgumentValue(InternalDiffChangelogCommandStep.COMPARE_CONTROL_ARG, new CompareControl(finalSchemaComparisons,
                        (String) null))
                .addArgumentValue(InternalDiffChangelogCommandStep.OBJECT_CHANGE_FILTER_ARG, null).addArgumentValue(
                        InternalDiffChangelogCommandStep.CHANGELOG_FILE_ARG, outputFileArg).addArgumentValue(InternalDiffChangelogCommandStep.DIFF_OUTPUT_CONTROL_ARG,
                        diffOutputControl);
        command.setOutput(out);
        command.execute();
    }

    private void recreate(ConnectionInfo data) throws Exception {
        LOG.info("recreate database '{}'", data.database);
        try (var conn = DriverManager.getConnection(data.serverUrl, data.username, data.password); var stmt = conn.createStatement()) {
            stmt.executeUpdate(String.format("drop database if exists %s", data.database));
            stmt.executeUpdate(String.format("create database %s default character set utf8", data.database));
        }
    }

    private void update(ConnectionInfo data) throws Exception {
        var dataSource = dataSource(data);
        var liquibase = new SpringLiquibase();
        liquibase.setChangeLog(Migration.Def.CHANGELOG_FILE);
        liquibase.setDataSource(dataSource);
        liquibase.setDatabaseChangeLogTable(Migration.Def.CHANGELOG_TABLE);
        liquibase.setDatabaseChangeLogLockTable(Migration.Def.LOCK_TABLE);
        liquibase.setContexts(contexts);
        liquibase.setResourceLoader(new FileSystemResourceLoader());
        liquibase.afterPropertiesSet();
        LOG.info("database updated with not yet run changes");
    }

    private void hbm2ddl(ConnectionInfo data) throws Exception {
        var hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", dialect);
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");

        var dataSource = dataSource(data);
        var sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("works.buddy.library.model");
        sessionFactoryBean.setAnnotatedPackages("works.buddy.common.hibernate.types");
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        sessionFactoryBean.afterPropertiesSet();
        try (var sessionFactory = sessionFactoryBean.getObject()) {
            LOG.info("exported hibernate model");
        } finally {
            dataSource.destroy();
        }
    }

    private SingleConnectionDataSource dataSource(ConnectionInfo data) {
        var dataSource = new SingleConnectionDataSource(data.url, true);
        dataSource.setDriverClassName(data.driver);
        dataSource.setUsername(data.username);
        dataSource.setPassword(data.password);
        return dataSource;
    }

    private Database database(ConnectionInfo data) throws DatabaseException {
        return CommandLineUtils.createDatabaseObject(new FileSystemResourceAccessor(), data.url, data.username, data.password, data.driver, null, null, true,
                true, null, null, null, null, null, Migration.Def.CHANGELOG_TABLE, Migration.Def.LOCK_TABLE);
    }

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new DbmDiff(loadProps())).execute(args);
        System.exit(exitCode);
    }

    private static Properties loadProps() throws IOException {
        Properties properties = new Properties();
        properties.load(new ClassPathResource("conf/hibernate.properties").getInputStream());
        properties.load(new ClassPathResource("conf/run.properties").getInputStream());
        return properties;
    }

    static final class ConnectionInfo {

        private final String url;

        private final String username;

        private final String password;

        private final String driver;

        private final String database;

        private final String serverUrl;

        public ConnectionInfo(String url, String username, String password, String driver) {
            this.url = url;
            this.username = username;
            this.password = password;
            this.driver = driver;
            var databaseIdx = url.lastIndexOf('/') + 1;
            var paramsIdx = url.indexOf('?');
            this.database = paramsIdx != -1 ? url.substring(databaseIdx, paramsIdx) : url.substring(databaseIdx);
            this.serverUrl = url.substring(0, databaseIdx);
        }

        @Override
        public String toString() {
            return "ConnectionInfo{" + "url='" + url + '\'' + ", username='" + username + '\'' + ", password='" + password + '\'' + ", driver='" + driver +
                    '\'' + ", database='" + database + '\'' + ", serverUrl='" + serverUrl + '\'' + '}';
        }
    }

    static final class ChangeSetCountParser {

        private final ByteArrayOutputStream result;

        public ChangeSetCountParser(ByteArrayOutputStream result) {
            this.result = result;
        }

        int parse() {
            var dbf = DocumentBuilderFactory.newInstance();
            var is = new ByteArrayInputStream(result.toByteArray());
            try {
                var db = dbf.newDocumentBuilder();
                var doc = db.parse(is);
                var list = doc.getElementsByTagName("changeSet");
                return list.getLength();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
