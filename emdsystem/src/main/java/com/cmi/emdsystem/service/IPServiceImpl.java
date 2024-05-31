package com.cmi.emdsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmi.emdsystem.model.IP;
import com.cmi.emdsystem.repository.IPRepository;

@Service
public class IPServiceImpl implements IPService {

	@Autowired
	private IPRepository ipRepository;

	@Override
	public Integer checkIP(Integer ipCode) throws Exception {

		return null;
	}

	@Override
	public List<IP> getAvailableIP(String ipSegment) throws Exception {

		return ipRepository.getAvailableIP(ipSegment);
	}

	@Override
	public IP getIp(int ipCode) {
		return ipRepository.findByIpCode(ipCode);
	}
}
