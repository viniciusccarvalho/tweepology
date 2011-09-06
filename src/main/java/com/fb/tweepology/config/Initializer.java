package com.fb.tweepology.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import com.fb.tweepology.account.JdbcAccountRepository;

@Configuration
public class Initializer {

	@Inject DataSource datasource;
	
	@Inject JdbcTemplate template;
	
	@Bean(name="DSInitializer")
	public DataSourceInitializer initializer(){
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(datasource);
		initializer.setDatabasePopulator(databasePopulator());
		initializer.setEnabled(true);
		return initializer;
	}
	
	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("JdbcRepository.sql", Initializer.class));
		populator.addScript(new ClassPathResource("Account.sql", JdbcAccountRepository.class));
		return populator;
	}
}
