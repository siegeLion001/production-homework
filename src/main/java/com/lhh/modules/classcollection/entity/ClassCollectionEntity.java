package com.lhh.modules.classcollection.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 收藏表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-24 16:41:18
 */
public class ClassCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer collId;
	//消息id
	private Integer topId;
	//班级id
	private String classId;
	//操作人id
	private String authId;
	//评论人类型  教师或学生
	private Integer authType;
	//创建时间
	private Date createTime;

	public Integer getCollMark() {
		return collMark;
	}

	public void setCollMark(Integer collMark) {
		this.collMark = collMark;
	}

	//表示字段   1,收藏   2,成长记录
	private Integer collMark;

	/**
	 * 获取：主键
	 */
	public Integer getCollId() {
		return collId;
	}

	/**
	 * 设置：主键
	 */
	public void setCollId(Integer collId) {
		this.collId = collId;
	}

	/**
	 * 获取：消息id
	 */
	public Integer getTopId() {
		return topId;
	}

	/**
	 * 设置：消息id
	 */
	public void setTopId(Integer topId) {
		this.topId = topId;
	}

	/**
	 * 获取：班级id
	 */
	public String getClassId() {
		return classId;
	}

	/**
	 * 设置：班级id
	 */
	public void setClassId(String classId) {
		this.classId = classId;
	}

	/**
	 * 获取：操作人id
	 */
	public String getAuthId() {
		return authId;
	}

	/**
	 * 设置：操作人id
	 */
	public void setAuthId(String authId) {
		this.authId = authId;
	}

	/**
	 * 获取：评论人类型  教师或学生
	 */
	public Integer getAuthType() {
		return authType;
	}

	/**
	 * 设置：评论人类型  教师或学生
	 */
	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
