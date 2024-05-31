package com.cmi.emdsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmi.emdsystem.model.Link;
import com.cmi.emdsystem.model.PCId;

public interface LinkRepository extends JpaRepository<Link, PCId> {

	public List<Link> findByUserId(Integer userId);

	public Integer countByLinkId(PCId pcId);

}
