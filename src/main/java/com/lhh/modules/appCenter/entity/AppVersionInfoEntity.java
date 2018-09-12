package com.lhh.modules.appCenter.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 应用版本信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public class AppVersionInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//应用版本id
	private Integer appVersionId;
	//应用id
	private Integer appId;
	//应用版本
	private String appVersion;
	//应用大小
	private String appSize;
	//应用路径
	private String appPath;
	//创建时间
	private Date createTime;
	//是否删除
	private String deleteYn;
    //pak包名
	private String packageName;
	//应用版本简介
	private String introduce;
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
	 * 设置：应用id
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	/**
	 * 获取：应用id
	 */
	public Integer getAppId() {
		return appId;
	}
	/**
	 * 设置：应用版本
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	/**
	 * 获取：应用版本
	 */
	public String getAppVersion() {
		return appVersion;
	}
	/**
	 * 设置：应用大小
	 */
	public void setAppSize(String size) {
		this.appSize = size;
	}
	/**
	 * 获取：应用大小
	 */
	public String getAppSize() {
		return appSize;
	}
	/**
	 * 设置：应用路径
	 */
	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}
	/**
	 * 获取：应用路径
	 */
	public String getAppPath() {
		return appPath;
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
	 * 设置：是否删除
	 */
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	/**
	 * 获取：是否删除
	 */
	public String getDeleteYn() {
		return deleteYn;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
}
