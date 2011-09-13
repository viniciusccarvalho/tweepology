package com.fb.tweepology.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.core.Direction;

@NodeEntity
public class TwitterProfile implements Serializable {

	

	public TwitterProfile(){}
	@Indexed
	private Long id;
	private int followersCount;
	private int favoritesCount;
	private int friendsCount;
	private Date createdDate;
	private int statusesCount;
	private String name;
	private String screenName;
	
	@RelatedTo(elementClass=TwitterProfile.class,type="FOLLOWS",direction=Direction.INCOMING)
	private Set<TwitterProfile> follows;
	
	@RelatedTo(elementClass=TwitterProfile.class,type="FOLLOWS",direction=Direction.OUTGOING)
	private Set<TwitterProfile> followers;
	
	
	
	
	public TwitterProfile(org.springframework.social.twitter.api.TwitterProfile adaptee){
		this.id = adaptee.getId();
		this.followersCount = adaptee.getFollowersCount();
		this.favoritesCount = adaptee.getFavoritesCount();
		this.friendsCount = adaptee.getFriendsCount();
		this.createdDate = adaptee.getCreatedDate();
		this.statusesCount = adaptee.getStatusesCount();
		this.name = adaptee.getName();
		this.screenName = adaptee.getScreenName();
		this.followers = new HashSet<TwitterProfile>();
		this.follows = new HashSet<TwitterProfile>();
	}
	
	public void addFollower(TwitterProfile p){
		this.followers.add(p);
	}
	
	public void addFriend(TwitterProfile p){
		this.follows.add(p);
	}
	public Set<TwitterProfile> getFollows() {
		return Collections.unmodifiableSet(this.follows);
	}

	public Set<TwitterProfile> getFollowers() {
		return Collections.unmodifiableSet(this.followers);
	}
	

	public Long getId() {
		return id;
	}

	public float getPopularity(){
		return (float)this.followersCount/(float)this.friendsCount;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public int getFavoritesCount() {
		return favoritesCount;
	}

	public void setFavoritesCount(int favoritesCount) {
		this.favoritesCount = favoritesCount;
	}

	public int getFriendsCount() {
		return friendsCount;
	}

	public void setFriendsCount(int friendsCount) {
		this.friendsCount = friendsCount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getStatusesCount() {
		return statusesCount;
	}

	public void setStatusesCount(int statusesCount) {
		this.statusesCount = statusesCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	public float getDailyAverage(){
		if(this.createdDate == null){
			return 0f;
		}
		Date today = new Date();
		int numdays = (int)((today.getTime()-this.createdDate.getTime())/(1000 * 60 * 60 * 24));
		return (float)this.getStatusesCount()/(float)numdays;
	}
	
}
