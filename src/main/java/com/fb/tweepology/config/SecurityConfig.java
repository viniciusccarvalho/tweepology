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
package com.fb.tweepology.config;

import javax.annotation.PostConstruct;
import javax.resource.spi.work.SecurityContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security Configuration.
 * @author Craig Walls
 */
@Configuration
@ImportResource("classpath:com/fb/tweepology/config/security.xml")
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
    	return NoOpPasswordEncoder.getInstance();
	}
    
	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}
	
	@PostConstruct
	public void setupSecurityContext(){
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}

}
