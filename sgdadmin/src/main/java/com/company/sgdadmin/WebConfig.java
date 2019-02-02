package com.company.sgdadmin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration		
@EnableWebMvc
public class WebConfig  implements WebMvcConfigurer  {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/static/images/**",
                "/static/css/**",
                "/static/js/**")
                .addResourceLocations(
                        "classpath:/static/images/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");	
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
}
