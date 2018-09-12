package com.lhh.modules.cmbook.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 18:48:30
 */
public class CmBookEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private Integer typeId;
	//
	private Integer publisherId;
	//册别名称
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
	 * 设置：
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：
	 */
	public Integer getTypeId() {
		return typeId;
	}
	/**
	 * 设置：
	 */
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	/**
	 * 获取：
	 */
	public Integer getPublisherId() {
		return publisherId;
	}
	/**
	 * 设置：册别名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：册别名称
	 */
	public String getName() {
		return name;
	}
}
