package com.fb.tweepology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.fb.tweepology", excludeFilters = { @Filter(Configuration.class) })
public class WebMVCConfig extends WebMvcConfigurerAdapter {

	
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	public void configureResourceHandling(ResourceConfigurer resourceConfigurer) {
		resourceConfigurer.addPathMapping("/resources/**");
		resourceConfigurer.addResourceLocation("/resources/");
	}
}
