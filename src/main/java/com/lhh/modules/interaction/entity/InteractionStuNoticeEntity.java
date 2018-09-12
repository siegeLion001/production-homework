package com.lhh.modules.interaction.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 记录已读，未读信息
 * 
 * @author menglimei
 * @email menglimei@lanhaihui.net
 * @date 2018-09-05 17:14:36
 */
public class InteractionStuNoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer stuNoticeId;
	//公告id
	private Integer noticeId;
	//学生id
	private String stuId;
	//是否已读 Y:已读 N：未读
	private String readYn;
	//创建时间
	private Date createTime;
	//班级id
	private String classId;

	/**
	 * 设置：
	 */
	public void setStuNoticeId(Integer stuNoticeId) {
		this.stuNoticeId = stuNoticeId;
	}
	/**
	 * 获取：
	 */
	public Integer getStuNoticeId() {
		return stuNoticeId;
	}
	/**
	 * 设置：公告id
	 */
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	/**
	 * 获取：公告id
	 */
	public Integer getNoticeId() {
		return noticeId;
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
	 * 设置：是否已读 Y:已读 N：未读
	 */
	public void setReadYn(String readYn) {
		this.readYn = readYn;
	}
	/**
	 * 获取：是否已读 Y:已读 N：未读
	 */
	public String getReadYn() {
		return readYn;
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
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	
}
