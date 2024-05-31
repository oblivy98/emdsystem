package com.cmi.emdsystem.request;

public class LinkReq {

	private String serialNumber;
	private String modelNumber;
	private Integer userId;
	private String actualUser;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getActualUser() {
		return actualUser;
	}

	public void setActualUser(String actualUser) {
		this.actualUser = actualUser;
	}

	public LinkReq() {

	}

	public LinkReq(String serialNumber, String modelNumber, Integer userId, String actualUser) {
		super();
		this.serialNumber = serialNumber;
		this.modelNumber = modelNumber;
		this.userId = userId;
		this.actualUser = actualUser;
	}

}
