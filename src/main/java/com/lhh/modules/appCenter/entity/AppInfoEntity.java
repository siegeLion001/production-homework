package com.lhh.modules.appCenter.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 * 应用信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public class AppInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//应用id-主键
	private Integer appId;
	//应用名称
	private String appName;
	//应用标签
	private String lableIdList;
	//应用评分
	private Double score;
	//创建时间
	private Date createTime;
	//Y:删除;N:没删除
	private String deleteYn;

	private List<Long> lables;
	
	//图标路径
	private String picPath;
	//下载次数
	private Integer downloadCount;
	//评论次数
	private Integer commentCount; 
	//应用简介                       
	private String introduce;
	//应用预览图
	private String imgList;
	//1：教师端应用，2：学生端应用
	private String client;
	//应用上架时间
	private Date upTime;
	//应用下架时间
	private Date downTime;

	/**
	 * 设置：应用id-主键
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	/**
	 * 获取：应用id-主键
	 */
	public Integer getAppId() {
		return appId;
	}
	/**
	 * 设置：应用名称
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	/**
	 * 获取：应用名称
	 */
	public String getAppName() {
		return appName;
	}
	/**
	 * 设置：应用标签
	 */
	public void setLableIdList(String lableIdList) {
		this.lableIdList = lableIdList;
	}
	/**
	 * 获取：应用标签
	 */
	public String getLableIdList() {
		return lableIdList;
	}
	/**
	 * 设置：应用评分
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	/**
	 * 获取：应用评分
	 */
	public Double getScore() {
		return score;
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
	 * 设置：Y:删除;N:没删除
	 */
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	/**
	 * 获取：Y:删除;N:没删除
	 */
	public String getDeleteYn() {
		return deleteYn;
	}
	
	public List<Long> getLables() {
		return lables;
	}

	public void setLables(List<Long> lables) {
		this.lables = lables;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public Integer getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getImgList() {
		return imgList;
	}
	public void setImgList(String imgList) {
		this.imgList = imgList;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Date getUpTime() {
		return upTime;
	}
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	public Date getDownTime() {
		return downTime;
	}
	public void setDownTime(Date downTime) {
		this.downTime = downTime;
	}
}
