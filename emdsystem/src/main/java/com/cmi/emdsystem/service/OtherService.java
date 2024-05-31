package com.cmi.emdsystem.service;

import com.cmi.emdsystem.request.FileReq;
import com.cmi.emdsystem.request.FileReqPC;
import com.cmi.emdsystem.request.FileReqUser;

public interface OtherService {

	public void importData(FileReq file) throws Exception;

	public FileReq exportData(FileReq file) throws Exception;

	public void importCSVPC(FileReq file) throws Exception;

	public FileReqPC exportCSVPC(FileReqPC file) throws Exception;

	public void importCSVUser(FileReq file) throws Exception;

	public FileReqUser exportCSVUser(FileReqUser file) throws Exception;
}