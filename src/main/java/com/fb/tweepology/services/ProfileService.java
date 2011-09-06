package com.fb.tweepology.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Async;
import org.springframework.social.ApiException;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

	@Inject
	private Twitter twitter;

	public List<Tweet> getUserTimeline(Date from, Date to) {
		List<Tweet> timeline = new ArrayList<Tweet>();
		Date last = to;
		int page = 1;
		while (last.getTime() - from.getTime() > 0) {
			List<Tweet> temp = twitter.timelineOperations().getUserTimeline(
					page, 200);
			for (Tweet t : temp) {
				last = t.getCreatedAt();
				if (last.getTime() - from.getTime() > 0) {
					timeline.add(t);
				} else {
					break;
				}
			}
			page++;
		}
		return timeline;
	}

	
	public List<Tweet> getUserTimeline(){
		boolean hasNext = true;
		List<Tweet> timeline = new ArrayList<Tweet>();
		int page = 1;
		while(hasNext){
			List<Tweet> temp = twitter.timelineOperations().getUserTimeline(page, 200);
			if(temp.size() == 0){
				hasNext = false;
			}
			timeline.addAll(temp);
			page++;
		}
		return timeline;
	}
	
	
	public void x(){
		Thread t = new Thread(new ThereIFixIt(twitter.timelineOperations()));
		t.start();
	}
		

	
	private class ThereIFixIt implements Runnable{
		private TimelineOperations operations;
		
		public ThereIFixIt(TimelineOperations timeline){
			this.operations = timeline;
		}

		public void run() {
			
			long start = System.currentTimeMillis();
			boolean hasNext = true;
			List<Tweet> timeline = new ArrayList<Tweet>();
			HashMap<String, Integer> fans = new HashMap();
			long sinceId = 0;
			long maxId = 0;
			
			while(hasNext){
				try{
					
					List<Tweet> temp = this.operations.getMentions(1, 100, sinceId, maxId);
					if(temp.size() == 0){
						break;
					}
					timeline.addAll(temp);
					maxId = temp.get(temp.size()-1).getId()-1;
					
				}catch (ApiException e) {
					System.err.println("Fuck you twitter, non scalable shit");
					try {
						Thread.sleep(1000);
						continue;
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				
			}
			
			for(Tweet t: timeline){
				int value = fans.get(t.getFromUser()) == null ? 1 : fans.get(t.getFromUser()) + 1;
				fans.put(t.getFromUser(),value);
			}
			long end = System.currentTimeMillis();
			System.out.println("Total processtime: " + (end-start));
			for(String s : fans.keySet()){
				System.out.println(s + " : " + fans.get(s));
			}
		}
		
	}
	
	
}
