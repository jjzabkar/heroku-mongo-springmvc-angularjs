package com.jjz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

/**
 * Spring context {@code Configuration} that configures the root Spring context.
 * <p>
 * Does <b>NOT</b> load {@link Controller}s; that is the job of {@link WebMvcConfiguration}.
 */
@Configuration
@Import({ MongoDbConfiguration.class })
@ComponentScan(basePackages = { "com.jjz" }, excludeFilters = @ComponentScan.Filter({ Controller.class, Configuration.class }))
public class WebModuleConfiguration {

	Logger logger = LoggerFactory.getLogger(getClass());

	public WebModuleConfiguration() {
		logger.info("init");
	}

}
