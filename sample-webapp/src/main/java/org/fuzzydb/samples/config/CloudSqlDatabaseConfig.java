package org.fuzzydb.samples.config;

import javax.inject.Named;
import javax.sql.DataSource;

import org.cloudfoundry.runtime.service.CloudServicesScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Primary
@Configuration
@EnableTransactionManagement
@Profile("cloud")
public class CloudSqlDatabaseConfig extends SqlDatabaseConfig {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired(required=false)
	@Named("fuzzydb-sample-webapp-data")
	private DataSource cloudDataSource;
	
	static public @Bean CloudServicesScanner getCloudServicesScanner() {
		return new CloudServicesScanner();
	}
	
	
	@Primary
	@Bean
	public DataSource dataSource() {
		
		if (cloudDataSource != null) {
			log.info("Using DataSource from environment: " + cloudDataSource);
			return cloudDataSource;
		}
		
		// if not configured in this deployment use embedded
		return super.dataSource();	// TODO: (destroyMethod = "shutdown") needs registering somehow...
	}
}
