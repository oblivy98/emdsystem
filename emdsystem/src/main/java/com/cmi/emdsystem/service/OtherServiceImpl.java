package com.cmi.emdsystem.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmi.emdsystem.model.DeviceInformation;
import com.cmi.emdsystem.model.IP;
import com.cmi.emdsystem.model.Link;
import com.cmi.emdsystem.model.PC;
import com.cmi.emdsystem.model.PCId;
import com.cmi.emdsystem.model.User;
import com.cmi.emdsystem.repository.IPRepository;
import com.cmi.emdsystem.repository.LinkRepository;
import com.cmi.emdsystem.repository.PCRepository;
import com.cmi.emdsystem.repository.UserRepository;
import com.cmi.emdsystem.request.DeviceReq;
import com.cmi.emdsystem.request.FileReq;
import com.cmi.emdsystem.request.FileReqPC;
import com.cmi.emdsystem.request.FileReqUser;
import com.cmi.emdsystem.request.LinkSaveReq;

@Service
public class OtherServiceImpl implements OtherService {

	@Autowired
	private PCService pcService;
	@Autowired
	private PCRepository pcRepository;
	@Autowired
	private IPService ipService;
	@Autowired
	private IPRepository ipRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LinkService linkService;
	@Autowired
	private LinkRepository linkRepository;
	@Autowired
	private DeviceService deviceService;

	private static final Logger logger = LoggerFactory.getLogger(OtherServiceImpl.class);
	// Import - Export Database

	@Override
	public void importData(FileReq fileReq) throws Exception {

		String fileInfo = fileReq.getBase64().substring(0, fileReq.getBase64().indexOf(','));

		String base64StringLiteral = fileReq.getBase64().substring(fileReq.getBase64().indexOf(',') + 1,
				fileReq.getBase64().length());
		byte[] base64String = Base64.getDecoder().decode(base64StringLiteral.getBytes("UTF-8"));
		InputStream stream = new ByteArrayInputStream(base64String);

		// opening workbook
		XSSFWorkbook wb = new XSSFWorkbook(stream);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row = sheet.getRow(3);
		Integer ctr = 6;

		// Checks file imported before inserting to the database
		String check1 = "", check2 = "", check3 = "", check4 = "";
		if (row.getCell(11) != null && row.getCell(12) != null && row.getCell(2) != null && row.getCell(3) != null) {
			check1 = row.getCell(11).getStringCellValue();
			check1 = check1.replaceAll("\\\\r|\\\\n", "");
			check2 = row.getCell(12).getStringCellValue();
			check2 = check2.replaceAll("(\\\\r|\\\\n|\\\\t)", " ");
			check3 = row.getCell(2).getStringCellValue();
			check3 = check3.replaceAll("(\\\\r|\\\\n|\\\\t)", " ");
			check4 = row.getCell(3).getStringCellValue();
			check4 = check4.replaceAll("(\\\\r|\\\\n|\\\\t)", " ");
		}

		row = sheet.getRow(6);
		while (row != null) {

			if (!row.getCell(6).getStringCellValue().equals("") && !row.getCell(7).getStringCellValue().equals("")) {

				importPCData(row);

				// Delay Process to allow PC Information to be inserted successfully first
				Thread.sleep(50);

				// Check if there is a user for User table and Link Table
				if (row.getCell(12) != null) {
					importUserData(row);
				}

				// Check if there is a Device record available
				if (row.getCell(45) != null) {
					System.out.println("Sending Date Detected");
					importDeviceData(row);
				}
			}
			ctr++;
			row = sheet.getRow(ctr);
		}
	}

	private void importPCData(XSSFRow row) throws Exception {
		PC pc = new PC();
		PCId pcId = new PCId();
		IP ip = new IP();

		// Check if there is Serial Number and Model Number. when missing, don't insert
		if (row.getCell(6) != null && row.getCell(7) != null) {

			pcId.setModelNumber(row.getCell(6).getStringCellValue());
			pcId.setSerialNumber(row.getCell(7).getStringCellValue());
			pc.setPcId(pcId);
			pc.setTypeOfPc(2);
			if (row.getCell(2) != null) {
				if (row.getCell(2).getStringCellValue().equals("ノートPC")) {
					pc.setTypeOfPc(1);
				}
			}

			pc.setPcDetails(2);
			if (row.getCell(3) != null) {
				if (row.getCell(3).getStringCellValue().equals("A4")) {
					pc.setPcDetails(1);
				}
			}

			if (row.getCell(4) != null) {
				pc.setManufacturer(row.getCell(4).getStringCellValue());
			}

			if (row.getCell(5) != null) {
				pc.setProductName(row.getCell(5).getStringCellValue());
			}

			if (row.getCell(9) != null) {
				pc.setWirelessMac(row.getCell(9).getStringCellValue());
			}

			if (row.getCell(10) != null) {
				pc.setWiredMac(row.getCell(10).getStringCellValue());
			}

			if (row.getCell(13) != null) {
				pc.setLocOfUse(row.getCell(13).getStringCellValue());
			}

			pc.setApplication(8);
			if (row.getCell(14) != null) {
				if (row.getCell(14).getStringCellValue().equals("個人使用PC")) {
					pc.setApplication(0);
				} else if (row.getCell(14).getStringCellValue().equals("共有使用PC")) {
					pc.setApplication(1);
				} else if (row.getCell(14).getStringCellValue().equals("開発サーバ")) {
					pc.setApplication(2);
				} else if (row.getCell(14).getStringCellValue().equals("管理サーバ")) {
					pc.setApplication(3);
				} else if (row.getCell(14).getStringCellValue().equals("顔認証PC")) {
					pc.setApplication(4);
				} else if (row.getCell(14).getStringCellValue().equals("経理PC")) {
					pc.setApplication(5);
				} else if (row.getCell(14).getStringCellValue().equals("その他")) {
					pc.setApplication(6);
				} else if (row.getCell(14).getStringCellValue().equals("客先貸与PC")) {
					pc.setApplication(7);
				}
			}
			if (row.getCell(16) != null) {
				pc.setInternalSegment(row.getCell(16).getStringCellValue());
			}

			pc.setIpCode(99999);
			if (row.getCell(17) != null) {
				if (!row.getCell(17).getStringCellValue().equals("")
						&& !row.getCell(17).getStringCellValue().equals("－")
						&& row.getCell(17).getStringCellValue().equals("-")) {
					ip = ipRepository.findByIpAddress(row.getCell(17).getStringCellValue());

					if (ip != null) {
						pc.setIpCode(ip.getIpCode());
					}

				}
			}

			pc.setWifiReg(1);
			try {
				if (row.getCell(18).getStringCellValue().equals("○")
						|| row.getCell(18).getStringCellValue().equals("●")) {
					pc.setWifiReg(0);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			pc.setOperatingSystem(3);
			if (row.getCell(22) != null) {
				String os = row.getCell(22).getStringCellValue();
				os.replaceAll("\\\\r|\\\\n|", "").replaceAll(" ", "");
				if (os.equals("Windows7")) {
					pc.setOperatingSystem(0);
				} else if (os.equals("Windows10")) {
					pc.setOperatingSystem(1);
				} else if (os.equals("Windows10Pro") || os.equals("Windows10Pro(64bit)")) {
					pc.setOperatingSystem(2);
				}
			}

			// Microsoft Office
			pc.setMicrosoftOffice(5);
			if (row.getCell(23) != null) {
				pc.setMicrosoftOffice(0);
			} else if (row.getCell(24) != null) {
				pc.setMicrosoftOffice(1);
			} else if (row.getCell(25) != null) {
				pc.setMicrosoftOffice(2);
			} else if (row.getCell(26) != null) {
				pc.setMicrosoftOffice(3);
			} else if (row.getCell(27) != null) {
				pc.setMicrosoftOffice(4);
			}

			pc.setAntiVirus(2);
			if (row.getCell(29) != null) {
				pc.setAntiVirus(0);
			}
			if (row.getCell(30) != null) {
				pc.setAntiVirus(1);
			}

			pc.setPrinterSetting(1);
			try {
				if (row.getCell(32).getStringCellValue().equals("名古屋")) {
					pc.setPrinterSetting(0);
				}
			} catch (Exception e) {

			}

			// BIOS
			pc.setBiosSetting(1);
			try {
				if (row.getCell(33).getStringCellValue().equals("○")) {
					pc.setBiosSetting(0);
				}
			} catch (Exception e) {

			}

			// Bitlocker
			pc.setBitLocker(1);
			if (row.getCell(34).getStringCellValue().equals("○")) {
				pc.setBitLocker(0);
			}

			// ScreenSaver
			pc.setScreenSaver(1);
			if (row.getCell(35).getStringCellValue().equals("○")) {
				pc.setScreenSaver(0);
			}

			if (row.getCell(36) != null) {
				pc.setCpu(row.getCell(36).getStringCellValue());// cpu
			}

			if (row.getCell(37) != null) {
				pc.setMemory(row.getCell(37).getStringCellValue());// memory
			}

			if (row.getCell(38) != null) {
				pc.setHddssd(row.getCell(38).getStringCellValue());// hddssd
			}

			if (row.getCell(39) != null) {
				pc.setScreen(row.getCell(39).getStringCellValue());// screen
			}

			// WebCamera
			pc.setWebCamera(1);
			if (row.getCell(40).getStringCellValue().equals("○")) {
				pc.setWebCamera(0);
				;
			}

			// NFC
			pc.setNfc(1);
			if (row.getCell(41).getStringCellValue().equals("○")) {
				pc.setNfc(0);
			}

			if (row.getCell(42) != null) {
				pc.setMonitor(row.getCell(42).getStringCellValue());// monitor
			}

			if (row.getCell(43) != null) {
				pc.setAccessories(row.getCell(43).getStringCellValue());// accessories
			}

			if (row.getCell(44) != null) {
				pc.setRemarks(row.getCell(44).getStringCellValue());// remarks
			}

			if (row.getCell(47) != null) {
				// String bitLockerKey = row.getCell(47).getRawValue();
				pc.setBitLockerKey(row.getCell(47).getRawValue());
				if (pc.getBitLockerKey() == null) {
					pc.setBitLockerKey("N/A");
				}
			}
			pcService.savePCInfo(pc);
		}
	}

	private void importUserData(XSSFRow row) throws Exception {

		// Check if there is a User ID before inserting to User and Link Table
		if (row.getCell(12).getNumericCellValue() >= 1 && row.getCell(12).getNumericCellValue() <= 99999) {
			User user = new User();
			LinkSaveReq link = new LinkSaveReq();

			link.setModelNumber(row.getCell(6).getStringCellValue());
			link.setSerialNumber(row.getCell(7).getStringCellValue());

			String userName = row.getCell(11).getStringCellValue();
			if (userName.indexOf("（") != -1) {
				link.setActualUser(userName.substring(userName.indexOf("（") + 1, userName.length() - 1));
				user.setUserName(userName.substring(0, userName.indexOf("（")));
			} else {
				user.setUserName(row.getCell(11).getStringCellValue());
			}
			Double userIdDouble = new Double(row.getCell(12).getNumericCellValue());

			// Save User
			user.setUserId(userIdDouble.intValue());
			user.setUserAccess(1);
			userService.saveUserInfo(user);

			// Save Link
			link.setUserId(userIdDouble.intValue());
			linkService.saveLinkInfo(link);
		}
	}

	private void importDeviceData(XSSFRow row) throws Exception {

		DeviceReq device = new DeviceReq();
		LocalDate updateDay = LocalDate.now();

		device.setModelNumber(row.getCell(6).getStringCellValue());
		device.setSerialNumber(row.getCell(7).getStringCellValue());
		device.setUpdateDay(updateDay);

		if (row.getCell(15) != null) {
			device.setUserHistory(row.getCell(15).getStringCellValue());
		}

		device.setExternalItems(1);
		if (row.getCell(19).getStringCellValue() == "○") {
			device.setExternalItems(0);
		}

		try {
			if (row.getCell(20) != null && !row.getCell(20).getStringCellValue().equals("")) {
				LocalDate applicationDate = row.getCell(20).getDateCellValue().toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate();
				device.setApplicationDate(applicationDate);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		device.setWfhAgreement(1);
		if (row.getCell(21) != null) {
			if (row.getCell(21).getStringCellValue().equals("○")) {
				device.setWfhAgreement(0);
			}
		}

		// Send Date
		try {
			if (row.getCell(45).getDateCellValue() != null) {
				LocalDate sendDate = row.getCell(45).getDateCellValue().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				System.out.println(sendDate);
				device.setOfficeSendDate(sendDate);
			}
		} catch (Exception e) {

		}

		// Return date
		try {
			if (row.getCell(46).getDateCellValue() != null) {
				LocalDate returnDate = row.getCell(46).getDateCellValue().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				device.setOfficeReturnDate(returnDate);
			}
		} catch (Exception e) {

		}
		deviceService.saveDeviceInfo(device);
	}

	@Override
	public FileReq exportData(FileReq file) throws Exception {

		// Define Variable
		int rowSetCtr = 6;
		// Excel Logic

		// Set Excel object
		XSSFWorkbook wb = new XSSFWorkbook();

		// Headers
		// Sheet Initialize
		XSSFSheet sheet = wb.createSheet("機器一覧");
		XSSFSheet sheet2 = wb.createSheet("BitLocker回復キー一覧");

		// Design Cell Header
		// merge Header Cells
		styleWorkbook(wb, sheet, sheet2);
		workbookHeaderSheet2(sheet2);

		// Fetch PC first
		List<PC> listPc = new ArrayList<>();

		listPc = pcService.findAll();
		for (PC pcInfo : listPc) {

			// Create object
			IP ip = new IP();
			Link link = new Link();
			User user = new User();
			DeviceInformation device = new DeviceInformation();

			// defineVariable
			Integer linkctr = 0;

			// get IP Address
			ip = ipService.getIp(pcInfo.getIpCode());

			// Get Device Records
			device = deviceService.findByLatestRecord(pcInfo.getPcId().getSerialNumber(),
					pcInfo.getPcId().getModelNumber());

			// check if there is a record
			linkctr = linkService.countRecordByPCId(pcInfo.getPcId().getSerialNumber(),
					pcInfo.getPcId().getModelNumber());

			if (linkctr != 0) {

				// Get Link if PC is Linked to User
				link = linkService.findLinkByPCId(pcInfo.getPcId().getSerialNumber(),
						pcInfo.getPcId().getModelNumber());

				// Get User Information
				user = userService.findUserByUserId(link.getUserId());
			}

			// Set data to cells. rowSetCtr for row indentation
			XSSFRow rowData = sheet.createRow(rowSetCtr);

			// Function call for inserting all cell value for sheet 1
			insertSheet1(pcInfo, ip, device, link, user, rowData, rowSetCtr, linkctr);

			// Function call for inserting all cell value for sheet 2
			// Setup 2nd Sheet BitLockerKey
			XSSFRow rowDataSheet2 = sheet2.createRow(rowSetCtr - 5);
			insertSheet2(pcInfo, ip, device, link, user, rowDataSheet2, rowSetCtr - 5);

			// Increment rowSetCtr
			rowSetCtr++;
		}

		// After all loop. save then close /*CHECK */
		/* Working as intended */
		FileOutputStream excelFileTest = new FileOutputStream("D:" + "/機器管理.xlsx");
		wb.write(excelFileTest);
		excelFileTest.close();

		FileReq fileReq = new FileReq();
		fileReq.setFileName("機器管理.xlsx");

		ByteArrayOutputStream byteExcel = new ByteArrayOutputStream();
		wb.write(byteExcel);
		byte[] bytes = byteExcel.toByteArray();

		String stringToBase64 = Base64.getEncoder().encodeToString(bytes);
		fileReq.setBase64(stringToBase64);
		System.out.println(fileReq.getBase64());
		return fileReq;
	}

	private void styleWorkbook(XSSFWorkbook wb, XSSFSheet sheet, XSSFSheet sheet2) {
		XSSFRow row = sheet.createRow(3);
		XSSFRow row2 = sheet.createRow(4);
		XSSFRow row3 = sheet.createRow(5);
		XSSFCell cell = row.createCell(0);
		XSSFCellStyle style = wb.createCellStyle();
		XSSFCellStyle upwardText = wb.createCellStyle();
		XSSFFont font = wb.createFont();

		// SetStyle
		font.setFontName("ＭＳ Ｐゴシック");
		font.setBold(true);

		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderRight(BorderStyle.MEDIUM);
		style.setBorderLeft(BorderStyle.MEDIUM);
		style.setFont(font);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		upwardText.setBorderBottom(BorderStyle.MEDIUM);
		upwardText.setBorderTop(BorderStyle.MEDIUM);
		upwardText.setBorderRight(BorderStyle.MEDIUM);
		upwardText.setBorderLeft(BorderStyle.MEDIUM);
		upwardText.setRotation((short) 90);

		// Merge Cells for column 0-20
		sheet.addMergedRegion(new CellRangeAddress(3, 5, 0, 0));
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("＃");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 1, 1));
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("ｽﾃｰﾀｽ");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 2, 2));
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("種別");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 3, 3));
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("詳細\\\\r\\\\n種別");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 4, 4));
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("メーカー");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 5, 5));
		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("商品名");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 6, 6));
		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("型式・型番");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 7, 7));
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("製造番号");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 8, 8));
		cell = row.createCell(8);
		cell.setCellStyle(style);
		cell.setCellValue("管理番号");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 9, 9));
		cell = row.createCell(9);
		cell.setCellStyle(style);
		cell.setCellValue("MACアドレス\\\\r\\\\n【Wireless LAN】");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 10, 10));
		cell = row.createCell(10);
		cell.setCellStyle(style);
		cell.setCellValue("MACアドレス\\\\r\\\\n【ｲｰｻｰﾈｯﾄ】");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 11, 11));
		cell = row.createCell(11);
		cell.setCellStyle(style);
		cell.setCellValue("利用者\\\\r\\\\n（実使用者等）");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 12, 12));
		cell = row.createCell(12);
		cell.setCellStyle(style);
		cell.setCellValue("利用者\\\\r\\\\n社員番号");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 13, 13));
		cell = row.createCell(13);
		cell.setCellStyle(style);
		cell.setCellValue("利用場所");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 14, 14));
		cell = row.createCell(14);
		cell.setCellStyle(style);
		cell.setCellValue("用途");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 15, 15));
		cell = row.createCell(15);
		cell.setCellStyle(style);
		cell.setCellValue("利用者履歴メモ");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 16, 16));
		cell = row.createCell(16);
		cell.setCellStyle(style);
		cell.setCellValue("社内\\\\r\\\\nセグメント");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 17, 17));
		cell = row.createCell(17);
		cell.setCellStyle(style);
		cell.setCellValue("社内IP\\\\r\\\\nアドレス");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 18, 18));
		cell = row.createCell(18);
		cell.setCellStyle(style);
		cell.setCellValue("WiFi登録");

		sheet.addMergedRegion(new CellRangeAddress(3, 5, 19, 19));
		cell = row.createCell(19);
		cell.setCellStyle(style);
		cell.setCellValue("社外持出");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 20, 20));
		cell = row2.createCell(20);
		cell.setCellStyle(style);
		cell.setCellValue("持出\\\\r\\\\n申請日");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 21, 21));
		cell = row2.createCell(21);
		cell.setCellStyle(style);
		cell.setCellValue("在宅勤務\\\\r\\\\n誓約書");

		sheet.addMergedRegion(new CellRangeAddress(3, 3, 22, 43));
		cell = row.createCell(22);
		cell.setCellStyle(style);
		cell.setCellValue("ＰＣ設定");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 22, 22));
		cell = row2.createCell(22);
		cell.setCellStyle(style);
		cell.setCellValue("ＯＳ");

		sheet.addMergedRegion(new CellRangeAddress(4, 4, 23, 28));
		cell = row2.createCell(23);
		cell.setCellStyle(style);
		cell.setCellValue("MS　Ｏｆｆｉｃｅ");

		cell = row3.createCell(23);
		cell.setCellStyle(upwardText);
		cell.setCellValue("Microsoft365");

		cell = row3.createCell(24);
		cell.setCellStyle(upwardText);
		cell.setCellValue("Office2016");

		cell = row3.createCell(25);
		cell.setCellStyle(upwardText);
		cell.setCellValue("Office2013");

		cell = row3.createCell(26);
		cell.setCellStyle(upwardText);
		cell.setCellValue("Office2010");

		cell = row3.createCell(27);
		cell.setCellStyle(upwardText);
		cell.setCellValue("Office2007");

		cell = row3.createCell(28);
		cell.setCellStyle(upwardText);
		cell.setCellValue("OfficeforMac");

		sheet.addMergedRegion(new CellRangeAddress(4, 4, 29, 31));
		cell = row2.createCell(29);
		cell.setCellStyle(style);
		cell.setCellValue("ウィルス対策");

		cell = row3.createCell(29);
		cell.setCellStyle(upwardText);
		cell.setCellValue("ウィルスバスター");

		cell = row3.createCell(30);
		cell.setCellStyle(upwardText);
		cell.setCellValue("Symantec Endpoint Protection");

		cell = row3.createCell(31);
		cell.setCellStyle(upwardText);
		cell.setCellValue("ＭｃＡｆｅｅ　virusScan");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 32, 32));
		cell = row2.createCell(32);
		cell.setCellStyle(style);
		cell.setCellValue("プリンタ\\\\r\\\\n設定");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 33, 33));
		cell = row2.createCell(33);
		cell.setCellStyle(style);
		cell.setCellValue("BIOS\\\\r\\\\nパスワード");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 34, 34));
		cell = row2.createCell(34);
		cell.setCellStyle(style);
		cell.setCellValue("Bit\\\\r\\\\nLocker");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 35, 35));
		cell = row2.createCell(35);
		cell.setCellStyle(style);
		cell.setCellValue("ｽｸﾘｰﾝ\\\\r\\\\nｾｰﾊﾞｰ");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 36, 36));
		cell = row2.createCell(36);
		cell.setCellStyle(style);
		cell.setCellValue("CPU");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 37, 37));
		cell = row2.createCell(37);
		cell.setCellStyle(style);
		cell.setCellValue("メモリ\\\\r\\\\n標準＋増設");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 38, 38));
		cell = row2.createCell(38);
		cell.setCellStyle(style);
		cell.setCellValue("HDD/SSD");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 39, 39));
		cell = row2.createCell(39);
		cell.setCellStyle(style);
		cell.setCellValue("画面");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 40, 40));
		cell = row2.createCell(40);
		cell.setCellStyle(style);
		cell.setCellValue("Web\\\\r\\\\nｶﾒﾗ");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 41, 41));
		cell = row2.createCell(41);
		cell.setCellStyle(style);
		cell.setCellValue("NFC");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 42, 42));
		cell = row2.createCell(42);
		cell.setCellStyle(style);
		cell.setCellValue("外部\\\\r\\\\nﾃﾞｨｽﾌﾟﾚｲ");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 43, 43));
		cell = row2.createCell(43);
		cell.setCellStyle(style);
		cell.setCellValue("付属品等\\\\r\\\\n\\\\r\\\\n");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 44, 44));
		cell = row2.createCell(44);
		cell.setCellStyle(style);
		cell.setCellValue("備　　　考");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 45, 45));
		cell = row2.createCell(45);
		cell.setCellStyle(style);
		cell.setCellValue("本社\\\\r\\\\n送付日");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 46, 46));
		cell = row2.createCell(46);
		cell.setCellStyle(style);
		cell.setCellValue("本社\\\\r\\\\n返却日");

		sheet.addMergedRegion(new CellRangeAddress(4, 5, 47, 47));
		cell = row2.createCell(47);
		cell.setCellStyle(style);
		cell.setCellValue("BitLocker回復キー");
	}

	private void insertSheet2(PC pcInfo, IP ip, DeviceInformation device, Link link, User user, XSSFRow rowDataSheet2,
			int rowSetCtr) {
		rowDataSheet2.createCell(0).setCellValue(rowSetCtr);
		rowDataSheet2.createCell(1).setCellValue(pcInfo.getPcId().getSerialNumber());
		rowDataSheet2.createCell(2).setCellValue("");
		rowDataSheet2.createCell(3).setCellValue("");
		rowDataSheet2.createCell(4).setCellValue(pcInfo.getBitLockerKey());

	}

	private void insertSheet1(PC pcInfo, IP ip, DeviceInformation device, Link link, User user, XSSFRow rowData,
			int rowSetCtr, Integer linkctr) {

		rowData.createCell(0).setCellValue(rowSetCtr - 5);

		insertPCRow(pcInfo, rowData, rowSetCtr);

		if (device != null) {
			insertDeviceRow(device, rowData, rowSetCtr);
		}

		if (linkctr != 0) {
			insertUserRow(user, link, rowData, rowSetCtr);
		}

		rowData.createCell(17).setCellValue(ip.getIpAddress());

		// Formula BitLocker
		rowData.createCell(47).setCellFormula("VLOOKUP(H" + rowSetCtr + ",BitLocker回復キー一覧!$B$1:$F$44,5,FALSE)");

	}

	private void insertUserRow(User user, Link link, XSSFRow rowData, int rowSetCtr) {
		String userActualUser = user.getUserName();
		if (link.getActualUser() != null) {
			userActualUser = userActualUser + "（" + link.getActualUser() + "）";
		}
		rowData.createCell(11).setCellValue(userActualUser);
		if (user.getUserId() != 0) {
			rowData.createCell(12).setCellValue(user.getUserId());
		}
	}

	private void insertDeviceRow(DeviceInformation device, XSSFRow rowData, int rowSetCtr) {
		if (device.getUserHistory() != null) {
			rowData.createCell(15).setCellValue(device.getUserHistory());
		}

		String export = "-";
		if (device.getExternalItems() == 0) {
			export = "○";
		}
		rowData.createCell(19).setCellValue(export);

		// 21
		if (device.getApplicationDate() != null) {
			rowData.createCell(20).setCellValue(device.getApplicationDate());
		}

		String wfhPledge = "-";
		if (device.getWfhAgreement() == 0) {
			wfhPledge = "○";
		}
		rowData.createCell(21).setCellValue(wfhPledge);

		// Send Date
		if (device.getOfficeSendDate() != null) {
			rowData.createCell(45).setCellValue(device.getOfficeSendDate());
		}
		// Return Date
		if (device.getOfficeReturnDate() != null) {
			rowData.createCell(46).setCellValue(device.getOfficeReturnDate());
		}
	}

	private void insertPCRow(PC pcInfo, XSSFRow rowData, int rowSetCtr) {

		if (pcInfo.getTypeOfPc() == 1) {
			rowData.createCell(2).setCellValue("ノートPC");
		} else {
			rowData.createCell(2).setCellValue("デスクトップPC");
		}

		if (pcInfo.getPcDetails() == 1) {
			rowData.createCell(3).setCellValue("A4");
		} else {
			rowData.createCell(3).setCellValue("B5モバイル");
		}

		rowData.createCell(4).setCellValue(pcInfo.getManufacturer());
		rowData.createCell(5).setCellValue(pcInfo.getProductName());
		rowData.createCell(6).setCellValue(pcInfo.getPcId().getModelNumber());
		rowData.createCell(7).setCellValue(pcInfo.getPcId().getSerialNumber());

		if (pcInfo.getWirelessMac() != null) {
			rowData.createCell(9).setCellValue(pcInfo.getWirelessMac());
		}

		// 11
		if (pcInfo.getWiredMac() != null) {
			rowData.createCell(10).setCellValue(pcInfo.getWiredMac());
		}

		if (pcInfo.getLocOfUse() != null) {
			rowData.createCell(13).setCellValue(pcInfo.getLocOfUse());
		}

		String application = "";
		if (pcInfo.getApplication() == 0) {
			application = "個人使用PC";
		} else if (pcInfo.getApplication() == 1) {
			application = "共有使用PC";
		} else if (pcInfo.getApplication() == 2) {
			application = "開発サーバ";
		} else if (pcInfo.getApplication() == 3) {
			application = "管理サーバ";
		} else if (pcInfo.getApplication() == 4) {
			application = "顔認証PC";
		} else if (pcInfo.getApplication() == 5) {
			application = "経理PC";
		} else if (pcInfo.getApplication() == 6) {
			application = "その他";
		} else if (pcInfo.getApplication() == 7) {
			application = "客先貸与PC";
		} else if (pcInfo.getApplication() == 8) {
			application = "シンクラ端末";
		}
		rowData.createCell(14).setCellValue(application);

		if (pcInfo.getInternalSegment() != null) {
			rowData.createCell(16).setCellValue(pcInfo.getInternalSegment());
		}

		String wifiReg = "-";
		if (pcInfo.getWifiReg() == 0) {
			wifiReg = "○";
		}
		rowData.createCell(18).setCellValue(wifiReg);

		String operatingSystem = "";
		if (pcInfo.getOperatingSystem() == 0) {
			operatingSystem = "Windows 7";
		} else if (pcInfo.getOperatingSystem() == 1) {
			operatingSystem = "Windows 10";
		} else if (pcInfo.getOperatingSystem() == 2) {
			operatingSystem = "Windows 10 Pro";
		} else if (pcInfo.getOperatingSystem() == 3) {
			operatingSystem = "Windows 11";
		}
		rowData.createCell(22).setCellValue(operatingSystem);

		// Microsoft Office
		if (pcInfo.getMicrosoftOffice() == 0) {
			rowData.createCell(23).setCellValue("○");
		} else if (pcInfo.getMicrosoftOffice() == 1) {
			rowData.createCell(24).setCellValue("○");
		} else if (pcInfo.getMicrosoftOffice() == 2) {
			rowData.createCell(25).setCellValue("○");
		} else if (pcInfo.getMicrosoftOffice() == 3) {
			rowData.createCell(26).setCellValue("○");
		} else if (pcInfo.getMicrosoftOffice() == 4) {
			rowData.createCell(27).setCellValue("○");
		} else if (pcInfo.getMicrosoftOffice() == 5) {
			rowData.createCell(28).setCellValue("○");
		}

		// Security
		if (pcInfo.getAntiVirus() == 0) {
			rowData.createCell(29).setCellValue("○");
		} else if (pcInfo.getAntiVirus() == 1) {
			rowData.createCell(30).setCellValue("○");
		} else if (pcInfo.getAntiVirus() == 2) {
			rowData.createCell(31).setCellValue("○");
		}

		// Printer Setting
		String printerSetting = "-";
		if (pcInfo.getPrinterSetting() == 0) {
			printerSetting = "名古屋";
		}
		rowData.createCell(32).setCellValue(printerSetting);

		// BIOS
		String bios = "-";
		if (pcInfo.getBiosSetting() == 0) {
			bios = "○";
		}
		rowData.createCell(33).setCellValue(bios);

		// Bitlocker
		String bitLocker = "-";
		if (pcInfo.getBitLocker() == 0) {
			bitLocker = "○";
		}
		rowData.createCell(34).setCellValue(bitLocker);

		// ScreenSaver
		String screenSaver = "-";
		if (pcInfo.getScreenSaver() == 0) {
			screenSaver = "○";
		}
		rowData.createCell(35).setCellValue(screenSaver);

		if (pcInfo.getCpu() != null) {
			rowData.createCell(36).setCellValue(pcInfo.getCpu());// cpu
		}

		if (pcInfo.getMemory() != null) {
			rowData.createCell(37).setCellValue(pcInfo.getMemory());// Ram
		}

		if (pcInfo.getHddssd() != null) {
			rowData.createCell(38).setCellValue(pcInfo.getHddssd());// Storage
		}

		if (pcInfo.getScreen() != null) {
			rowData.createCell(39).setCellValue(pcInfo.getScreen());// Display Resolution
		}

		// WebCamera
		String webCamera = "-";
		if (pcInfo.getWebCamera() == 0) {
			webCamera = "○";
		}
		rowData.createCell(40).setCellValue(webCamera);
		// NFC
		String nfc = "-";
		if (pcInfo.getWebCamera() == 0) {
			nfc = "○";
		}
		rowData.createCell(41).setCellValue(nfc);

		if (pcInfo.getMonitor() != null) {
			rowData.createCell(42).setCellValue(pcInfo.getMonitor());
		}
		if (pcInfo.getAccessories() != null) {
			rowData.createCell(43).setCellValue(pcInfo.getAccessories());
		}
		if (pcInfo.getRemarks() != null) {
			rowData.createCell(44).setCellValue(pcInfo.getRemarks());
		}

	}

	private void workbookHeaderSheet2(XSSFSheet sheet2) {
		XSSFRow headerRowSheet2 = sheet2.createRow(0);
		headerRowSheet2.createCell(0).setCellValue("＃");
		headerRowSheet2.createCell(1).setCellValue("製造番号");
		headerRowSheet2.createCell(2).setCellValue("作成日");
		headerRowSheet2.createCell(3).setCellValue("時刻");
		headerRowSheet2.createCell(4).setCellValue("サイズ");
		headerRowSheet2.createCell(5).setCellValue("ファイル名");
	}

	// Import - Export Database

	/* ============================ */

	// Import - Export PC

	public void importCSVPC(FileReq file) throws Exception {

		PC pc = new PC();
		PCId pcId = new PCId();

		String fileInfo = file.getBase64().substring(0, file.getBase64().indexOf(','));
		String base64StringLiteral = file.getBase64().substring(file.getBase64().indexOf(',') + 1,
				file.getBase64().length());

		byte[] base64String = Base64.getDecoder().decode(base64StringLiteral.getBytes("UTF-8"));
		InputStream stream = new ByteArrayInputStream(base64String);

		String result = IOUtils.toString(stream, StandardCharsets.UTF_8);
		String[] removeNewLine = result.split("\r\n");
		String[] rs = removeNewLine[1].split(",");

		pcId.setSerialNumber(rs[0]);
		pcId.setModelNumber(rs[1]);

		pc.setPcId(pcId);
		pc.setProductName(rs[2]);
		pc.setManufacturer(rs[3]);
		pc.setTypeOfPc(Integer.parseInt(rs[4]));
		pc.setPcDetails(Integer.parseInt(rs[5]));
		pc.setApplication(Integer.parseInt(rs[6]));
		pc.setLocOfUse(rs[7]);
		pc.setInternalSegment(rs[8]);
		pc.setWifiReg(Integer.parseInt(rs[9]));

		pc.setOperatingSystem(Integer.parseInt(rs[10]));
		pc.setMicrosoftOffice(Integer.parseInt(rs[11]));
		pc.setAntiVirus(Integer.parseInt(rs[12]));
		pc.setOtherApplication(rs[13]);
		pc.setPrinterSetting(Integer.parseInt(rs[14]));
		pc.setBiosSetting(Integer.parseInt(rs[15]));
		pc.setBitLocker(Integer.parseInt(rs[16]));
		pc.setScreenSaver(Integer.parseInt(rs[17]));
		pc.setCpu(rs[18]);
		pc.setMemory(rs[19]);
		pc.setHddssd(rs[20]);
		pc.setScreen(rs[21]);
		pc.setWebCamera(Integer.parseInt(rs[22]));
		pc.setNfc(Integer.parseInt(rs[23]));
		pc.setMonitor(rs[24]);
		pc.setAccessories(rs[25]);
		pc.setBitLockerKey(rs[26]);
		pc.setRemarks(rs[27]);
		pc.setWirelessMac(rs[28]);
		pc.setWiredMac(rs[29]);
		pc.setIpCode(Integer.parseInt(rs[30]));
		// Check if User ID Exists to avoid overlapping of data
		try {
			Integer pcIdCheck = 0;
			pcIdCheck = pcRepository.countByPcIdSerialNumberAndPcIdModelNumber(pc.getPcId().getSerialNumber(),
					pc.getPcId().getModelNumber());
			if (pcIdCheck == 0) {
				pcService.savePCInfo(pc);
			} else {
				System.out.println("PC Serial Number and Model NumberExists");
			}
		} catch (Exception e) {
			pcService.savePCInfo(pc);
		}
	}

	public FileReqPC exportCSVPC(FileReqPC file) throws Exception {
		PC pc = new PC();
		PCId pcId = new PCId();
		FileReq fileReq = new FileReq();
		pcId.setSerialNumber(file.getSerialNumber());
		pcId.setModelNumber(file.getModelNumber());

		pc = pcRepository.findByPcId(pcId);

		if (pc.getPcId().getSerialNumber() != null && pc.getPcId().getModelNumber() != null) {
			// Append All Line
			String header = "Serial Number,Model Number,Product Name,Manufacturer,Type Of PC,"
					+ "PC Details, Appliation, Location of Use, Internal Segment, Wifi Registration,"
					+ "Operating System, Microsoft Office, AntiVirus, Other Application, Printer Setting,"
					+ "BIOS Setting, BitLocker, Screen Saver, CPU, Memory,"
					+ "HDD/SSD, Screen, Web Camera, NFC, Monitor,"
					+ "Accessories, BitLocker Key, Remarks, Wireless Mac, Wired Mac," + "IP Code";

			String pcInfo = pcId.getSerialNumber() + "," + pcId.getModelNumber() + "," + pc.getProductName() + ","
					+ pc.getManufacturer() + "," + pc.getTypeOfPc() + "," + pc.getPcDetails() + ","
					+ pc.getApplication() + "," + pc.getLocOfUse() + "," + pc.getInternalSegment() + ","
					+ pc.getWifiReg() + "," + pc.getOperatingSystem() + "," + pc.getMicrosoftOffice() + ","
					+ pc.getAntiVirus() + "," + pc.getOtherApplication() + "," + pc.getPrinterSetting() + ","
					+ pc.getBiosSetting() + "," + pc.getBitLocker() + "," + pc.getScreenSaver() + "," + pc.getCpu()
					+ "," + pc.getMemory() + "," + pc.getHddssd() + "," + pc.getScreen() + "," + pc.getWebCamera() + ","
					+ pc.getNfc() + "," + pc.getMonitor() + "," + pc.getAccessories() + "," + pc.getBitLockerKey() + ","
					+ pc.getRemarks() + "," + pc.getWirelessMac() + "," + pc.getWiredMac() + "," + pc.getIpCode();
			;

			// After Fetching all data. save then close /*CHECK */
			String fileName = file.getFilePath() + "/" + pcId.getSerialNumber() + " - " + pcId.getModelNumber()
					+ ".csv";
			try {
				fileReq = exportCSV(fileName, header, pcInfo);

				file.setFileName(fileReq.getFileName());
				file.setBase64(fileReq.getBase64());

				return file;
			} catch (Exception e) {
				System.out.print(e.getMessage());
				return null;
			}
		}
		return null;
	}

	// Import - Export User

	public void importCSVUser(FileReq file) throws Exception {

		User user = new User();
		String fileInfo = file.getBase64().substring(0, file.getBase64().indexOf(','));
		String base64StringLiteral = file.getBase64().substring(file.getBase64().indexOf(',') + 1,
				file.getBase64().length());

		byte[] base64String = Base64.getDecoder().decode(base64StringLiteral.getBytes("UTF-8"));
		InputStream stream = new ByteArrayInputStream(base64String);

		String result = IOUtils.toString(stream, StandardCharsets.UTF_8);
		String[] removeNewLine = result.split("\r\n");
		String[] resultSplit = removeNewLine[1].split(",");
		user.setUserId(Integer.parseInt(resultSplit[0]));
		user.setUserName(resultSplit[1]);
		user.setUserAccess(Integer.parseInt(resultSplit[2]));
		if (!resultSplit[3].equals("null")) {
			user.setEmailAddress(resultSplit[3]);
		}
		// Check if User ID Exists to avoid overlapping of data
		try {
			Integer userIdCheck = 0;
			userIdCheck = userRepository.countByUserId(user.getUserId());
			if (userIdCheck == 0) {
				userService.saveUserInfo(user);
			} else {
				System.out.println("User ID Exists");
			}
		} catch (Exception e) {
			userService.saveUserInfo(user);
		}
	}

	public FileReqUser exportCSVUser(FileReqUser file) throws Exception {
		User user = new User();
		FileReq fileReq = new FileReq();
		user = userService.findUserByUserId(file.getUserId());

		if (user.getUserId() > 0 && user.getUserId() <= 99999) {
			// Append All Line
			String header = "User ID,User Name,User Access,Email Address";

			String userInfo = user.getUserId() + "," + user.getUserName() + "," + user.getUserAccess() + ","
					+ user.getEmailAddress();

			// After Fetching all data. save then close /*CHECK */
			String fileName = file.getFilePath() + "/" + user.getUserId() + ".csv";
			try {
				fileReq = exportCSV(fileName, header, userInfo);

				file.setFileName(fileReq.getFileName());
				file.setBase64(fileReq.getBase64());

				return file;
			} catch (Exception e) {
				System.out.print(e.getMessage());
				return null;
			}
		}
		return null;
	}

	private FileReq exportCSV(String fileName, String header, String info) throws Exception {
		File newCSV = new File(fileName);
		if (!newCSV.exists()) {
			newCSV = new File(fileName);
		}

		FileReq fileReq = new FileReq();

		StringBuffer sb = new StringBuffer();
		sb.append(header);
		sb.append("\n");
		sb.append(info);

		String stringLiteral = sb.toString();
		String stringToBase64 = Base64.getEncoder().encodeToString(stringLiteral.getBytes());

		fileReq.setBase64(stringToBase64);
		return fileReq;
		/*
		 * String fileInfo = fileReq.getBase64().substring(0,
		 * fileReq.getBase64().indexOf(',')); String base64StringLiteral =
		 * fileReq.getBase64().substring(fileReq.getBase64().indexOf(',') + 1,
		 * fileReq.getBase64().length());
		 * 
		 * byte[] base64String =
		 * Base64.getDecoder().decode(base64StringLiteral.getBytes("UTF-8"));
		 * InputStream stream = new ByteArrayInputStream(base64String);
		 */
	}

}
