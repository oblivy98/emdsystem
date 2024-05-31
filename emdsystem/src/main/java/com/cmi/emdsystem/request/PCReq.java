package com.cmi.emdsystem.request;

public class PCReq {
	private String serialNumber;
	private String modelNumber;
	private String productName;
	private String manufacturer;
	private int typeOfPc;
	private int pcDetails;
	private int application;
	private String locOfUse;
	private String internalSegment;
	private int wifiReg;
	private int operatingSystem;
	private int microsoftOffice;
	private int antiVirus;
	private String otherApplication;
	private int printerSetting;
	private int biosSetting;
	private int bitLocker;
	private int screenSaver;
	private String cpu;
	private String memory;
	private String hddssd;
	private String screen;
	private int webCamera;
	private int nfc;
	private String monitor;
	private String accessories;
	private String bitLockerKey;
	private String remarks;
	private String wirelessMac;
	private String wiredMac;
	private int ipCode;

	public PCReq() {

	}

	public PCReq(String serialNumber, String modelNumber, String productName, String manufacturer, int typeOfPc,
			int pcDetails, int application, String locOfUse, String internalSegment, int wifiReg, int operatingSystem,
			int microsoftOffice, int antiVirus, String otherApplication, int printerSetting, int biosSetting,
			int bitLocker, int screenSaver, String cpu, String memory, String hddssd, String screen, int webCamera,
			int nfc, String monitor, String accessories, String bitLockerKey, String remarks, String wirelessMac,
			String wiredMac, int ipCode) {
		super();
		this.serialNumber = serialNumber;
		this.modelNumber = modelNumber;
		this.productName = productName;
		this.manufacturer = manufacturer;
		this.typeOfPc = typeOfPc;
		this.pcDetails = pcDetails;
		this.application = application;
		this.locOfUse = locOfUse;
		this.internalSegment = internalSegment;
		this.wifiReg = wifiReg;
		this.operatingSystem = operatingSystem;
		this.microsoftOffice = microsoftOffice;
		this.antiVirus = antiVirus;
		this.otherApplication = otherApplication;
		this.printerSetting = printerSetting;
		this.biosSetting = biosSetting;
		this.bitLocker = bitLocker;
		this.screenSaver = screenSaver;
		this.cpu = cpu;
		this.memory = memory;
		this.hddssd = hddssd;
		this.screen = screen;
		this.webCamera = webCamera;
		this.nfc = nfc;
		this.monitor = monitor;
		this.accessories = accessories;
		this.bitLockerKey = bitLockerKey;
		this.remarks = remarks;
		this.wirelessMac = wirelessMac;
		this.wiredMac = wiredMac;
		this.ipCode = ipCode;
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
