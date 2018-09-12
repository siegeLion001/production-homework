package com.lhh.modules.cmtype.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
public class CmTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//名称
	private String name;
	//说明
	private String explanation;

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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：说明
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	/**
	 * 获取：说明
	 */
	public String getExplanation() {
		return explanation;
	}
}
