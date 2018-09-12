package com.lhh.modules.classAssist.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 点赞表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-26 09:52:54
 */
public class ClassAssistEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer assId;
	//消息id
	private Integer topId;
	//点赞人id
	private String authId;
	//被赞人id
	private String targetId;
	//点赞标识 (备用)
	private String assistMark;
	//创建时间
	private Date createTime;

	/**
	 * 设置：主键
	 */
	public void setAssId(Integer assId) {
		this.assId = assId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getAssId() {
		return assId;
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
	/**
	 * 设置：点赞人id
	 */
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	/**
	 * 获取：点赞人id
	 */
	public String getAuthId() {
		return authId;
	}
	/**
	 * 设置：被赞人id
	 */
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	/**
	 * 获取：被赞人id
	 */
	public String getTargetId() {
		return targetId;
	}
	/**
	 * 设置：点赞标识 (备用)
	 */
	public void setAssistMark(String assistMark) {
		this.assistMark = assistMark;
	}
	/**
	 * 获取：点赞标识 (备用)
	 */
	public String getAssistMark() {
		return assistMark;
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
}
