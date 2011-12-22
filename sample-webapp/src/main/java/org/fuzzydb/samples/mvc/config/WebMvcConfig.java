/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fuzzydb.samples.mvc.config;

import javax.inject.Inject;

import org.fuzzydb.samples.social.ConnectedToHandlerInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.wwm.attrs.converters.RefToStringConverter;
import com.wwm.attrs.converters.StringToRefConverter;

@Configuration
@EnableWebMvc
@ComponentScan("org.fuzzydb.samples.mvc")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Inject
	private ConnectionRepository connectionRepository;

	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ConnectedToHandlerInterceptor(connectionRepository));		
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		super.addFormatters(registry);
		registry.addConverter(new StringToRefConverter());
		registry.addConverter(new RefToStringConverter());
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// for spring-js dojo
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/META-INF/web-resources/");
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login");
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}
	
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/WEB-INF/messages/messages");
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

    @Bean
    public ViewResolver viewResolver() {
    	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    	viewResolver.setPrefix("/WEB-INF/views/");
    	viewResolver.setSuffix(".jsp");
    	return viewResolver;
    }
}
