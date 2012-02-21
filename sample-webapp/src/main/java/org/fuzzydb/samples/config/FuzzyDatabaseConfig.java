package org.fuzzydb.samples.config;

import java.util.ArrayList;
import java.util.Collections;

import org.fuzzydb.samples.data.DataGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.wwm.db.spring.random.RandomAttributeSource;

@Configuration
@ImportResource("classpath:/fuzzy-repository-context.xml")
public class FuzzyDatabaseConfig {

	@Bean
	public XStreamMarshaller marshaller() throws ClassNotFoundException {
		XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
		xStreamMarshaller.setAliases(Collections.singletonMap("objects", ArrayList.class));
		return xStreamMarshaller;
	}
	
	
//	@Bean StoreInitializer storeInitializer() {
//		return new StoreInitializer();
//	}
	
	@Bean
	@Lazy
	public DataGenerator dataGenerator() {
		return new DataGenerator(randomGenerator());
	}
	
    @Bean
    public RandomAttributeSource randomGenerator() {
    	return new RandomAttributeSource();
    }

//    @Bean 
//    public PostcodeConverter postcodeConverter() {
//    	return new PostcodeConverter();
//    }
}
