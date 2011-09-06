package com.fb.tweepology.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializaer  {

	public void onStartup(ServletContext container) {
	/*	AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

		context.register(AppConfig.class);
		container.addListener(new ContextLoaderListener(context));
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebMVCConfig.class);

		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		*/
	}

}
