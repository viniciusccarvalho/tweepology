package com.fb.tweepology.services.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fb.tweepology.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class},loader=AnnotationConfigContextLoader.class)
public class GraphTest {
	
	

	@Test
	public void test(){
		
	}
	
}
