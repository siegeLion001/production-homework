package com.lhh.common.utils;

import java.util.LinkedHashMap;

import java.util.List;

/**
 * 模板组成部分
 * 
 * @author miaofu
 *
 */
public class SheetPart {

	private Class<?> entityClass;

	/**
	 * 返回数据的集合Map的key值
	 */
	private String key;
	/**
	 * 展示的字段名称
	 */
	private LinkedHashMap<String, String> fieldMap;
	/**
	 * 需要保护列的集合
	 */
	private LinkedHashMap<String, String> protectMap;
	/**
	 * 标题所在行号
	 */
	private Integer titleRowNumber;
	/**
	 * 数据开始行号
	 */
	private Integer startRowNumber;
	/**
	 * 数据结束行号
	 */
	private Integer endRowNumber;
	/**
	 * 标题
	 */
	private String headline;
	/**
	 * 读取到的数据
	 */
	private List<?> data;
	/**
	 * 数据条数
	 */
	private int count;

	public SheetPart(String headline, LinkedHashMap<String, String> fieldMap, LinkedHashMap<String, String> protectMap,
			List<?> data, int count) {
		this.headline = headline;
		this.fieldMap = fieldMap;
		this.protectMap = protectMap;
		this.data = data;
		this.count = count;
	}

	public SheetPart(Class<?> entityClass, String key, LinkedHashMap<String, String> fieldMap, Integer titleRowNumber,
			Integer startRowNumber, Integer endRowNumber) {
		this.entityClass = entityClass;
		this.key = key;
		this.fieldMap = fieldMap;
		this.titleRowNumber = titleRowNumber;
		this.startRowNumber = startRowNumber;
		this.endRowNumber = endRowNumber;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public LinkedHashMap<String, String> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(LinkedHashMap<String, String> fieldMap) {
		this.fieldMap = fieldMap;
	}

	public Integer getTitleRowNumber() {
		return titleRowNumber;
	}

	public void setTitleRowNumber(Integer titleRowNumber) {
		this.titleRowNumber = titleRowNumber;
	}

	public Integer getStartRowNumber() {
		return startRowNumber;
	}

	public void setStartRowNumber(Integer startRowNumber) {
		this.startRowNumber = startRowNumber;
	}

	public Integer getEndRowNumber() {
		return endRowNumber;
	}

	public void setEndRowNumber(Integer endRowNumber) {
		this.endRowNumber = endRowNumber;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public LinkedHashMap<String, String> getProtectMap() {
		return protectMap;
	}

	public void setProtectMap(LinkedHashMap<String, String> protectMap) {
		this.protectMap = protectMap;
	}
	
}
