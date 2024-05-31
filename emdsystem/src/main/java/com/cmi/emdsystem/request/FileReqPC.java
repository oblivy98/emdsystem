package com.cmi.emdsystem.request;

public class FileReqPC {
	private String serialNumber;
	private String modelNumber;
	private String filePath;
	private String base64;
	private String fileName;
	private String size;

	public FileReqPC() {

	}

	public FileReqPC(String serialNumber, String modelNumber, String filePath, String base64, String fileName,
			String size) {
		super();
		this.serialNumber = serialNumber;
		this.modelNumber = modelNumber;
		this.filePath = filePath;
		this.base64 = base64;
		this.fileName = fileName;
		this.size = size;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
