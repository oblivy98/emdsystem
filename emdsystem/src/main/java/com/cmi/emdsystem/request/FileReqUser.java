package com.cmi.emdsystem.request;

public class FileReqUser {
	private Integer userId;
	private String filePath;
	private String base64;
	private String fileName;
	private String size;

	public FileReqUser() {

	}

	public FileReqUser(Integer userId, String filePath, String base64, String fileName, String size) {
		super();
		this.userId = userId;
		this.filePath = filePath;
		this.base64 = base64;
		this.fileName = fileName;
		this.size = size;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
