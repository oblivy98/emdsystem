package com.cmi.emdsystem.response;

import java.time.LocalDate;

public class DeviceRsp {

	private String serialNumber;
	private String modelNumber;
	private LocalDate updateDay;
	private String locOfUse;
	private int internalConnection;
	private int externalItems;
	private int classification;
	private LocalDate officeSendDate;
	private LocalDate officeReturnDate;
	private String userHistory;
	private LocalDate applicationDate;
	private int wfhAgreement;
	private LocalDate wfhDate;
	private String remarks;

	public DeviceRsp() {

	}

	public DeviceRsp(String serialNumber, String modelNumber, LocalDate updateDay, String locOfUse,
			int internalConnection, int externalItems, int classification, LocalDate officeSendDate,
			LocalDate officeReturnDate, String userHistory, LocalDate applicationDate, int wfhAgreement,
			LocalDate wfhDate, String remarks) {
		super();
		this.serialNumber = serialNumber;
		this.modelNumber = modelNumber;
		this.updateDay = updateDay;
		this.locOfUse = locOfUse;
		this.internalConnection = internalConnection;
		this.externalItems = externalItems;
		this.classification = classification;
		this.officeSendDate = officeSendDate;
		this.officeReturnDate = officeReturnDate;
		this.userHistory = userHistory;
		this.applicationDate = applicationDate;
		this.wfhAgreement = wfhAgreement;
		this.wfhDate = wfhDate;
		this.remarks = remarks;
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

	public LocalDate getUpdateDay() {
		return updateDay;
	}

	public void setUpdateDay(LocalDate updateDay) {
		this.updateDay = updateDay;
	}

	public String getLocOfUse() {
		return locOfUse;
	}

	public void setLocOfUse(String locOfUse) {
		this.locOfUse = locOfUse;
	}

	public int getInternalConnection() {
		return internalConnection;
	}

	public void setInternalConnection(int internalConnection) {
		this.internalConnection = internalConnection;
	}

	public int getExternalItems() {
		return externalItems;
	}

	public void setExternalItems(int externalItems) {
		this.externalItems = externalItems;
	}

	public int getClassification() {
		return classification;
	}

	public void setClassification(int classification) {
		this.classification = classification;
	}

	public LocalDate getOfficeSendDate() {
		return officeSendDate;
	}

	public void setOfficeSendDate(LocalDate officeSendDate) {
		this.officeSendDate = officeSendDate;
	}

	public LocalDate getOfficeReturnDate() {
		return officeReturnDate;
	}

	public void setOfficeReturnDate(LocalDate officeReturnDate) {
		this.officeReturnDate = officeReturnDate;
	}

	public String getUserHistory() {
		return userHistory;
	}

	public void setUserHistory(String userHistory) {
		this.userHistory = userHistory;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}

	public int getWfhAgreement() {
		return wfhAgreement;
	}

	public void setWfhAgreement(int wfhAgreement) {
		this.wfhAgreement = wfhAgreement;
	}

	public LocalDate getWfhDate() {
		return wfhDate;
	}

	public void setWfhDate(LocalDate wfhDate) {
		this.wfhDate = wfhDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
