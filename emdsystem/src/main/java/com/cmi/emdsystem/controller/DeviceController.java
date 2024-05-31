package com.cmi.emdsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.emdsystem.repository.DeviceRepository;
import com.cmi.emdsystem.request.DeviceReq;
import com.cmi.emdsystem.response.DeviceRsp;
import com.cmi.emdsystem.service.DeviceService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/device")
public class DeviceController {

	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private DeviceService deviceService;

	@GetMapping("/devicelist")
	public List<DeviceRsp> getAllPCWithRecord() {
		return deviceService.findDeviceWithRecord();
	}

	/* Read Function */
	@GetMapping("/devicerecordinfo/{serialNumber}/{modelNumber}")
	public List<DeviceRsp> getDeviceInfo(@PathVariable String serialNumber, @PathVariable String modelNumber)
			throws Exception {
		return deviceService.findBypcId(serialNumber, modelNumber);
	}

	/* Create Link Information */
	@PostMapping("/devicecreate")
	public String saveDeviceInfo(@RequestBody DeviceReq deviceReq) throws Exception {

		int checkInfo = deviceService.saveDeviceInfo(deviceReq);
		if (checkInfo == 0) {
			return "Saved Successfully";
		} else {
			return "Unsuccessful";
		}
	}

	/* Edit Device Information */
	@PostMapping("/deviceedit")
	public String editLinkInfo(@RequestBody DeviceReq deviceReq) throws Exception {
		int checkInfo = deviceService.editDeviceInfo(deviceReq);
		if (checkInfo == 0) {
			return "Saved Successfull;y";
		} else {
			return "Unsuccessful";
		}
	}

	/* Delete Device */
	@DeleteMapping("/devicedelete")
	public Integer deleteLinkInfo(@RequestBody DeviceReq deviceReq) throws Exception {
		return deviceService.deleteDeviceInfo(deviceReq);
	}

}
