package com.naicson.yugioh.entity;

import java.sql.Timestamp;

public class BasicData {
	
	private String event_name;
	private Timestamp event_time;
	private static final String action_source = "website";
	private UserData user_data;
	
	
	
	public BasicData(String event_name, Timestamp event_time, UserData user_data) {
		this.event_name = event_name;
		this.event_time = event_time;
		this.setUser_data(user_data);	
	}
	
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public Timestamp getEvent_time() {
		return event_time;
	}
	public void setEvent_time(Timestamp event_time) {
		this.event_time = event_time;
	}
	public static String getActionSource() {
		return action_source;
	}

	public UserData getUser_data() {
		return user_data;
	}

	public void setUser_data(UserData user_data) {
		this.user_data = user_data;
	}
	
	
}
