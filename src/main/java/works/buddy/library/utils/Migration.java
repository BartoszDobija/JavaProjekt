package works.buddy.library.utils;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import works.buddy.library.config.DataSourceConfig;

import javax.sql.DataSource;

public class Migration {

    public static final class Def {

        public static final String CHANGELOG_TABLE = "dbm_changelog";

        public static final String LOCK_TABLE = "dbm_lock";

        public static final String CHANGELOG_FILE = "dbm/changelog.xml";

    }

    private static final Logger LOG = LoggerFactory.getLogger(Migration.class);

    public static void main(String[] args) {
        LOG.info("start");
        AnnotationConfigApplicationContext ctx = null;
        try {
            ctx = new AnnotationConfigApplicationContext(DbmConfig.class);
        } finally {
            if (ctx != null) {
                ctx.close();
            }
        }
        LOG.info("finish");
    }

    @Configuration
    @Import({DataSourceConfig.class})
    static class DbmConfig {

        @Autowired
        private DataSource dataSource;

        @Value("${dbm.contexts}")
        private String contexts;

        @Value("${dbm.enabled}")
        private Boolean enabled;

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocations(getLocations());
            configurer.setFileEncoding("UTF-8");
            configurer.setOrder(Ordered.LOWEST_PRECEDENCE);
            return configurer;
        }

        private static Resource[] getLocations() {
            return new FileSystemResource[]{new FileSystemResource("src/main/resources/hibernate.properties")};
        }

        @Bean
        public SpringLiquibase liquibase() {
            SpringLiquibase liquibase = new SpringLiquibase();
            liquibase.setChangeLog(Def.CHANGELOG_FILE);
            liquibase.setDataSource(dataSource);
            liquibase.setDatabaseChangeLogTable(Def.CHANGELOG_TABLE);
            liquibase.setDatabaseChangeLogLockTable(Def.LOCK_TABLE);
            liquibase.setContexts(contexts);
            liquibase.setShouldRun(enabled);
            return liquibase;
        }
    }
}
