package com.cmi.emdsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.emdsystem.model.IP;
import com.cmi.emdsystem.repository.IPRepository;
import com.cmi.emdsystem.service.IPService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/ip")
public class IPController {

	@Autowired
	private IPRepository ipRepository;
	
	@Autowired
	private IPService ipService;
	
	@GetMapping("/ipinfo/{ipCode}")
	public Optional<IP> getIPInfo(@PathVariable int ipCode) throws Exception{
		return ipRepository.findById(ipCode);
	}
	
	@GetMapping("/iplistavailable/{ipSegment}")
	public List<IP> getAvailableIP(@PathVariable String ipSegment) throws Exception{
		return ipService.getAvailableIP(ipSegment);
	}
}
