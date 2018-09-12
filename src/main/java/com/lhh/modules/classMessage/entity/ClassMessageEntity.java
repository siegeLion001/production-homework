package com.lhh.modules.classMessage.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-12 09:37:33
 */
public class ClassMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer msgId;
	//班级id
	private String classId;
	//评论人id
	private String authId;
	//目标人id
	private String targetId;
	//寄语
	private String message;
	//学期字段
	private String semester;
	//创建日期
	private Date createTime;
	//父id
	private Integer parentId;

	/**
	 * 设置：主键
	 */
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getMsgId() {
		return msgId;
	}
	/**
	 * 设置：班级id
	 */
	public void setClassId(String classId) {
		this.classId = classId;
	}
	/**
	 * 获取：班级id
	 */
	public String getClassId() {
		return classId;
	}
	/**
	 * 设置：评论人id
	 */
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	/**
	 * 获取：评论人id
	 */
	public String getAuthId() {
		return authId;
	}
	/**
	 * 设置：目标人id
	 */
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	/**
	 * 获取：目标人id
	 */
	public String getTargetId() {
		return targetId;
	}
	/**
	 * 设置：寄语
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 获取：寄语
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 设置：学期字段
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	/**
	 * 获取：学期字段
	 */
	public String getSemester() {
		return semester;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
