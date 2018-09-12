package com.lhh.modules.HomeworkExplainRel.service;


import com.lhh.modules.HomeworkExplainRel.entity.HomeworkExplainRelEntity;

import java.util.List;
import java.util.Map;

/**
 * 作业讲解关联表

 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-19 11:49:03
 */
public interface HomeworkExplainRelService {
	
	HomeworkExplainRelEntity queryObject(Integer id);
	
	List<HomeworkExplainRelEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HomeworkExplainRelEntity homeworkExplainRel);
	
	void update(HomeworkExplainRelEntity homeworkExplainRel);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	void saveBatch(List<HomeworkExplainRelEntity> relList);
}
