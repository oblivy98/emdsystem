package com.cmi.emdsystem.service;

import java.util.List;

import com.cmi.emdsystem.model.IP;

public interface IPService {

	public Integer checkIP(Integer ipCode) throws Exception;

	public List<IP> getAvailableIP(String ipSegment) throws Exception;

	public IP getIp(int ipCode);
}
