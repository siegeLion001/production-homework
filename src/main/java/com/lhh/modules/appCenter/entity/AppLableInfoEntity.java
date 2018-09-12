package com.lhh.modules.appCenter.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 应用标签信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public class AppLableInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer lableId;
	//标签名
	private String lableName;
	//操作者id
	private String operatorId;
	//创建时间
	private Date createTime;
	//Y:删除 N:未删除
	private String deleteYn;
	//图标路径
	private String iconPath;

	/**
	 * 设置：主键
	 */
	public void setLableId(Integer lableId) {
		this.lableId = lableId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getLableId() {
		return lableId;
	}
	/**
	 * 设置：标签名
	 */
	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
	/**
	 * 获取：标签名
	 */
	public String getLableName() {
		return lableName;
	}
	/**
	 * 设置：操作者id
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	/**
	 * 获取：操作者id
	 */
	public String getOperatorId() {
		return operatorId;
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
	/**
	 * 设置：Y:删除 N:未删除
	 */
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	/**
	 * 获取：Y:删除 N:未删除
	 */
	public String getDeleteYn() {
		return deleteYn;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	
	
}
