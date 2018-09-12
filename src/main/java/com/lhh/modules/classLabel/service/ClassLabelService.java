package com.lhh.modules.classLabel.service;


import java.util.List;
import java.util.Map;

import com.lhh.modules.classLabel.entity.ClassLabelEntity;

/**
 * 标签表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-09 13:50:28
 */
public interface ClassLabelService {
	
	ClassLabelEntity queryObject(Integer labelid);
	
	List<ClassLabelEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClassLabelEntity classLabel);
	
	void update(ClassLabelEntity classLabel);
	
	void delete(Integer labelid);
	
	void deleteBatch(Integer[] labelids);

    List<ClassLabelEntity> queryLabelList(Map<String,Object> params);
}
