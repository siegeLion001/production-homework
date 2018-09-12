package com.lhh.modules.appCenter.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 应用下载记录
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
/**
 * @author Administrator
 *
 */
public class AppDownloadRecordInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer recordId;
	//用户id
	private String userId;
	//应用版本id
	private Integer appVersionId;
	//创建时间
	private Date createTime;
    //appId
	private Integer appId;
	//是否删除
	private String deleteYn;
	//用户名
	private String userName;
	//1:下载 2：更新
	private String type;
	
	/**
	 * 设置：主键
	 */
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getRecordId() {
		return recordId;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：应用版本id
	 */
	public void setAppVersionId(Integer appVersionId) {
		this.appVersionId = appVersionId;
	}
	/**
	 * 获取：应用版本id
	 */
	public Integer getAppVersionId() {
		return appVersionId;
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
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
