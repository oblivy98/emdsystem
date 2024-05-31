package com.cmi.emdsystem.request;

public class LinkSwapReq {

	private String serialNumber;
	private String modelNumber;
	private Integer userId;
	private String actualUser;
	private String oldSerialNumber;
	private String oldModelNumber;

	public LinkSwapReq() {

	}

	public LinkSwapReq(String serialNumber, String modelNumber, Integer userId, String actualUser,
			String oldSerialNumber, String oldModelNumber) {
		super();
		this.serialNumber = serialNumber;
		this.modelNumber = modelNumber;
		this.userId = userId;
		this.actualUser = actualUser;
		this.oldSerialNumber = oldSerialNumber;
		this.oldModelNumber = oldModelNumber;
	}

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

	public String getOldSerialNumber() {
		return oldSerialNumber;
	}

	public void setOldSerialNumber(String oldSerialNumber) {
		this.oldSerialNumber = oldSerialNumber;
	}

	public String getOldModelNumber() {
		return oldModelNumber;
	}

	public void setOldModelNumber(String oldModelNumber) {
		this.oldModelNumber = oldModelNumber;
	}

}
