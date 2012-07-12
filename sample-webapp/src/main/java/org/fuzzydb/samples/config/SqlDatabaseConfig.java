package org.fuzzydb.samples.config;

import javax.inject.Named;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Config SQL data sources
 */
@Configuration
@EnableTransactionManagement
public class SqlDatabaseConfig {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired(required=false)
	@Named("fuzzydb-sample-webapp-data")
	private DataSource cloudDataSource;
	
	
	@Bean // (destroyMethod = "shutdown") only available on EmbeddedDatabase TODO: register shutdown if needed
	public DataSource dataSource() {
		
		if (cloudDataSource != null) {
			log.info("Using DataSource from environment: " + cloudDataSource);
			return cloudDataSource;
		}
		
		EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
		factory.setDatabaseName("fuzzydb-sample-webapp");
		factory.setDatabaseType(EmbeddedDatabaseType.H2);
		factory.setDatabasePopulator(databasePopulator());
		return factory.getDatabase();
	}
	// internal helpers

	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("JdbcUsersConnectionRepository.sql", JdbcUsersConnectionRepository.class));
		return populator;
	}

}
