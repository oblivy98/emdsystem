package com.cmi.emdsystem.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmi.emdsystem.model.Link;
import com.cmi.emdsystem.model.PC;
import com.cmi.emdsystem.model.PCId;
import com.cmi.emdsystem.repository.LinkRepository;
import com.cmi.emdsystem.repository.PCRepository;
import com.cmi.emdsystem.request.LinkReq;
import com.cmi.emdsystem.request.LinkSaveReq;
import com.cmi.emdsystem.request.LinkSwapReq;
import com.cmi.emdsystem.response.LinkRsp;

@Service
public class LinkServiceImpl implements LinkService {

	@Autowired
	private LinkRepository linkRepository;
	@Autowired
	private PCRepository pcRepository;

	@Override
	public Integer saveLinkInfo(LinkSaveReq linkReq) throws Exception {

		Link linkSave = new Link();
		PCId linkId = new PCId();
		PC pc = new PC();

		linkId.setSerialNumber(linkReq.getSerialNumber());
		linkId.setModelNumber(linkReq.getModelNumber());

		pc = pcRepository.findByPcId(linkId);

		linkSave.setPcLink(pc);
		linkSave.setLinkId(linkId);
		linkSave.setUserId(linkReq.getUserId());
		linkSave.setActualUser(linkReq.getActualUser());
		linkRepository.save(linkSave);
		return 0;
	}

	@Override
	public Integer editLinkInfo(Link link) throws Exception {

		linkRepository.save(link);
		return 0;
	}

	@Override
	public Link findLinkByPCId(String serialNumber, String modelNumber) throws Exception {

		PCId linkId = new PCId();
		linkId.setSerialNumber(serialNumber);
		linkId.setModelNumber(modelNumber);

		Optional<Link> link = linkRepository.findById(linkId);

		if (link.isPresent()) {
			return link.get();
		}
		throw new Exception("No link information available");
	}

	@Override
	public List<LinkRsp> findByUserId(Integer userId) throws Exception {

		List<Link> linkList = new ArrayList<>();
		linkList = linkRepository.findByUserId(userId);

		Collection<LinkRsp> linkRsp = new ArrayList<>();
		for (Link links : linkList) {
			LinkRsp linkRow = new LinkRsp();
			linkRow.setSerialNumber(links.getLinkId().getSerialNumber());
			linkRow.setModelNumber(links.getLinkId().getModelNumber());
			linkRow.setUserId(links.getUserId());
			linkRow.setActualUser(links.getActualUser());

			linkRsp.add(linkRow);
		}

		return (List<LinkRsp>) linkRsp;
	}

	@Override
	public Integer swapLink(LinkSwapReq linkSwapReq) {

		/* Delete current record */
		PCId linkId = new PCId();
		linkId.setSerialNumber(linkSwapReq.getOldSerialNumber());
		linkId.setModelNumber(linkSwapReq.getOldModelNumber());
		linkRepository.deleteById(linkId);

		Link link = new Link();
		linkId.setSerialNumber(linkSwapReq.getSerialNumber());
		linkId.setModelNumber(linkSwapReq.getModelNumber());
		link.setUserId(linkSwapReq.getUserId());
		link.setActualUser(linkSwapReq.getActualUser());

		// set pcLink
		PC pc = new PC();
		pc = pcRepository.findByPcId(linkId);

		link.setPcLink(pc);

		/* Save Swapped link */
		linkRepository.save(link);

		return 0;
	}

	@Override
	public Integer countRecordByPCId(String serialNumber, String modelNumber) {
		PCId pcId = new PCId();
		pcId.setSerialNumber(serialNumber);
		pcId.setModelNumber(modelNumber);
		return linkRepository.countByLinkId(pcId);
	}

	@Override
	public Integer deleteLink(LinkReq linkReq) throws Exception {
		Link link = new Link();
		PCId linkId = new PCId();
		linkId.setSerialNumber(linkReq.getSerialNumber());
		linkId.setModelNumber(linkReq.getModelNumber());
		link.setLinkId(linkId);
		link.setUserId(linkReq.getUserId());
		linkRepository.delete(link);
		return 0;
	}
}
