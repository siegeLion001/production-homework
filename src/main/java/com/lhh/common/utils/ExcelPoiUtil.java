package com.lhh.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lhh.common.exception.RRException;

/**
 * excel导入导出-poi实现
 * 
 * @author ma.zd
 * @email mazengdi@lanhaihui.net
 * @date 2016年10月31日 上午10:40:10
 */
public class ExcelPoiUtil {

	/**
	 * 集合导出excel
	 * 
	 * @param list 要导出的集合
	 * @param fieldMap 展示的字段名称
	 * @param sheetName sheet名称
	 * @param out OutputStream
	 * @param lockpass 展示的字段名称
	 * @param protectpass 保护密码
	 * @param protectMap 需要保护列的集合
	 */
	public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName,
			OutputStream out, String lockpass, String protectpass, LinkedHashMap<String, String> protectMap) {
		// 创建一个Excel文件
		XSSFWorkbook wb = new XSSFWorkbook();
		// 创建一个工作表
		Sheet sheet = wb.createSheet(sheetName);
		// 保护密码
		sheet.protectSheet(protectpass);

		wb.setRevisionsPassword(protectpass, HashAlgorithm.sha1);
		wb.setWorkbookPassword(protectpass, HashAlgorithm.sha1);
		wb.lockStructure();
		wb.lockRevision();

		// 添加表头行
		Row row = sheet.createRow(0);

		// 定义存放英文字段名和中文字段名的数组
		String[] enFields = new String[fieldMap.size()];
		String[] cnFields = new String[fieldMap.size()];

		// 填充数组
		int count = 0;
		for (Entry<String, String> entry : fieldMap.entrySet()) {
			enFields[count] = entry.getKey();
			cnFields[count] = entry.getValue();
			count++;
		}

		// 填充表头
		Cell headCell = null;
		for (int i = 0; i < cnFields.length; i++) {
			headCell = row.createCell(i);
			headCell.setCellValue(cnFields[i]);
		}

		// 添加数据内容
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);

			// 获取单个对象
			T item = list.get(i);
			for (int j = 0; j < enFields.length; j++) {
				XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
				if (protectMap.containsKey(enFields[j])) {
					style.setLocked(true);
				} else {
					style.setLocked(false);
				}
				Object objValue = ReflectUtil.getNestedProperty(item, enFields[j]);
				String fieldValue = objValue == null ? "" : objValue.toString();
				// 创建单元格，并设置值
				Cell cell = row.createCell(j);
				cell.setCellValue(fieldValue);
				cell.setCellStyle(style);
			}
		}

		try {
			OutputStream nos = new ByteArrayOutputStream();
			wb.write(nos);
			nos.close();

			POIFSFileSystem fs = new POIFSFileSystem();
			EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
			Encryptor enc = info.getEncryptor();
			enc.confirmPassword(lockpass);

			InputStream nis = parseStream(nos);
			OPCPackage opc = OPCPackage.open(nis);
			OutputStream os = enc.getDataStream(fs);
			opc.save(os);
			opc.close();

			fs.writeFilesystem(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 集合导出excel
	 * 
	 * @param fileName
	 *            导出的文件名称
	 * @param list
	 *            准备导出的结果集
	 * @param fieldMap
	 *            展示的字段名称
	 * @param sheetName
	 *            sheet页名称
	 * @param response
	 * @param lockpass
	 *            文件密码
	 * @param protectpass
	 *            保护密码
	 * @param protectMap
	 *            需要保护列的集合 guyaowu--2018年3月20日16:53:13
	 */
	public static <T> void listToExcel(String fileName, List<T> list, LinkedHashMap<String, String> fieldMap,
			String sheetName, HttpServletResponse response, String lockpass, String protectpass,
			LinkedHashMap<String, String> protectMap) {
		// 设置response头信息
		response.reset();
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // 改成输出excel文件
		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
		// 创建工作簿并发送到浏览器
		try {
			OutputStream out = response.getOutputStream();
			listToExcel(list, fieldMap, sheetName, out, lockpass, protectpass, protectMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 集合导出excel
	 * 
	 * @param list
	 * @param fieldMap 展示的字段名称
	 * @param sheetName sheet名称
	 * @param response
	 * @param lockpass 问价密码
	 * @param protectpass 保护密码
	 * @param protectMap 需要保护列的集合
	 */
	public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName,
			HttpServletResponse response, String lockpass, String protectpass,
			LinkedHashMap<String, String> protectMap) {
		// 设置默认文件名为当前时间：年月日时分秒
		String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
		// 设置response头信息
		response.reset();
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // 改成输出excel文件
		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
		// 创建工作簿并发送到浏览器
		try {
			OutputStream out = response.getOutputStream();
			listToExcel(list, fieldMap, sheetName, out, lockpass, protectpass, protectMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 流转换
	 * 
	 * @param out
	 * @return
	 * @throws Exception
	 */
	private static ByteArrayInputStream parseStream(OutputStream out) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = (ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
		return swapStream;
	}

	/**
	 * Excel导入List
	 * 
	 * @param in
	 * @param sheetName sheet名称
	 * @param entityClass
	 * @param fieldMap 展示的字段名称
	 * @param password 文件密码
	 * @return
	 */
	public static <T> List<T> excelToList(InputStream in, String sheetName, Class<T> entityClass,
			LinkedHashMap<String, String> fieldMap, String password) {
		// 定义要返回的list
		List<T> resultList = new ArrayList<T>();
		try {
			// 解密excel文件
			POIFSFileSystem pfs = new POIFSFileSystem(in);
			in.close();
			EncryptionInfo encInfo = new EncryptionInfo(pfs);
			Decryptor decryptor = Decryptor.getInstance(encInfo);
			decryptor.verifyPassword(password);
			Workbook wb = new XSSFWorkbook(decryptor.getDataStream(pfs));
			// 读取一个工作表
			Sheet sheet = wb.getSheet(sheetName);
			// 获取表头行
			Row headerRow = sheet.getRow(0);
			Iterator<Cell> iter = headerRow.iterator();
			List<String> excelFieldList = new ArrayList<String>();
			while (iter.hasNext()) {
				Cell cell = iter.next();
				excelFieldList.add(cell.getStringCellValue());
			}
			// 判断需要的字段在Excel中是否都存在
			boolean isExist = true;
			for (String cnName : fieldMap.keySet()) {
				if (!excelFieldList.contains(cnName)) {
					isExist = false;
					break;
				}
			}
			// 如果有列名不存在，则抛出异常，提示错误
			if (!isExist) {
				throw new RRException("Excel中缺少必要的字段，或字段名称有误");
			}
			// 将列名和列号放入Map中,这样通过列名就可以拿到列号
			LinkedHashMap<Integer, String> colMap = new LinkedHashMap<Integer, String>();
			for (int i = 0; i < excelFieldList.size(); i++) {
				colMap.put(i, excelFieldList.get(i));
			}
			// 填充数据
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				Iterator<Cell> iter2 = row.iterator();
				// 新建要转换的对象
				T entity = entityClass.newInstance();
				int index = 0;
				while (iter2.hasNext()) {
					Cell cell = iter2.next();
					Object content = "";
					try {
						content = cell.getStringCellValue();
					} catch (IllegalStateException e) {
						content = cell.getNumericCellValue() + "";
					}
					String enNormalName = fieldMap.get(colMap.get(index));
					ReflectUtil.setProperty(entity, enNormalName, content);
					index++;
				}
				resultList.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	/**
	 * 读取excel到集合
	 * @param in
	 * @param sheetName sheet名称
	 * @param entityClass 
	 * @param fieldMap 展示的字段名称
	 * @param password 文件密码
	 * @param protectpass
	 *            保护密码 需要验证
	 * @return
	 */
	public static <T> List<T> excelToList(InputStream in, String sheetName, Class<T> entityClass,
			LinkedHashMap<String, String> fieldMap, String password, String protectpass) {
		// 定义要返回的list
		List<T> resultList = new ArrayList<T>();
		try {
			// 解密excel文件
			POIFSFileSystem pfs = new POIFSFileSystem(in);
			in.close();
			EncryptionInfo encInfo = new EncryptionInfo(pfs);
			Decryptor decryptor = Decryptor.getInstance(encInfo);
			decryptor.verifyPassword(password);
			XSSFWorkbook wb = new XSSFWorkbook(decryptor.getDataStream(pfs));
			boolean b = wb.validateWorkbookPassword(protectpass);
			if (b == false) {
				throw new Exception("保护密码验证失败");
			}
			// 读取一个工作表
			Sheet sheet = wb.getSheet(sheetName);
			// 获取表头行
			Row headerRow = sheet.getRow(0);
			Iterator<Cell> iter = headerRow.iterator();
			List<String> excelFieldList = new ArrayList<String>();
			while (iter.hasNext()) {
				Cell cell = iter.next();
				excelFieldList.add(cell.getStringCellValue());
			}
			// 判断需要的字段在Excel中是否都存在
			boolean isExist = true;
			for (String cnName : fieldMap.keySet()) {
				if (!excelFieldList.contains(cnName)) {
					isExist = false;
					break;
				}
			}
			// 如果有列名不存在，则抛出异常，提示错误
			if (!isExist) {
				throw new RRException("Excel中缺少必要的字段，或字段名称有误");
			}
			// 将列名和列号放入Map中,这样通过列名就可以拿到列号
			LinkedHashMap<Integer, String> colMap = new LinkedHashMap<Integer, String>();
			for (int i = 0; i < excelFieldList.size(); i++) {
				colMap.put(i, excelFieldList.get(i));
			}
			// 填充数据
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				Iterator<Cell> iter2 = row.iterator();
				// 新建要转换的对象
				T entity = entityClass.newInstance();
				int index = 0;
				while (iter2.hasNext()) {
					Cell cell = iter2.next();
					Object content = "";
					try {
						content = cell.getStringCellValue();
					} catch (IllegalStateException e) {
						content = cell.getNumericCellValue() + "";
					}
					String enNormalName = fieldMap.get(colMap.get(index));
					ReflectUtil.setProperty(entity, enNormalName, content);
					index++;
				}
				resultList.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	/**
	 * 读取全部sheet页面数据 可将一页中数据分成不同对象
	 * （注每个sheet页格式需要相同）
	 * @param in InputStream
	 * @param password 文件密码
	 * @param protectpass 保护密码
	 * @param parts SheetPart
	 * @return
	 */
	public static List<Map<String, List>> excelToList(InputStream in, String password, String protectpass,
			SheetPart... parts) {
		// 定义要返回的list
		List resultList = new ArrayList();
		try {
			// 解密excel文件
			POIFSFileSystem pfs = new POIFSFileSystem(in);
			in.close();
			EncryptionInfo encInfo = new EncryptionInfo(pfs);

			Decryptor decryptor = Decryptor.getInstance(encInfo);
			decryptor.verifyPassword(password);
			XSSFWorkbook wb = new XSSFWorkbook(decryptor.getDataStream(pfs));
			boolean b = wb.validateWorkbookPassword(protectpass);
			if (b == false) {
				throw new Exception("保护密码验证失败");
			}
			for (int sn = 0; sn < wb.getNumberOfSheets(); sn++) {
				Sheet sheet = wb.getSheetAt(sn);
				Map sheetValue = new HashMap<String, List>();
				for (SheetPart sheetPart : parts) {
					// 获取表头行
					Row headerRow = sheet.getRow(sheetPart.getTitleRowNumber());
					Iterator<Cell> iter = headerRow.iterator();
					List<String> excelFieldList = new ArrayList<String>();
					while (iter.hasNext()) {
						Cell cell = iter.next();
						excelFieldList.add(cell.getStringCellValue());
					}
					// 判断需要的字段在Excel中是否都存在
					boolean isExist = true;
					for (String cnName : sheetPart.getFieldMap().keySet()) {
						if (!excelFieldList.contains(cnName)) {
							isExist = false;
							break;
						}
					}
					// 如果有列名不存在，则抛出异常，提示错误
					if (!isExist) {
						throw new RRException("Excel中缺少必要的字段，或字段名称有误");
					}
					// 将列名和列号放入Map中,这样通过列名就可以拿到列号
					LinkedHashMap<Integer, String> colMap = new LinkedHashMap<Integer, String>();
					for (int i = 0; i < excelFieldList.size(); i++) {
						colMap.put(i, excelFieldList.get(i));
					}
					// 填充数据
					// 将sheet转换为list
					List partList = new ArrayList();
					int end = sheetPart.getEndRowNumber() == null ? sheet.getLastRowNum() + 1
							: sheetPart.getEndRowNumber();
					for (int i = sheetPart.getStartRowNumber(); i < end; i++) {
						Row row = sheet.getRow(i);
						Iterator<Cell> iter2 = row.iterator();
						// 新建要转换的对象
						Object entity = sheetPart.getEntityClass().newInstance();
						int index = 0;
						while (iter2.hasNext()) {
							Cell cell = iter2.next();
							Object content = "";
							try {
								content = cell.getStringCellValue();
							} catch (IllegalStateException e) {
								content = cell.getNumericCellValue() + "";
							}
							String enNormalName = sheetPart.getFieldMap().get(colMap.get(index));
							ReflectUtil.setProperty(entity, enNormalName, content);
							index++;
						}
						partList.add(entity);
					}
					sheetValue.put(sheetPart.getKey(), partList);
				}
				resultList.add(sheetValue);
			}
			pfs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	};

	/**
	 * 多sheet多部分数据导出到excel
	 * 
	 * @param out
	 * @param lockpass 文件密码
	 * @param protectpass 保护密码
	 * @param sVos SheetVo
	 */
	public static void dataToExcel(OutputStream out, String lockpass, String protectpass, SheetVo... sVos) {
		// 创建一个Excel文件
		XSSFWorkbook wb = new XSSFWorkbook();
		try {
			wb.setWorkbookPassword(protectpass, HashAlgorithm.sha1);
			wb.lockStructure();
			for (SheetVo sheetVo : sVos) {
				String sheetName = sheetVo.getSheetName();
				// 创建一个工作表
				Sheet sheet = wb.createSheet(sheetName);
				// 保护密码
				sheet.protectSheet(protectpass);
				// 添加表头行
				SheetPart[] sheetParts = sheetVo.getSheetParts();
				int lastEnd = 0;
				for (SheetPart sheetPart : sheetParts) {
					// 定义存放英文字段名和中文字段名的数组
					String[] enFields = new String[sheetPart.getFieldMap().size()];
					String[] cnFields = new String[sheetPart.getFieldMap().size()];
					// 填充数组
					int count = 0;
					for (Entry<String, String> entry : sheetPart.getFieldMap().entrySet()) {
						enFields[count] = entry.getKey();
						cnFields[count] = entry.getValue();
						count++;
					}
					String headline = sheetPart.getHeadline();
					// 添加标题并且合并单元格
					if (!StringUtils.isEmpty(headline)) {
						Row headlineRow = sheet.createRow(lastEnd);
						Cell cell = headlineRow.createCell(0);
						cell.setCellValue(headline);
						sheet.autoSizeColumn(0, true);
						lastEnd = lastEnd + 1;
					}
					Row titleRow = sheet.createRow(lastEnd);
					// 填充表头
					Cell titleCell = null;
					for (int i = 0; i < cnFields.length; i++) {
						titleCell = titleRow.createCell(i);
						titleCell.setCellValue(cnFields[i]);
						sheet.autoSizeColumn(i, true);
					}
					lastEnd = lastEnd + 1;
					Row dataRow = null;
					List list = sheetPart.getData();
					// 添加数据内容
					for (int i = 0; i < sheetPart.getCount(); i++) {
						dataRow = sheet.createRow(lastEnd);
						// 获取单个对象
						Object item = list.get(i);
						for (int j = 0; j < enFields.length; j++) {
							XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
							if (sheetPart.getProtectMap().containsKey(enFields[j])) {
								style.setLocked(true);
							} else {
								style.setLocked(false);
							}
							sheet.autoSizeColumn(i, true);
							Object objValue = ReflectUtil.getNestedProperty(item, enFields[j]);
							String fieldValue = objValue == null ? "" : objValue.toString();
							// 创建单元格，并设置值
							Cell cell = dataRow.createCell(j);
							cell.setCellValue(fieldValue);
							cell.setCellStyle(style);
						}
						lastEnd++;
					}
				}
			}
			OutputStream nos = new ByteArrayOutputStream();
			wb.write(nos);
			nos.close();
			POIFSFileSystem fs = new POIFSFileSystem();
			EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
			Encryptor enc = info.getEncryptor();
			enc.confirmPassword(lockpass);
			InputStream nis = parseStream(nos);
			OPCPackage opc = OPCPackage.open(nis);
			OutputStream os = enc.getDataStream(fs);
			opc.save(os);
			opc.close();
			fs.writeFilesystem(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 集合导出excel
	 * 
	 * @param list
	 * @param sheetName
	 * @param fieldMap
	 * @param response
	 */
	public static <T> void dataToExcel(String fileName, HttpServletResponse response, String lockpass, String protectpass,
			SheetVo... sVos) {
		response.reset();
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // 改成输出excel文件
		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
		// 创建工作簿并发送到浏览器
		try {
			OutputStream out = response.getOutputStream();
			dataToExcel(out, lockpass, protectpass, sVos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
