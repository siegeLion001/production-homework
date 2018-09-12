package com.lhh.modules.banana.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-28 11:13:03
 */
public class BananaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//图片路径
	private String url;
	//状态  1,使用  2,不使用
	private Integer state;
	//图片标题
	private String title;

	private String connect;
	//客户端  1,教师端 2,学生端 0,通用
	private Integer client;
	//创建日期
	private Date createTime;
	//修改日期
	private Date updateTime;
	//内容(文章)
	private String content;

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
	 * 设置：图片路径
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：图片路径
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：状态  1,使用  2,不使用
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态  1,使用  2,不使用
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：图片标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：图片标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：客户端  1,教师端 2,学生端 0,通用
	 */
	public void setClient(Integer client) {
		this.client = client;
	}
	/**
	 * 获取：客户端  1,教师端 2,学生端 0,通用
	 */
	public Integer getClient() {
		return client;
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
	/**
	 * 设置：修改日期
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改日期
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public String getConnect() {
		return connect;
	}

	public void setConnect(String connect) {
		this.connect = connect;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
