package com.fb.tweepology.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Vinicius Carvalho
 * 
 * General Application Context configuration
 *
 */
@Configuration
@ComponentScan(basePackages = "com.fb.tweepology", excludeFilters = { @Filter(Configuration.class) })
@PropertySource("classpath:com/fb/tweepology/config/application.properties")
@EnableTransactionManagement
@ImportResource("classpath:com/fb/tweepology/config/datagraph.xml")
public class AppConfig {

	
	@Inject
	private Environment environment;
	
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(datasource());
	}
	
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(datasource());
	}

	
	@Bean(destroyMethod="close")
	public DataSource datasource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(environment.getProperty("db.driver"));
		ds.setUrl(environment.getProperty("db.url"));
		ds.setUsername(environment.getProperty("db.username"));
		ds.setPassword(environment.getProperty("db.password"));
		return ds;
	}
	
	
}
