package com.cmi.emdsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cmi.emdsystem.model.IP;

public interface IPRepository extends JpaRepository<IP, Integer> {

	@Query("SELECT T1 FROM IP T1 " + " LEFT JOIN PC T2 ON T1.ipCode = T2.ipCode"
			+ " WHERE T2.ipCode IS NULL AND ipAddress LIKE CONCAT('%192.168.', :ipSegment, '%')")
	public List<IP> getAvailableIP(@Param("ipSegment") String ipSegment);
	/*
	 * @Query("SELECT T1.ipCode, T1.ipAddress FROM IP AS T1 " +
	 * " LEFT JOIN PC AS T2 ON T1.ipCode = T2.ipCode" +
	 * " WHERE T2.ipCode IS NULL AND ipAddress LIKE '%192.168.:ipSegment%'")
	 */

	public IP findByIpCode(Integer IpCode);

	public IP findByIpAddress(String ipAddress);
}
