package com.fb.tweepology.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fb.tweepology.model.TwitterProfile;
import com.fb.tweepology.services.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	
	@Inject 
	private ProfileService profileService;
	
	@Inject Twitter twitter;
	
	private DateFormat groupFormat = new SimpleDateFormat("yyyyMMdd");
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	@ResponseBody
	@Transactional
	public org.springframework.social.twitter.api.TwitterProfile getProfile(@PathVariable("id") Long id){
		org.springframework.social.twitter.api.TwitterProfile p = twitter.userOperations().getUserProfile();
		TwitterProfile profile = new TwitterProfile(p);
		profile.persist();
		List<org.springframework.social.twitter.api.TwitterProfile> followers = twitter.friendOperations().getFollowers(); 
		List<org.springframework.social.twitter.api.TwitterProfile> friends = twitter.friendOperations().getFriends();
		for(org.springframework.social.twitter.api.TwitterProfile f : followers){
			TwitterProfile pf = new TwitterProfile(f);
			pf.persist();
			profile.addFollower(pf);
			
		}
		for(org.springframework.social.twitter.api.TwitterProfile f : friends){
			TwitterProfile pf = new TwitterProfile(f);
			pf.persist();
			profile.addFriend(pf);
		}
		return p;
	}
	
	@RequestMapping(value="timeline",method=RequestMethod.GET)
	@ResponseBody
	public List<Tweet> timeline(){
		Calendar lastMonth = Calendar.getInstance();
		lastMonth.add(Calendar.DAY_OF_MONTH, -30);
		List<Tweet> timeline =profileService.getUserTimeline(lastMonth.getTime(), new Date());
		
		return timeline;
	}
	
	@RequestMapping(value="timeline/groups",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> timelineGroup(){
		List<Tweet> timeline = timeline();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(Tweet t: timeline){
			String date = groupFormat.format(t.getCreatedAt());
			if(map.get(date) == null){
				map.put(date, 1);
			}else{
				map.put(date, map.get(date)+1);
			}
		}
		return map;
	}
	
	@RequestMapping(value="timeline/fans",method=RequestMethod.GET)
	@ResponseBody
	public String fans(){
		profileService.x();
		return "ok";
	}
}
