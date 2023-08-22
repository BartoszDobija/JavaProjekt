package works.buddy.library.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    public static final class Def {

        public static final String CHANGELOG_TABLE = "DATABASECHANGELOG";

        public static final String LOCK_TABLE = "DATABASECHANGELOGLOCK";

        public static final String CHANGELOG_FILE = "dbm/changelog.xml";

    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(LiquibaseConfig.Def.CHANGELOG_FILE);
        liquibase.setDataSource(dataSource);
        liquibase.setDatabaseChangeLogTable(LiquibaseConfig.Def.CHANGELOG_TABLE);
        liquibase.setDatabaseChangeLogLockTable(LiquibaseConfig.Def.LOCK_TABLE);
        return liquibase;
    }
}