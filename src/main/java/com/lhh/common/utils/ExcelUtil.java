package com.lhh.common.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.lhh.common.exception.RRException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 ******************************************************
 * Description:导入导出Excel工具类 CreateTime:2017年1月5日 下午1:31:58 CreateUser: 马增迪
 *******************************************************
 */
public class ExcelUtil {

	/*
	 *************************************** 
	 * Description:导出excel2003：list 数据集合，fieldMap 类的英文属性和Excel中的中文列名的对应关系，sheetName
	 * 工作表的名称，out 导出流 CreateTime :2017年1月5日 下午1:34:30 CreateUser :马增迪
	 *****************************************
	 */
	public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName,
			OutputStream out) throws Exception {
		if (list.size() == 0 || list == null) {
			throw new Exception("数据源中没有任何数据");
		}
		int sheetSize = list.size();
		if (sheetSize > 65535 || sheetSize < 1) {
			sheetSize = 65535;
		}
		// 创建工作簿并发送到OutputStream指定的地方
		WritableWorkbook wwb;
		try {
			wwb = Workbook.createWorkbook(out);
			// 1.计算一共有多少个工作表
			int sheetNum = list.size() % sheetSize == 0 ? list.size() / sheetSize : (list.size() / sheetSize + 1);
			// 2.创建相应的工作表，并向其中填充数据
			for (int i = 0; i < sheetNum; i++) {
				// 如果只有一个工作表的情况
				if (1 == sheetNum) {
					WritableSheet sheet = wwb.createSheet(sheetName, i);
					fillSheet(sheet, list, fieldMap, 0, list.size() - 1);
					// 有多个工作表的情况
				} else {
					WritableSheet sheet = wwb.createSheet(sheetName + (i + 1), i);
					// 获取开始索引和结束索引
					int firstIndex = i * sheetSize;
					int lastIndex = (i + 1) * sheetSize - 1 > list.size() - 1 ? list.size() - 1
							: (i + 1) * sheetSize - 1;
					// 填充工作表
					fillSheet(sheet, list, fieldMap, firstIndex, lastIndex);
				}
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 *************************************** 
	 * Description:list转excel，导出到浏览器 CreateTime :2017年1月5日 下午1:36:03 CreateUser :马增迪
	 *****************************************
	 */
	public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName,
			HttpServletResponse response) {

		// 设置默认文件名为当前时间：年月日时分秒
		String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
		// 设置response头信息
		response.reset();
		response.setContentType("application/vnd.ms-excel"); // 改成输出excel文件
		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
		// 创建工作簿并发送到浏览器
		try {
			OutputStream out = response.getOutputStream();
			listToExcel(list, fieldMap, sheetName, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setColumnAutoSize(WritableSheet ws, int extraWith) {
		// 获取本列的最宽单元格的宽度
		for (int i = 0; i < ws.getColumns(); i++) {
			int colWith = 0;
			for (int j = 0; j < ws.getRows(); j++) {
				String content = ws.getCell(i, j).getContents().toString();
				int cellWith = content.length();
				if (colWith < cellWith) {
					colWith = cellWith;
				}
			}
			// 设置单元格的宽度为最宽宽度+额外宽度
			ws.setColumnView(i, colWith + extraWith);
		}
	}

	/*
	 *************************************** 
	 * Description:excel转对象List：in 输入流，sheetName 工作表的名称，entityClass 实体class，fieldMap
	 * ：Excel中的中文列头和类的英文属性的对应关系Map CreateTime :2017年1月5日 下午1:37:24 CreateUser :马增迪
	 *****************************************
	 */
	public static <T> List<T> excelToList(InputStream in, String sheetName, Class<T> entityClass,
			LinkedHashMap<String, String> fieldMap) {
		// 定义要返回的list
		List<T> resultList = new ArrayList<T>();
		try {
			// 根据Excel数据源创建WorkBook
			Workbook wb = Workbook.getWorkbook(in);
			// 获取工作表
			Sheet sheet = wb.getSheet(sheetName);
			// 获取工作表的有效行数
			int realRows = 0;
			for (int i = 0; i < sheet.getRows(); i++) {

				int nullCols = 0;
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell currentCell = sheet.getCell(j, i);
					if (currentCell == null || "".equals(currentCell.getContents().toString())) {
						nullCols++;
					}
				}
				if (nullCols == sheet.getColumns()) {
					break;
				} else {
					realRows++;
				}
			}
			// 如果Excel中没有数据则提示错误
			if (realRows <= 1) {
				throw new RRException("Excel文件中没有任何数据");
			}
			Cell[] firstRow = sheet.getRow(0);
			String[] excelFieldNames = new String[firstRow.length];
			// 获取Excel中的列名
			for (int i = 0; i < firstRow.length; i++) {
				excelFieldNames[i] = firstRow[i].getContents().toString().trim();
			}
			// 判断需要的字段在Excel中是否都存在
			boolean isExist = true;
			List<String> excelFieldList = Arrays.asList(excelFieldNames);
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
			LinkedHashMap<String, Integer> colMap = new LinkedHashMap<String, Integer>();
			for (int i = 0; i < excelFieldNames.length; i++) {
				colMap.put(excelFieldNames[i], firstRow[i].getColumn());
			}

			// 将sheet转换为list
			for (int i = 1; i < realRows; i++) {
				// 新建要转换的对象
				T entity = entityClass.newInstance();
				// 给对象中的字段赋值
				for (Entry<String, String> entry : fieldMap.entrySet()) {
					// 获取中文字段名
					String cnNormalName = entry.getKey();
					// 获取英文字段名
					String enNormalName = entry.getValue();
					// 根据中文字段名获取列号
					int col = colMap.get(cnNormalName);
					// 获取当前单元格中的内容
					String content = sheet.getCell(col, i).getContents().toString().trim();
					ReflectUtil.setProperty(entity, enNormalName, content);
				}
				resultList.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	// 向工作表中填充数据
	private static <T> void fillSheet(WritableSheet sheet, List<T> list, LinkedHashMap<String, String> fieldMap,
			int firstIndex, int lastIndex) throws Exception {

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
		for (int i = 0; i < cnFields.length; i++) {
			Label label = new Label(i, 0, cnFields[i]);
			sheet.addCell(label);
		}
		// 填充内容
		int rowNo = 1;
		for (int index = firstIndex; index <= lastIndex; index++) {
			// 获取单个对象
			T item = list.get(index);
			for (int i = 0; i < enFields.length; i++) {
				Object objValue = null;
				objValue = ReflectUtil.getNestedProperty(item, enFields[i]);
				String fieldValue = objValue == null ? "" : objValue.toString();
				Label label = new Label(i, rowNo, fieldValue);
				sheet.addCell(label);
			}
			rowNo++;
		}
		// 设置自动列宽
		setColumnAutoSize(sheet, 5);
	}

	// 向工作表中填充数据
	private static <T> void myfillSheet(WritableSheet sheet, List<T> list, LinkedHashMap<String, String> fieldMap,
			int start, int dataCount) throws Exception {

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
		for (int i = 0; i < cnFields.length; i++) {
			Label label = new Label(i, start, cnFields[i]);
			sheet.addCell(label);
		}
		start = start + 1;
		// 填充内容 开始行号 总条数
		int dNo = 0;
		for (int rowNo = start; rowNo < start + dataCount; rowNo++) {
			// 获取单个对象
			System.out.println(dataCount + "xxx");
			T item = list.get(dNo);
			for (int i = 0; i < enFields.length; i++) {
				Object objValue = null;
				objValue = ReflectUtil.getNestedProperty(item, enFields[i]);
				String fieldValue = objValue == null ? "" : objValue.toString();
				Label label = new Label(i, rowNo, fieldValue);
				sheet.addCell(label);
			}
			dNo++;
		}
		// 设置自动列宽
		setColumnAutoSize(sheet, 5);
	}

	// 导出全部sheet页面数据 可将一页中数据分成不同对象
	public static List<Map<String, List>> excelToList(InputStream in, SheetPart... parts) {
		// 定义要返回的list
		List resultList = new ArrayList();
		try {
			// 根据Excel数据源创建WorkBook
			Workbook wb = Workbook.getWorkbook(in);
			// 获取工作表
			Sheet[] ss = wb.getSheets();
			for (Sheet sheet : ss) {
				Map sheetValue = new HashMap<String, List>();

				for (SheetPart sp : parts) {
					// 获取工作表的有效行数

					int realRows = sheet.getRows();
					int start = sp.getStartRowNumber();
					int end = sp.getEndRowNumber() == null ? realRows : sp.getEndRowNumber();
					// 获取列名

					Cell[] titleRow = sheet.getRow(sp.getTitleRowNumber());
					String[] excelFieldNames = new String[titleRow.length];
					// 获取Excel中的列名
					for (int t = 0; t < titleRow.length; t++) {
						excelFieldNames[t] = titleRow[t].getContents().toString().trim();
					}

					// 判断需要的字段在Excel中是否都存在
					boolean isExist = true;
					List<String> excelFieldList = Arrays.asList(excelFieldNames);
					Set sss = sp.getFieldMap().keySet();
					for (String cnName : sp.getFieldMap().keySet()) {
						if (!excelFieldList.contains(cnName)) {

							System.out.println(cnName);
							isExist = false;
							break;
						}
					}

					// 如果有列名不存在，则抛出异常，提示错误
					if (!isExist) {
						throw new RRException("Excel中缺少必要的字段，或字段名称有误");
					}
					// 将列名和列号放入Map中,这样通过列名就可以拿到列号
					LinkedHashMap<String, Integer> colMap = new LinkedHashMap<String, Integer>();
					for (int e = 0; e < excelFieldNames.length; e++) {
						colMap.put(excelFieldNames[e], titleRow[e].getColumn());
					}
					// 将sheet转换为list
					List partList = new ArrayList();

					for (int i = start; i < end; i++) {
						Cell[] cs = sheet.getRow(i);

						// 新建要转换的对象
						Object entity = sp.getEntityClass().newInstance();
						// 给对象中的字段赋值
						//
						for (Entry<String, String> entry : sp.getFieldMap().entrySet()) {
							// 获取中文字段名
							String cnNormalName = entry.getKey();
							// 获取英文字段名
							String enNormalName = entry.getValue();
							// 根据中文字段名获取列号
							int col = colMap.get(cnNormalName);
							// 获取当前单元格中的内容
							String content = sheet.getCell(col, i).getContents().toString().trim();
							ReflectUtil.setProperty(entity, enNormalName, content);
						}

						partList.add(entity);
					}
					sheetValue.put(sp.getKey(), partList);
				}
				resultList.add(sheetValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	};

	
	
	
	public static void dataToExcel(OutputStream out, SheetVo... sVos) {
		try {
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			if (sVos.length < 0) {
				throw new RuntimeException("没有数据");
			}
			int sheetNum = sVos.length;

			for (int i = 0; i < sVos.length; i++) {
				SheetVo sheetVo = sVos[i];
				WritableSheet sheet = wwb.createSheet(sheetVo.getSheetName(), i);
				SheetPart[] sheetParts = sheetVo.getSheetParts();
				int lastEnd = 0;
				for (SheetPart sheetPart : sheetParts) {
					String headline = sheetPart.getHeadline();
					// 添加标题并且合并单元格
					if (!StringUtils.isEmpty(headline)) {
						Label label = new Label(0, lastEnd, headline);
						sheet.addCell(label);
						sheet.mergeCells(lastEnd, 0, sheetPart.getFieldMap().size() - 1, lastEnd);
						lastEnd = lastEnd + 1;
					}
					myfillSheet(sheet, sheetPart.getData(), sheetPart.getFieldMap(), lastEnd, sheetPart.getCount());
					lastEnd = lastEnd + sheetPart.getCount() + 1;
				}
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}