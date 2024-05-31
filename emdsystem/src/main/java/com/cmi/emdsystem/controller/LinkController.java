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

import com.cmi.emdsystem.model.Link;
import com.cmi.emdsystem.repository.LinkRepository;
import com.cmi.emdsystem.request.LinkReq;
import com.cmi.emdsystem.request.LinkSaveReq;
import com.cmi.emdsystem.request.LinkSwapReq;
import com.cmi.emdsystem.response.LinkRsp;
import com.cmi.emdsystem.service.LinkService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/link")
public class LinkController {

	@Autowired
	private LinkRepository linkRepository;
	@Autowired
	private LinkService linkService;

	/* Read Function */

	// Count record
	@GetMapping("/count")
	public long countRecord() {
		return linkRepository.count();
	}

	@GetMapping("/linklist")
	public List<Link> getAllLinked() {
		return linkRepository.findAll();
	}

	// Get linked PC to user
	@GetMapping("/linked/{userId}")
	public Optional<List<LinkRsp>> getLinked(@PathVariable Integer userId) throws Exception {
		return Optional.of(linkService.findByUserId(userId));// Optional.of(linkService.findByUserId(userId));
	}

	// Get PC Information
	@GetMapping("/linkinfo/{serialNumber}/{modelNumber}")
	public Optional<Link> getLinkInfo(@PathVariable String serialNumber, String modelNumber) throws Exception {
		return Optional.ofNullable(linkService.findLinkByPCId(serialNumber, modelNumber));
	}

	/* Create Link Information */
	@PostMapping("/linkcreate")
	public String saveLinkInfo(@RequestBody LinkSaveReq link) throws Exception {
		int checkInfo = linkService.saveLinkInfo(link);
		if (checkInfo == 0) {
			return "Saved Successfully";
		} else {
			return "Unsuccessful";
		}
	}

	/* Edit Link Information */
	@PostMapping("/linkedit")
	public void editLinkInfo(@RequestBody Link link) throws Exception {
		int checkInfo = linkService.editLinkInfo(link);
	}

	/* Delete Link */
	@DeleteMapping("/linkdelete")
	public void deleteLinkInfo(@RequestBody LinkReq link) throws Exception {
		linkService.deleteLink(link);
	}

	/* Swap Link */
	@PostMapping("/linkswap")
	public void swapLink(@RequestBody LinkSwapReq linkSwapReq) throws Exception {
		linkService.swapLink(linkSwapReq);
	}
}
