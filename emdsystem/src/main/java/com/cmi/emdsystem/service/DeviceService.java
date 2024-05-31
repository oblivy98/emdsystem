package com.cmi.emdsystem.service;

import java.time.LocalDate;
import java.util.List;

import com.cmi.emdsystem.model.DeviceInformation;
import com.cmi.emdsystem.request.DeviceReq;
import com.cmi.emdsystem.response.DeviceRsp;

public interface DeviceService {

	List<DeviceRsp> findDeviceWithRecord();

	List<DeviceRsp> findBypcId(String serialNumber, String modelNumber);

	DeviceInformation findByRecord(String serialNumber, String modelNumber, LocalDate updateDay);

	Integer saveDeviceInfo(DeviceReq deviceReq);

	Integer editDeviceInfo(DeviceReq deviceReq);

	Integer deleteDeviceInfo(DeviceReq deviceReq);

	DeviceInformation findByLatestRecord(String serialNumber, String modelNumber);
}
