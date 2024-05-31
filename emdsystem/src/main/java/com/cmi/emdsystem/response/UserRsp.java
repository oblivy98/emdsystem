package com.cmi.emdsystem.response;

import lombok.Data;

@Data
public class UserRsp {

	/*
	 * Integer getId(); String getUserName(); Integer getUserAccess(); String
	 * getEmailAddress();
	 */
	Integer userId;
	String userName;
	Integer userAccess;
	String emailAddress;

	public UserRsp(Integer userId, String userName, Integer userAccess, String emailAddress) {
		this.userId = userId;
		this.userName = userName;
		this.userAccess = userAccess;
		this.emailAddress = emailAddress;
	}
}
