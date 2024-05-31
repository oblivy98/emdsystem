package com.cmi.emdsystem.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MST_PCINFO")
public class PC {

	// 1

	@EmbeddedId
	private PCId pcId;

	public PCId getPcId() {
		return pcId;
	}

	public void setPcId(PCId pcId) {
		this.pcId = pcId;
	}

	public PC() {

	}

	public PC(String serialNumber, String modelNumber) {
		this.pcId = new PCId(serialNumber, modelNumber);
	}

	/*
	 * @Id
	 * 
	 * @Column(name = "NM_SEIZONO", length = 10) private String serialNumber;
	 * 
	 * @Id
	 * 
	 * @Column(name = "NM_KATABAN", length = 20) private String modelNumber;
	 */
	@Column(name = "NM_SYOUHIN", length = 30)
	private String productName;

	@Column(name = "KBN_MEKA", length = 20)
	private String manufacturer;

	@Column(name = "KBN_PC")
	private int typeOfPc;

	// 6

	@Column(name = "KBN_PCDETAIL")
	private int pcDetails;

	@Column(name = "KBN_SIYOU")
	private int application;

	@Column(name = "NM_RIYOU", length = 10)
	private String locOfUse;

	@Column(name = "NM_SEGMENT", length = 10)
	private String internalSegment;

	@Column(name = "KBN_WIFI")
	private int wifiReg;

	// 11

	@Column(name = "KBN_OS")
	private int operatingSystem;

	@Column(name = "KBN_MSOFFICE")
	private int microsoftOffice;

	@Column(name = "KBN_SECURITY")
	private int antiVirus;

	@Column(name = "NM_DWSOFT", length = 1500)
	private String otherApplication;

	@Column(name = "KBN_PRINT")
	private int printerSetting;

	// 16

	@Column(name = "KBN_BIOS")
	private int biosSetting;

	@Column(name = "KBN_BITLOCK")
	private int bitLocker;

	@Column(name = "KBN_SCREENSAFE")
	private int screenSaver;

	@Column(name = "NM_CPU", length = 40)
	private String cpu;

	@Column(name = "NM_MEMORY", length = 30)
	private String memory;

	// 21

	@Column(name = "NM_HDDSSD", length = 20)
	private String hddssd;

	@Column(name = "NM_SCREEN", length = 30)
	private String screen;

	@Column(name = "KBN_KAMERA")
	private int webCamera;

	@Column(name = "KBN_NFC")
	private int nfc;

	@Column(name = "NM_MONITOR", length = 30)
	private String monitor;

	// 26

	@Column(name = "NM_SUB", length = 20)
	private String accessories;

	@Column(name = "NM_BITKEY", length = 100)
	private String bitLockerKey;

	@Column(name = "NM_BIKOU", length = 100)
	private String remarks;

	@Column(name = "NM_WIRELESSMAC", length = 17)
	private String wirelessMac;

	@Column(name = "NM_WIREDMAC", length = 17)
	private String wiredMac;

	// 31
	@Column(name = "CD_IP")
	private int ipCode;

	@OneToOne(mappedBy = "pcLink", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "NM_SEIZONO")
	@JoinColumn(name = "NM_KATABAN")
	private Link linkPc;

	@OneToMany(mappedBy = "pcDevice", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<DeviceInformation> pcDevice = new ArrayList<DeviceInformation>();

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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getTypeOfPc() {
		return typeOfPc;
	}

	public void setTypeOfPc(int typeOfPc) {
		this.typeOfPc = typeOfPc;
	}

	public int getPcDetails() {
		return pcDetails;
	}

	public void setPcDetails(int pcDetails) {
		this.pcDetails = pcDetails;
	}

	public int getApplication() {
		return application;
	}

	public void setApplication(int application) {
		this.application = application;
	}

	public String getLocOfUse() {
		return locOfUse;
	}

	public void setLocOfUse(String locOfUse) {
		this.locOfUse = locOfUse;
	}

	public String getInternalSegment() {
		return internalSegment;
	}

	public void setInternalSegment(String internalSegment) {
		this.internalSegment = internalSegment;
	}

	public int getWifiReg() {
		return wifiReg;
	}

	public void setWifiReg(int wifiReg) {
		this.wifiReg = wifiReg;
	}

	public int getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(int operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public int getMicrosoftOffice() {
		return microsoftOffice;
	}

	public void setMicrosoftOffice(int microsoftOffice) {
		this.microsoftOffice = microsoftOffice;
	}

	public int getAntiVirus() {
		return antiVirus;
	}

	public void setAntiVirus(int antiVirus) {
		this.antiVirus = antiVirus;
	}

	public String getOtherApplication() {
		return otherApplication;
	}

	public void setOtherApplication(String otherApplication) {
		this.otherApplication = otherApplication;
	}

	public int getPrinterSetting() {
		return printerSetting;
	}

	public void setPrinterSetting(int printerSetting) {
		this.printerSetting = printerSetting;
	}

	public int getBiosSetting() {
		return biosSetting;
	}

	public void setBiosSetting(int biosSetting) {
		this.biosSetting = biosSetting;
	}

	public int getBitLocker() {
		return bitLocker;
	}

	public void setBitLocker(int bitLocker) {
		this.bitLocker = bitLocker;
	}

	public int getScreenSaver() {
		return screenSaver;
	}

	public void setScreenSaver(int screenSaver) {
		this.screenSaver = screenSaver;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getHddssd() {
		return hddssd;
	}

	public void setHddssd(String hddssd) {
		this.hddssd = hddssd;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public int getWebCamera() {
		return webCamera;
	}

	public void setWebCamera(int webCamera) {
		this.webCamera = webCamera;
	}

	public int getNfc() {
		return nfc;
	}

	public void setNfc(int nfc) {
		this.nfc = nfc;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public String getBitLockerKey() {
		return bitLockerKey;
	}

	public void setBitLockerKey(String bitLockerKey) {
		this.bitLockerKey = bitLockerKey;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getWirelessMac() {
		return wirelessMac;
	}

	public void setWirelessMac(String wirelessMac) {
		this.wirelessMac = wirelessMac;
	}

	public String getWiredMac() {
		return wiredMac;
	}

	public void setWiredMac(String wiredMac) {
		this.wiredMac = wiredMac;
	}

	public int getIpCode() {
		return ipCode;
	}

	public void setIpCode(int ipCode) {
		this.ipCode = ipCode;
	}
}
