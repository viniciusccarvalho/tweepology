package com.fb.tweepology.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AysncConfig implements AsyncConfigurer {

	public Executor getAsyncExecutor() {
		SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor("ASYNC-EXECUTOR");
		return executor;
	}

}
