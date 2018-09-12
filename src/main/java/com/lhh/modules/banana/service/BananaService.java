package com.lhh.modules.banana.service;


import com.lhh.modules.banana.entity.BananaEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-28 11:13:03
 */
public interface BananaService {
	
	BananaEntity queryObject(Integer id);
	
	List<BananaEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BananaEntity banana);
	
	void update(BananaEntity banana);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
