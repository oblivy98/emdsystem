package com.cmi.emdsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.emdsystem.request.FileReq;
import com.cmi.emdsystem.request.FileReqPC;
import com.cmi.emdsystem.request.FileReqUser;
import com.cmi.emdsystem.service.OtherService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("importexport")
public class OtherController {

	@Autowired
	private OtherService otherService;

	@PostMapping("/exportdata")
	public FileReq exportFileData(@RequestBody FileReq file) throws Exception {
		return otherService.exportData(file);
	}

	@PostMapping("/importdata")
	public void importFileData(@RequestBody FileReq file) throws Exception {
		otherService.importData(file);
	}

	@PostMapping("/exportcsvpc")
	public FileReqPC exportCSVPC(@RequestBody FileReqPC file) throws Exception {
		return otherService.exportCSVPC(file);
	}

	@PostMapping("/importcsvpc")
	public void importCSVPC(@RequestBody FileReq file) throws Exception {
		otherService.importCSVPC(file);
	}

	@PostMapping("/exportcsvuser")
	public FileReqUser exportCSVUser(@RequestBody FileReqUser file) throws Exception {
		return otherService.exportCSVUser(file);
	}

	@PostMapping("/importcsvuser")
	public void importCSVUser(@RequestBody FileReq file) throws Exception {
		otherService.importCSVUser(file);
	}
}
