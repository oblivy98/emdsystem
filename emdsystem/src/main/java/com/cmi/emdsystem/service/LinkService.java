package com.cmi.emdsystem.service;

import java.util.List;

import com.cmi.emdsystem.model.Link;
import com.cmi.emdsystem.request.LinkReq;
import com.cmi.emdsystem.request.LinkSaveReq;
import com.cmi.emdsystem.request.LinkSwapReq;
import com.cmi.emdsystem.response.LinkRsp;

public interface LinkService {

	public Integer saveLinkInfo(LinkSaveReq link) throws Exception;

	public Integer editLinkInfo(Link link) throws Exception;

	public List<LinkRsp> findByUserId(Integer userId) throws Exception;

	public Integer countRecordByPCId(String serialNumber, String modelNumber);

	public Link findLinkByPCId(String serialNumber, String modelNumber) throws Exception;

	public Integer swapLink(LinkSwapReq linkSwapReq) throws Exception;

	public Integer deleteLink(LinkReq linkReq) throws Exception;
}
