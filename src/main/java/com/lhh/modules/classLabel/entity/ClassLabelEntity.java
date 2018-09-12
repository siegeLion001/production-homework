package com.lhh.modules.classLabel.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 标签表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-09 13:50:28
 */
public class ClassLabelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer labelId;
	//标签名称
	private String labelName;
	//类型
	private Integer type;
    /*创建人*/
    private String createId;
	//创建时间
	private Date createTime;
	//备用字段
	private String reserve;

	/**
	 * 设置：主键
	 */
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getLabelId() {
		return labelId;
	}
	/**
	 * 设置：标签名称
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	/**
	 * 获取：标签名称
	 */
	public String getLabelName() {
		return labelName;
	}
	/**
	 * 设置：类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public Integer getType() {
		return type;
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
	/**
	 * 设置：备用字段
	 */
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	/**
	 * 获取：备用字段
	 */
	public String getReserve() {
		return reserve;
	}

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }
}
