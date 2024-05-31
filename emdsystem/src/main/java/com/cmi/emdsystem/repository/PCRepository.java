package com.cmi.emdsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmi.emdsystem.model.PC;
import com.cmi.emdsystem.model.PCId;

public interface PCRepository extends JpaRepository<PC, PCId> {

	// public List<PC> findBylinkPcIsNull();
	// public List<PC> findBySerialNumberAndModelNumber(String serialNumber, String
	// modelNumber);
	PC findByPcId(PCId pcId);

	List<PC> findBylinkPcNotNull();

	List<PC> findBylinkPcNull();

	public Integer countByPcIdSerialNumberAndPcIdModelNumber(String serialNumber, String modelNumber);
}
