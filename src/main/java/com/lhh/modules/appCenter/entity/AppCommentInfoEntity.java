package com.lhh.modules.appCenter.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 应用评论信息表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-17 09:31:00
 */
public class AppCommentInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer commentId;
	//用户id
	private String userId;
	//应用版本id
	private Integer appVersionId;
	//评论内容
	private String content;
	//评论分数
	private double score;
	//评论时间
	private Date commentTime;
	//父级评论id
	private Integer parentId;
	//用户名
	private String userName;
	//应用id
	private Integer appId;

	/**
	 * 设置：主键
	 */
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getCommentId() {
		return commentId;
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
	 * 设置：评论分数
	 */
	public void setScore(double score) {
		this.score = score;
	}
	/**
	 * 获取：评论分数
	 */
	public double getScore() {
		return score;
	}
	/**
	 * 设置：评论时间
	 */
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	/**
	 * 获取：评论时间
	 */
	public Date getCommentTime() {
		return commentTime;
	}
	/**
	 * 设置：父级评论id
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父级评论id
	 */
	public Integer getParentId() {
		return parentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	
}
