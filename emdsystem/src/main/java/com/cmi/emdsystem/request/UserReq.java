package com.cmi.emdsystem.request;

public class UserReq {

	private Integer userId;
	private String userName;
	private int userAccess;
	private String emailAddress;

	public UserReq() {

	}

	public UserReq(Integer userId, String userName, int userAccess, String emailAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userAccess = userAccess;
		this.emailAddress = emailAddress;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserAccess() {
		return userAccess;
	}

	public void setUserAccess(int userAccess) {
		this.userAccess = userAccess;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
