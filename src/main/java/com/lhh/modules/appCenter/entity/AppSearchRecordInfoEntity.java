package com.lhh.modules.appCenter.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-29 13:53:13
 */
public class AppSearchRecordInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//搜索记录id
	private Integer appSearchRecordId;
	//1:教师端，2：学生端
	private String client;
	//搜索内容
	private String content;
	//搜索次数
	private Integer count;
	//创建时间
	private Date createTime;

	/**
	 * 设置：搜索记录id
	 */
	public void setAppSearchRecordId(Integer appSearchRecordId) {
		this.appSearchRecordId = appSearchRecordId;
	}
	/**
	 * 获取：搜索记录id
	 */
	public Integer getAppSearchRecordId() {
		return appSearchRecordId;
	}
	/**
	 * 设置：1:教师端，2：学生端
	 */
	public void setClient(String client) {
		this.client = client;
	}
	/**
	 * 获取：1:教师端，2：学生端
	 */
	public String getClient() {
		return client;
	}
	/**
	 * 设置：搜索内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：搜索内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：搜索次数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：搜索次数
	 */
	public Integer getCount() {
		return count;
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
