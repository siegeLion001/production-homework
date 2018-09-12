package com.lhh.modules.HomeworkExplain.entity;

import com.lhh.common.validator.group.AddGroup;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;


/**
 * 作业讲解表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-19 11:49:07
 */
public class HomeworkExplainEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//教师id
	@NotBlank(groups = AddGroup.class)
	private String tchId;
	//作业id
	@NotBlank(groups = AddGroup.class)
	private Integer homeworkId;
	//班级ids(groups = AddGroup.class)
	private List<String> classIds;
	//大题号
	@NotBlank(groups = AddGroup.class)
	private String bNum;
	//小题号
	@NotBlank(groups = AddGroup.class)
	private String sNum;
	//v课
	private List<String> vClass;
	//v图
	private List<String> vPicture;



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
	 * 设置：作业id
	 */
	public void setHomeworkid(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}
	/**
	 * 获取：作业id
	 */
	public Integer getHomeworkid() {
		return homeworkId;
	}
	/**
	 * 设置：班级ids
	 */
	public void setClassIds(List classIds) {
		this.classIds = classIds;
	}
	/**
	 * 获取：班级ids
	 */
	public List getClassIds() {
		return classIds;
	}
	/**
	 * 设置：大题号
	 */
	public void setBNum(String bNum) {
		this.bNum = bNum;
	}
	/**
	 * 获取：大题号
	 */
	public String getBNum() {
		return bNum;
	}
	/**
	 * 设置：小题号
	 */
	public void setSNum(String sNum) {
		this.sNum = sNum;
	}
	/**
	 * 获取：小题号
	 */
	public String getSNum() {
		return sNum;
	}
	/**
	 * 设置：v课
	 */
	public void setVClass(List vClass) {
		this.vClass = vClass;
	}
	/**
	 * 获取：v课
	 */
	public List getVClass() {
		return vClass;
	}
	/**
	 * 设置：v图
	 */
	public void setVPicture(List vPicture) {
		this.vPicture = vPicture;
	}
	/**
	 * 获取：v图
	 */
	public List getVPicture() {
		return vPicture;
	}
}
