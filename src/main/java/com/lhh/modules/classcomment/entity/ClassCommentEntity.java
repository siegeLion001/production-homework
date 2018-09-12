package com.lhh.modules.classcomment.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 班级空间 评论
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-23 19:59:24
 */
public class ClassCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//评论人
	private String authId;
	//目标id 评论别人的评论时 使用
	private String targetId;
	//评论内容
	private String content;
	//创建时间
	private Date createTime;
	//消息id
	private Integer topId;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：评论人
	 */
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	/**
	 * 获取：评论人
	 */
	public String getAuthId() {
		return authId;
	}
	/**
	 * 设置：目标id 评论别人的评论时 使用
	 */
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	/**
	 * 获取：目标id 评论别人的评论时 使用
	 */
	public String getTargetId() {
		return targetId;
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
	 * 设置：消息id
	 */
	public void setTopId(Integer topId) {
		this.topId = topId;
	}
	/**
	 * 获取：消息id
	 */
	public Integer getTopId() {
		return topId;
	}
}
