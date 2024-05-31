package com.cmi.emdsystem.request;

public class FileReq {

	private String filePath;
	private String base64;
	private String fileName;
	private String size;

	public FileReq() {

	}

	public FileReq(String filePath, String fileName, String base64, String size) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
		this.base64 = base64;
		this.size = size;
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
