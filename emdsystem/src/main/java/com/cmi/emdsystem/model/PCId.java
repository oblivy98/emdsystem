package com.cmi.emdsystem.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class PCId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NM_SEIZONO", length = 10)
	private String serialNumber;

	@Column(name = "NM_KATABAN", length = 20)
	private String modelNumber;

	public PCId() {

	}

	public PCId(String serialNumber, String modelNumber) {
		super();
		this.serialNumber = serialNumber;
		this.modelNumber = modelNumber;
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
}
