package com.lhh.modules.classAppraisal.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 学生表扬批评记录表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-09 17:39:58
 */
public class ClassAppraisalEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	private Integer aprId;
	//评论人id
	private String authId;
	//评论人类型  教师或学生
	private Integer authType;
	//被评论人 (目标人)
	private String targetId;
	//评价
	private String appraisal;
	//表扬或批评  1 表扬  2批评
	private Integer state;
	//创建时间
	private Date createTime;
	//parentId   二级评论
	private Integer parentId;
	//班级id
	private String classId;
	//学期
	private String semester;


	/**
	 * 设置：评论人类型  教师或学生
	 */
	public void setAuthType(Integer authType) {
		this.authType = authType;
	}
	/**
	 * 获取：评论人类型  教师或学生
	 */
	public Integer getAuthType() {
		return authType;
	}

	/**
	 * 设置：评价
	 */
	public void setAppraisal(String appraisal) {
		this.appraisal = appraisal;
	}
	/**
	 * 获取：评价
	 */
	public String getAppraisal() {
		return appraisal;
	}
	/**
	 * 设置：表扬或批评  1 表扬  2批评
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：表扬或批评  1 表扬  2批评
	 */
	public Integer getState() {
		return state;
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

	public Integer getAprId() {
		return aprId;
	}

	public void setAprId(Integer aprId) {
		this.aprId = aprId;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
}
