package com.cmi.emdsystem.service;

import java.util.List;

import com.cmi.emdsystem.model.PC;
import com.cmi.emdsystem.request.PCReq;

public interface PCService {

	public List<PC> findAll();

	public PC findPCBySerialNumAndModelNum(String serialNum, String modelNum) throws Exception;

	// PC Form Service
	public Integer checkIPAvailability(Integer ipAddress) throws Exception;

	public Integer savePCInfo(PC pc) throws Exception;

	public Integer editPCInfo(PC pc) throws Exception;

	public List<PC> listOfLinkedPC();

	public List<PC> listOfAvailablePC();

	public void deletePC(PCReq pcReq);
}
