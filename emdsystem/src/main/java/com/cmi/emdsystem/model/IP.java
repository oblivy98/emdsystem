package com.cmi.emdsystem.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "MST_IP")
public class IP {

	@Id
	@Column(name = "CD_IP")
	//@OneToMany(cascade = CascadeType.All)
	//@JoinColumn(name="fk_cd_ip", reference)
	private int ipCode;
	
	@Column(name = "NM_IP", length = 15)
	private String ipAddress;
	
	@Column(name = "PURPOSE")
	private int purpose;
	
	@Column(name = "START_DAY", columnDefinition = "DATE")
	private Date startDay;
	
	@Column(name = "END_DAY", columnDefinition = "DATE")
	private Date endDay;
	
	@Column(name = "NM_BIKOU", length = 100)
	private String remarks;

	
	public int getIpCode() {
		return ipCode;
	}

	public void setIpCode(int ipCode) {
		this.ipCode = ipCode;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPurpose() {
		return purpose;
	}

	public void setPurpose(int purpose) {
		this.purpose = purpose;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
