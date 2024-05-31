package com.cmi.emdsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmi.emdsystem.model.DeviceInformation;
import com.cmi.emdsystem.model.DeviceInformationId;

public interface DeviceRepository extends JpaRepository<DeviceInformation, DeviceInformationId> {

	public List<DeviceInformation> findDistinctBypcDeviceNotNull();

	public List<DeviceInformation> findByDeviceIdSerialNumberAndDeviceIdModelNumber(String serialNumber,
			String modelNumber);

	public DeviceInformation findFirstByDeviceIdSerialNumberAndDeviceIdModelNumberOrderByDeviceIdUpdateDayDesc(
			String serialNumber, String modelNumber);
}
