package com.lhh.modules.HomeworkExplainRel.entity;

import java.io.Serializable;



/**
 * 作业讲解关联表

 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-19 11:49:03
 */
public class HomeworkExplainRelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//外键
	private Integer eId;
	//学生id
	private String stuId;
	//班级id
	private String classId;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：外键
	 */
	public void setEId(Integer eId) {
		this.eId = eId;
	}
	/**
	 * 获取：外键
	 */
	public Integer getEId() {
		return eId;
	}
	/**
	 * 设置：学生id
	 */
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	/**
	 * 获取：学生id
	 */
	public String getStuId() {
		return stuId;
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
}
