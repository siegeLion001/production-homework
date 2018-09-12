package com.lhh.modules.property.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;



/**
 * 标段属性表
 * 
 * @author ma.zd
 * @email mazengdi@lanhaihui.net
 * @date 2018-03-12 11:07:44
 */
public class PropertyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//编码
	private String code;
	//名称
	private String name;
	//分类 0.类型 1.地区
	private Integer type;
	/**
	 * 是否可变更的标记 0不可变更 1可变更 
	 * wangcheng
	 */
	@NotBlank(message="参数值不能为空")
	private Integer changeFlag;
	//创建时间
	private Date createTime;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：编码
	 */
	public String getCode() {
		return code;
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
	 * 设置：分类 0.类型 1.地区
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：分类 0.类型 1.地区
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	public Integer getChangeFlag() {
		return changeFlag;
	}
	public void setChangeFlag(Integer changeFlag) {
		this.changeFlag = changeFlag;
	}
}
