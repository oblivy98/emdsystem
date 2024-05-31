package com.cmi.emdsystem.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRN_KIKIINFO")
public class DeviceInformation {

	@EmbeddedId
	private DeviceInformationId deviceId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "NM_SEIZONO", referencedColumnName = "NM_SEIZONO", insertable = false, updatable = false),
			@JoinColumn(name = "NM_KATABAN", referencedColumnName = "NM_KATABAN", insertable = false, updatable = false) })
	private PC pcDevice;

	public PC getPcDevice() {
		return pcDevice;
	}

	public void setPcDevice(PC pcDevice) {
		this.pcDevice = pcDevice;
	}

	@Column(name = "NM_RIYOU")
	private String locOfUse;

	@Column(name = "KBN_NET")
	private int internalConnection;

	@Column(name = "KBN_OUTMOTIDASI")
	private int externalItems;

	@Column(name = "KBN_SYUBETU")
	private int classification;

	@Column(name = "DAY_SOUHU")
	private LocalDate officeSendDate;

	@Column(name = "DAY_HENKAN")
	private LocalDate officeReturnDate;

	@Column(name = "NM_MEMO")
	private String userHistory;

	@Column(name = "DAY_SINSEI")
	private LocalDate applicationDate;

	@Column(name = "KBN_KEIYAKUSYO")
	private int wfhAgreement;

	@Column(name = "DAY_ZAITAKU")
	private LocalDate wfhDate;

	@Column(name = "NM_BIKOU", length = 20)
	private String remarks;

	public DeviceInformationId getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(DeviceInformationId deviceId) {
		this.deviceId = deviceId;
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
