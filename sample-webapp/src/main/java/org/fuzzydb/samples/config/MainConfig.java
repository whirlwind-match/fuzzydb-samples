package org.fuzzydb.samples.config;

import org.fuzzydb.samples.social.ConnectedToHandlerInterceptor;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Turns on @Component scanning, loads application.properties, and enables Spring transaction management
 */
@Configuration
@ComponentScan(basePackageClasses = {ConnectedToHandlerInterceptor.class, MainConfig.class}
//, 
//	excludeFilters = { @Filter(Configuration.class), @Filter(type=FilterType.ASSIGNABLE_TYPE,value=MainConfig.class) }
)
@PropertySource("classpath:/application.properties")
@EnableTransactionManagement(mode=AdviceMode.ASPECTJ)
public class MainConfig {

}
