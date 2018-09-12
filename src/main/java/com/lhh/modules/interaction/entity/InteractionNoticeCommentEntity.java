package com.lhh.modules.interaction.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 师生互动模块公告的评论信息表
 * 
 * @author menglimei
 * @email menglimei@lanhaihui.net
 * @date 2018-09-05 17:14:36
 */
public class InteractionNoticeCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//评论id
	private Integer commentId;
	//公告id
	private Integer noticeId;
	//评论内容
	private String content;
	//音频路径
	private String audioPath;
	//图片路径
	private String picsPath;
	//录像路径
	private String videoPath;
	//评论时间
	private Date createTime;
	//是否删除 Y：已删除 N：未删除
	private String deleteYn;
	//评论者id
	private String commentator;

	/**
	 * 设置：评论id
	 */
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	/**
	 * 获取：评论id
	 */
	public Integer getCommentId() {
		return commentId;
	}
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
	 * 设置：评论内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：评论内容
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
	 * 设置：录像路径
	 */
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	/**
	 * 获取：录像路径
	 */
	public String getVideoPath() {
		return videoPath;
	}
	/**
	 * 设置：评论时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：评论时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：是否删除 Y：已删除 N：未删除
	 */
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	/**
	 * 获取：是否删除 Y：已删除 N：未删除
	 */
	public String getDeleteYn() {
		return deleteYn;
	}
	public String getCommentator() {
		return commentator;
	}
	public void setCommentator(String commentator) {
		this.commentator = commentator;
	}
	
}
