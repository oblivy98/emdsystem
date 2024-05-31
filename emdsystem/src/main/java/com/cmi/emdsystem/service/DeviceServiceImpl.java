package com.cmi.emdsystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmi.emdsystem.model.DeviceInformation;
import com.cmi.emdsystem.model.DeviceInformationId;
import com.cmi.emdsystem.model.PC;
import com.cmi.emdsystem.model.PCId;
import com.cmi.emdsystem.repository.DeviceRepository;
import com.cmi.emdsystem.repository.PCRepository;
import com.cmi.emdsystem.request.DeviceReq;
import com.cmi.emdsystem.response.DeviceRsp;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private PCRepository pcRepository;

	@Override
	public List<DeviceRsp> findBypcId(String serialNumber, String modelNumber) {

		List<DeviceInformation> recordOfDevice = new ArrayList<>();
		recordOfDevice = deviceRepository.findByDeviceIdSerialNumberAndDeviceIdModelNumber(serialNumber, modelNumber);

		Collection<DeviceRsp> deviceRsp = new ArrayList<>();
		for (DeviceInformation devices : recordOfDevice) {
			DeviceRsp deviceRow = new DeviceRsp();
			deviceRow.setSerialNumber(devices.getDeviceId().getSerialNumber());
			deviceRow.setModelNumber(devices.getDeviceId().getModelNumber());
			deviceRow.setUpdateDay(devices.getDeviceId().getUpdateDay());
			deviceRow.setLocOfUse(devices.getLocOfUse());
			deviceRow.setInternalConnection(devices.getInternalConnection());
			deviceRow.setExternalItems(devices.getExternalItems());
			deviceRow.setClassification(devices.getClassification());
			deviceRow.setOfficeSendDate(devices.getOfficeSendDate());
			deviceRow.setOfficeReturnDate(devices.getOfficeReturnDate());
			deviceRow.setUserHistory(devices.getUserHistory());
			deviceRow.setApplicationDate(devices.getApplicationDate());
			deviceRow.setWfhAgreement(devices.getWfhAgreement());
			deviceRow.setWfhDate(devices.getWfhDate());
			deviceRow.setRemarks(devices.getRemarks());

			deviceRsp.add(deviceRow);
		}
		return (List<DeviceRsp>) deviceRsp;
	}

	@Override
	public DeviceInformation findByRecord(String serialNumber, String modelNumber, LocalDate updateDay) {

		DeviceInformationId deviceId = new DeviceInformationId();
		deviceId.setSerialNumber(serialNumber);
		deviceId.setModelNumber(modelNumber);
		deviceId.setUpdateDay(updateDay);
		return null;
	}

	@Override
	public Integer saveDeviceInfo(DeviceReq deviceReq) {

		// set Object
		PC pc = new PC();
		PCId pcId = new PCId();
		DeviceInformation device = new DeviceInformation();
		DeviceInformationId deviceId = new DeviceInformationId();

		// Fetch PC Details
		pcId.setSerialNumber(deviceReq.getSerialNumber());
		pcId.setModelNumber(deviceReq.getModelNumber());
		pc = pcRepository.findByPcId(pcId);

		// set DeviceId
		deviceId.setSerialNumber(deviceReq.getSerialNumber());
		deviceId.setModelNumber(deviceReq.getModelNumber());
		deviceId.setUpdateDay(deviceReq.getUpdateDay());

		// set Device
		device.setDeviceId(deviceId);
		device.setPcDevice(pc);

		device.setLocOfUse(deviceReq.getLocOfUse());
		device.setInternalConnection(deviceReq.getInternalConnection());
		device.setExternalItems(deviceReq.getExternalItems());
		device.setClassification(deviceReq.getClassification());
		device.setOfficeSendDate(deviceReq.getOfficeSendDate());
		device.setOfficeReturnDate(deviceReq.getOfficeReturnDate());
		device.setUserHistory(deviceReq.getUserHistory());
		device.setApplicationDate(deviceReq.getApplicationDate());
		device.setWfhAgreement(deviceReq.getWfhAgreement());
		device.setWfhDate(deviceReq.getWfhDate());
		device.setRemarks(deviceReq.getRemarks());

		deviceRepository.save(device);
		return 0;
	}

	@Override
	public Integer editDeviceInfo(DeviceReq deviceReq) {

		DeviceInformation device = new DeviceInformation();
		DeviceInformationId deviceId = new DeviceInformationId();
		// set DeviceId
		deviceId.setSerialNumber(deviceReq.getSerialNumber());
		deviceId.setModelNumber(deviceReq.getModelNumber());
		deviceId.setUpdateDay(deviceReq.getUpdateDay());

		// set Device
		device.setDeviceId(deviceId);

		device.setLocOfUse(deviceReq.getLocOfUse());
		device.setInternalConnection(deviceReq.getInternalConnection());
		device.setExternalItems(deviceReq.getExternalItems());
		device.setClassification(deviceReq.getClassification());
		device.setOfficeSendDate(deviceReq.getOfficeSendDate());
		device.setOfficeReturnDate(deviceReq.getOfficeReturnDate());
		device.setUserHistory(deviceReq.getUserHistory());
		device.setApplicationDate(deviceReq.getApplicationDate());
		device.setWfhAgreement(deviceReq.getWfhAgreement());
		device.setWfhDate(deviceReq.getWfhDate());
		device.setRemarks(deviceReq.getRemarks());

		deviceRepository.save(device);
		return 0;
	}

	@Override
	public Integer deleteDeviceInfo(DeviceReq deviceReq) {
		DeviceInformation device = new DeviceInformation();
		DeviceInformationId deviceId = new DeviceInformationId();

		deviceId.setSerialNumber(deviceReq.getSerialNumber());
		deviceId.setModelNumber(deviceReq.getModelNumber());
		deviceId.setUpdateDay(deviceReq.getUpdateDay());

		device.setDeviceId(deviceId);
		deviceRepository.delete(device);
		return 0;
	}

	@Override
	public List<DeviceRsp> findDeviceWithRecord() {

		List<DeviceInformation> deviceList = new ArrayList<>();
		deviceList = deviceRepository.findDistinctBypcDeviceNotNull();

		Collection<DeviceRsp> deviceRsp = new ArrayList<>();
		for (DeviceInformation devices : deviceList) {
			DeviceRsp deviceRow = new DeviceRsp();
			deviceRow.setSerialNumber(devices.getDeviceId().getSerialNumber());
			deviceRow.setModelNumber(devices.getDeviceId().getModelNumber());

			deviceRow.setUpdateDay(devices.getDeviceId().getUpdateDay());
			deviceRow.setLocOfUse(devices.getLocOfUse());
			deviceRow.setInternalConnection(devices.getInternalConnection());
			deviceRow.setExternalItems(devices.getExternalItems());
			deviceRow.setClassification(devices.getClassification());
			deviceRow.setOfficeSendDate(devices.getOfficeSendDate());
			deviceRow.setOfficeReturnDate(devices.getOfficeReturnDate());
			deviceRow.setUserHistory(devices.getUserHistory());
			deviceRow.setApplicationDate(devices.getApplicationDate());
			deviceRow.setWfhAgreement(devices.getWfhAgreement());
			deviceRow.setWfhDate(devices.getWfhDate());
			deviceRow.setRemarks(devices.getRemarks());

			deviceRsp.add(deviceRow);
		}

		return (List<DeviceRsp>) deviceRsp;
	}

	@Override
	public DeviceInformation findByLatestRecord(String serialNumber, String modelNumber) {
		return deviceRepository.findFirstByDeviceIdSerialNumberAndDeviceIdModelNumberOrderByDeviceIdUpdateDayDesc(
				serialNumber, modelNumber);
	}
}
