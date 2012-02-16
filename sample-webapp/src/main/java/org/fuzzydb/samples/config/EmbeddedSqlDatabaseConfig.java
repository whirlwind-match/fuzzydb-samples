package org.fuzzydb.samples.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@Profile("embedded")
public class EmbeddedSqlDatabaseConfig extends SqlDatabaseConfig {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	
	@Bean(destroyMethod = "shutdown") 
	public DataSource dataSource() {
		return super.dataSource();
	}

}
