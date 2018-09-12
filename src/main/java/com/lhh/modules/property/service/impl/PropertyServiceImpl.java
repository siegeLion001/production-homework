package com.lhh.modules.property.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lhh.modules.property.dao.PropertyDao;
import com.lhh.modules.property.entity.PropertyEntity;
import com.lhh.modules.property.service.PropertyService;



@Service("propertyService")
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	private PropertyDao propertyDao;
	
	@Override
	public PropertyEntity queryObject(Integer id){
		return propertyDao.queryObject(id);
	}
	
	@Override
	public List<PropertyEntity> queryList(Map<String, Object> map){
		return propertyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return propertyDao.queryTotal(map);
	}
	
	@Override
	public void save(PropertyEntity property){
		propertyDao.save(property);
	}
	
	@Override
	public void update(PropertyEntity property){
		propertyDao.update(property);
	}
	
	@Override
	public void delete(Integer id){
		propertyDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		propertyDao.deleteBatch(ids);
	}
	/**
	 * 添加标段时下拉框显示标段属性信息
	 * ycc
	 * @return
	 */
	@Override
	public List<PropertyEntity> findAll() {
		// TODO Auto-generated method stub
		return propertyDao.findAll();
	}
	/**
	 * code拼接标段编号
	 * @param ids
	 * @return
	 * ycc
	 */
	@Override
	public String findNumber(String[] ids) {
		// TODO Auto-generated method stub
		return propertyDao.findNumber(ids);
	}
	/**
	 * 根据当前业务员的用户id查询对应的车间id
	 * @param userId
	 * @return
	 * ycc
	 */
	@Override
	public int getPlantId(Long userId) {
		// TODO Auto-generated method stub
		return propertyDao.getPlantId(userId);
	}
	/**
	 * 获取标段价格
	 * @return
	 * ycc
	 */
	@Override
	public String getPrice() {
		// TODO Auto-generated method stub
		return propertyDao.getPrice();
	}

	@Override
	public List<PropertyEntity> findListByType(Integer type) {
		return propertyDao.findListByType(type);
	}
	/**
	 * 根据类型名称查对应的code
	 * @param numberType
	 * @return
	 * ycc
	 */
	@Override
	public PropertyEntity getCodeByType(String numberType) {
		// TODO Auto-generated method stub
		return propertyDao.getCodeByType(numberType);
	}
	/**
	 * 根据区域名称查对应的code
	 * @param numberArea
	 * @return
	 * ycc
	 */
	@Override
	public PropertyEntity getCodeByArea(String numberArea) {
		// TODO Auto-generated method stub
		return propertyDao.getCodeByArea(numberArea);
	}
	
	@Override
	public PropertyEntity getCodeByPlant(String plant) {
		// TODO Auto-generated method stub
		return propertyDao.getCodeByPlant(plant);
	}
	
	@Override
	public String getPlantCode(int plantId) {
		// TODO Auto-generated method stub
		return propertyDao.getPlantCode(plantId);
	}

	
	
}
