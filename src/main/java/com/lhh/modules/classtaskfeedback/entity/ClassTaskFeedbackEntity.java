package com.lhh.modules.classtaskfeedback.entity;

import java.io.Serializable;
import java.util.Date;



/**
 *
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-14 13:54:57
 */
public class ClassTaskFeedbackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//id
	private Integer id;
	//评价 1 优秀 2 良好 3 合格 4 不合格
	private Integer assess;
	//评论人
	private String authId;
	//创建时间
	private Date createTime;
	//难度
	private Float difficulty;
	//消息id
	private Integer topId;
	//用时 毫秒值
	private Integer useTime;
	//工作量
	private Float workload;
	private String classId;

	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取：评价 1 优秀 2 良好 3 合格 4 不合格
	 */
	public Integer getAssess() {
		return assess;
	}

	/**
	 * 设置：评价 1 优秀 2 良好 3 合格 4 不合格
	 */
	public void setAssess(Integer assess) {
		this.assess = assess;
	}

	/**
	 * 获取：评论人
	 */
	public String getAuthId() {
		return authId;
	}

	/**
	 * 设置：评论人
	 */
	public void setAuthId(String authId) {
		this.authId = authId;
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

	/**
	 * 获取：难度
	 */
	public Float getDifficulty() {
		return difficulty;
	}

	/**
	 * 设置：难度
	 */
	public void setDifficulty(Float difficulty) {
		this.difficulty = difficulty;
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
	 * 获取：用时 毫秒值
	 */
	public Integer getUseTime() {
		return useTime;
	}

	/**
	 * 设置：用时 毫秒值
	 */
	public void setUseTime(Integer useTime) {
		this.useTime = useTime;
	}

	/**
	 * 获取：工作量
	 */
	public Float getWorkload() {
		return workload;
	}

	/**
	 * 设置：工作量
	 */
	public void setWorkload(Float workload) {
		this.workload = workload;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
}
