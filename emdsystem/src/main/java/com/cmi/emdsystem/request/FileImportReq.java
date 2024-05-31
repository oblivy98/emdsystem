package com.cmi.emdsystem.request;

public class FileImportReq {

	private String base64;
	private String fileName;
	private String size;

	public FileImportReq(String base64, String fileName, String size) {
		super();
		this.base64 = base64;
		this.fileName = fileName;
		this.size = size;
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
