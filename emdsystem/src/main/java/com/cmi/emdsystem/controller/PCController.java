package com.cmi.emdsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.emdsystem.model.PC;
import com.cmi.emdsystem.repository.PCRepository;
import com.cmi.emdsystem.request.PCReq;
import com.cmi.emdsystem.service.PCService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/pc")
public class PCController {

	@Autowired
	private PCRepository pcRepository;
	@Autowired
	private PCService pcService;

	@GetMapping("/pclist")
	public List<PC> getAllPC() {
		return pcService.findAll();
	}

	@GetMapping("/pcinfo/{serialNumber}/{modelNumber}")
	public Optional<PC> getPCInfo(@PathVariable String serialNumber, @PathVariable String modelNumber)
			throws Exception {
		return Optional.ofNullable(pcService.findPCBySerialNumAndModelNum(serialNumber, modelNumber));
	}

	/* Create User */
	@PostMapping("/pccreate")
	public String savePCInfo(@RequestBody PC pc) throws Exception {
		int checkInfo = pcService.savePCInfo(pc);
		if (checkInfo == 0) {
			return "Saved Successfully";
		} else {
			return "Unsuccessful";
		}
	}

	/* Edit User */
	@PostMapping("/pcedit")
	public void editUserInfo(@RequestBody PC pc) throws Exception {
		int checkInfo = pcService.editPCInfo(pc);
	}

	/* Delete Mapping */
	@DeleteMapping("/pcdelete")
	public void deleteUserInfo(@RequestBody PCReq pcReq) throws Exception {
		pcService.deletePC(pcReq);
	}

	/* LINK */
	/* Get Available PC */
	@GetMapping("/link/availablepc")
	public List<PC> getAvailablePC() throws Exception {
		return pcService.listOfAvailablePC();
	}

	/* Get Linked PC */
	@GetMapping("/link/linkedpc")
	public List<PC> getLinkedPC() throws Exception {
		return pcService.listOfLinkedPC();
	}

	/* Get Available PC */
	@GetMapping("/link/unlinkedpc")
	public List<PC> getUnlinkedPC() throws Exception {
		return pcService.listOfAvailablePC();
	}
}
