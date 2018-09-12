package com.lhh.modules.topiccluster.entity;

import java.io.Serializable;



/**
 * 学生作业表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-29 09:38:19
 */
public class TopicClusterEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//作业id
	private Integer homeworkId;
	//题目类型
	private Integer type;
	//题目内容
	private String content;
	//大题题号
	private String bNum;
	//大题分数
	private Float bScore;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：作业id
	 */
	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}
	/**
	 * 获取：作业id
	 */
	public Integer getHomeworkId() {
		return homeworkId;
	}
	/**
	 * 设置：题目类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：题目类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：题目内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：题目内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：大题题号
	 */
	public void setBNum(String bNum) {
		this.bNum = bNum;
	}
	/**
	 * 获取：大题题号
	 */
	public String getBNum() {
		return bNum;
	}
	/**
	 * 设置：大题分数
	 */
	public void setBScore(Float bScore) {
		this.bScore = bScore;
	}
	/**
	 * 获取：大题分数
	 */
	public Float getBScore() {
		return bScore;
	}
}
