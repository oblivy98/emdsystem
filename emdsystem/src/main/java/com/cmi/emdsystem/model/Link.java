package com.cmi.emdsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MST_PCKANRI")
public class Link {

	@EmbeddedId
	private PCId linkId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "NM_SEIZONO", referencedColumnName = "NM_SEIZONO", nullable = false),
			@JoinColumn(name = "NM_KATABAN", referencedColumnName = "NM_KATABAN", nullable = false) })
	@MapsId
	private PC pcLink;

	public PC getPcLink() {
		return pcLink;
	}

	public void setPcLink(PC pcLink) {
		this.pcLink = pcLink;
	}

	public PCId getLinkId() {
		return linkId;
	}

	public void setLinkId(PCId linkId) {
		this.linkId = linkId;
	}

	public Link() {

	}

	public Link(String serialNumber, String modelNumber) {
		this.linkId = new PCId(serialNumber, modelNumber);
	}

	/*
	 * @Id
	 * 
	 * @Column(name = "NM_SEIZONO", length = 10, nullable = false) private String
	 * serialNumber;
	 * 
	 * @Id
	 * 
	 * @Column(name = "NM_KATABAN", length = 10, nullable = false) private String
	 * modelNumber;
	 */
	@Column(name = "CD_SYAINNO", length = 5, nullable = false)
	private Integer userId;

	@Column(name = "NM_ACTUALUSER", length = 40)
	private String actualUser;

	@ManyToOne
	@JoinColumn(name = "CD_SYAINNO", insertable = false, updatable = false)
	private User userLink;

	public User getUserLink() {
		return userLink;
	}

	public void setUserLink(User userLink) {
		this.userLink = userLink;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/*
	 * public String getSerialNumber() { return serialNumber; }
	 * 
	 * public void setSerialNumber(String serialNumber) { this.serialNumber =
	 * serialNumber; }
	 * 
	 * public String getModelNumber() { return modelNumber; }
	 * 
	 * public void setModelNumber(String modelNumber) { this.modelNumber =
	 * modelNumber; }
	 */

	public String getActualUser() {
		return actualUser;
	}

	public void setActualUser(String actualUser) {
		this.actualUser = actualUser;
	}
}