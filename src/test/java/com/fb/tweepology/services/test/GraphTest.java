package com.fb.tweepology.services.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fb.tweepology.model.TwitterProfile;
import com.fb.tweepology.model.repositories.ProfileRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/context-test.xml"})
public class GraphTest {
	
	@Inject ProfileRepository profileRepository;

	@Test
	public void test(){
		TwitterProfile profile = profileRepository.findByPropertyValue("id", 69262970L);
		System.out.println(profile.getScreenName() + " " + profile.getFollowersCount());
	}
	
}
