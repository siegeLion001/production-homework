package com.lhh.modules.cmpublisher.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
public class CmPublisherEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//类型id
	private Integer typeId;
	//出版社名称
	private String name;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：类型id
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：类型id
	 */
	public Integer getTypeId() {
		return typeId;
	}
	/**
	 * 设置：出版社名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：出版社名称
	 */
	public String getName() {
		return name;
	}
}
