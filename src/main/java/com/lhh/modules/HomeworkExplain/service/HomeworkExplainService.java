package com.lhh.modules.HomeworkExplain.service;


import com.lhh.modules.HomeworkExplain.entity.HomeworkExplainEntity;
import com.lhh.modules.HomeworkExplainRel.entity.HomeworkExplainRelEntity;

import java.util.List;
import java.util.Map;

/**
 * 作业讲解表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-19 11:49:07
 */
public interface HomeworkExplainService {
	
	HomeworkExplainEntity queryObject(Integer id);
	
	List<HomeworkExplainEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HomeworkExplainEntity homeworkExplain);
	
	void update(HomeworkExplainEntity homeworkExplain);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void saveExplain(HomeworkExplainEntity homeworkExplainEntity, List<HomeworkExplainRelEntity> relEntityList);
}
