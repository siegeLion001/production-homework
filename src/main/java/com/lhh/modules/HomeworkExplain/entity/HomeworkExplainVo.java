package com.lhh.modules.HomeworkExplain.entity;

import com.lhh.common.validator.group.AddGroup;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 作业讲解表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-19 11:49:07
 */
public class HomeworkExplainVo extends HomeworkExplainEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/* 全体学生 错误学生  指定学生 字段标识*/
	@NotBlank(groups = AddGroup.class)
	private Integer commentedStatus;

	/* key 班级id  list 学生idList */
	private Map<String, List<String>> stuIdMap;

	public Map<String, List<String>> getStuIdMap() {
		return stuIdMap;
	}

	public void setStuIdMap(Map<String, List<String>> stuIdMap) {
		this.stuIdMap = stuIdMap;
	}

	public Integer getCommentedStatus() {
		return commentedStatus;
	}

	public void setCommentedStatus(Integer commentedStatus) {
		this.commentedStatus = commentedStatus;
	}
}
