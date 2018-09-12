package com.lhh.modules.interaction.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 师生互动模块的公告信息表
 * 
 * @author menglimei
 * @email menglimei@lanhaihui.net
 * @date 2018-09-05 17:14:36
 */
public class InteractionNoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//公告id
	private Integer noticeId;
	//教师id
	private String tchId;
	//班级ids
	private String[] classIds;
	//功能内容
	private String content;
	//音频路径
	private String audioPath;
	//图片路径
	private String picsPath;
	//是否允许学生自由评论 Y：允许  ；N：不允许
	private String commentYn;
	//发送时间
	private Date pushTime;
	//创建时间
	private Date createTime;
	//是否删除  Y;已删除 N:未删除
	private String deleteYn;

	/**
	 * 设置：公告id
	 */
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	/**
	 * 获取：公告id
	 */
	public Integer getNoticeId() {
		return noticeId;
	}
	/**
	 * 设置：教师id
	 */
	public void setTchId(String tchId) {
		this.tchId = tchId;
	}
	/**
	 * 获取：教师id
	 */
	public String getTchId() {
		return tchId;
	}
	/**
	 * 设置：班级ids
	 */
	public void setClassIds(String[] classIds) {
		this.classIds = classIds;
	}
	/**
	 * 获取：班级ids
	 */
	public String[] getClassIds() {
		return classIds;
	}
	/**
	 * 设置：功能内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：功能内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：音频路径
	 */
	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}
	/**
	 * 获取：音频路径
	 */
	public String getAudioPath() {
		return audioPath;
	}
	/**
	 * 设置：图片路径
	 */
	public void setPicsPath(String picsPath) {
		this.picsPath = picsPath;
	}
	/**
	 * 获取：图片路径
	 */
	public String getPicsPath() {
		return picsPath;
	}
	/**
	 * 设置：是否允许学生自由评论 Y：允许  ；N：不允许
	 */
	public void setCommentYn(String commentYn) {
		this.commentYn = commentYn;
	}
	/**
	 * 获取：是否允许学生自由评论 Y：允许  ；N：不允许
	 */
	public String getCommentYn() {
		return commentYn;
	}
	/**
	 * 设置：发送时间
	 */
	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	/**
	 * 获取：发送时间
	 */
	public Date getPushTime() {
		return pushTime;
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
	 * 设置：是否删除  Y;已删除 N:未删除
	 */
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	/**
	 * 获取：是否删除  Y;已删除 N:未删除
	 */
	public String getDeleteYn() {
		return deleteYn;
	}
}
