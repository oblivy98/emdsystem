package com.cmi.emdsystem.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DeviceInformationId {
	private static final long serialVersionUID = 1L;

	@Column(name = "NM_SEIZONO", length = 10, insertable = false, updatable = false)
	private String serialNumber;

	@Column(name = "NM_KATABAN", length = 10, insertable = false, updatable = false)
	private String modelNumber;

	@Column(name = "DAY_UPDATE")
	private LocalDate updateDay;

	public DeviceInformationId() {

	}

	public DeviceInformationId(String serialNumber, String modelNumber, LocalDate updateDay) {
		super();
		this.serialNumber = serialNumber;
		this.modelNumber = modelNumber;
		this.updateDay = updateDay;
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
}
