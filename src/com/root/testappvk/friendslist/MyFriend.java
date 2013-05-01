package com.root.testappvk.friendslist;

import java.io.Serializable;

public class MyFriend implements Serializable{
	
	private long id;
	private long uid;
	private String firstName;
	private String lastName;
	private String photoURL;
	private int isOnline;
	
	public MyFriend(long id, long uid, String firstName, String lastName, String photoURL, int isOnline) {
		this.id = id;
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.photoURL = photoURL;
		this.isOnline = isOnline;
	}

	public int getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	
	
}
