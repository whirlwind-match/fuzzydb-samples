package org.fuzzydb.samples.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Turns on @Component scanning, loads application.properties, and enables Spring transaction management
 */
@Configuration
@ComponentScan(basePackages = "org.fuzzydb.samples.social", excludeFilters = { @Filter(Configuration.class) })
@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
public class MainConfig {

}
