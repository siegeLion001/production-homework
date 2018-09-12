package com.lhh.modules.banana.service.impl;

import com.lhh.modules.banana.dao.BananaDao;
import com.lhh.modules.banana.entity.BananaEntity;
import com.lhh.modules.banana.service.BananaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service("bananaService")
public class BananaServiceImpl implements BananaService {
	@Autowired
	private BananaDao bananaDao;
	
	@Override
	public BananaEntity queryObject(Integer id){
		return bananaDao.queryObject(id);
	}
	
	@Override
	public List<BananaEntity> queryList(Map<String, Object> map){
		return bananaDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bananaDao.queryTotal(map);
	}
	
	@Override
	public void save(BananaEntity banana){
		bananaDao.save(banana);
	}
	
	@Override
	public void update(BananaEntity banana){
		bananaDao.update(banana);
	}
	
	@Override
	public void delete(Integer id){
		bananaDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bananaDao.deleteBatch(ids);
	}
	
}
