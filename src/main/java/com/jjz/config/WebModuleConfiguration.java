package com.jjz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;

/**
 * Spring context {@code Configuration} that configures the root Spring context.
 * <p>
 * Does <b>NOT</b> load {@link Controller}s; that is the job of {@link WebMvcConfiguration}.
 */
@Configuration
@Import({ MongoDbConfiguration.class })
@PropertySource("classpath:git.properties")
@ComponentScan(basePackages = { "com.jjz" }, excludeFilters = @ComponentScan.Filter({ Controller.class, Configuration.class }))
public class WebModuleConfiguration {

	Logger logger = LoggerFactory.getLogger(getClass());

	public WebModuleConfiguration() {
		logger.info("init");
	}

	@Bean(name = "propertySourcesPlaceholderConfigurer")
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@DependsOn("propertySourcesPlaceholderConfigurer")
	public GitRepositoryState gitRepositoryState() {
		return new GitRepositoryState();
	}

}
