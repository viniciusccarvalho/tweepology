package com.fb.tweepology.model.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.fb.tweepology.model.TwitterProfile;

public interface ProfileRepository extends GraphRepository<TwitterProfile>{

}
