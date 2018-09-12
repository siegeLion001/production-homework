package com.lhh.modules.property.dao;

import java.util.List;

import com.lhh.modules.property.entity.PropertyEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 标段属性表
 * 
 * @author ma.zd
 * @email mazengdi@lanhaihui.net
 * @date 2018-03-12 11:07:44
 */
public interface PropertyDao extends BaseDao<PropertyEntity> {
	/**
	 * 添加标段时下拉框显示标段属性信息
	 * ycc
	 * @return
	 */
	List<PropertyEntity> findAll();
	

	/**
	 * code拼接标段编号
	 * @param ids
	 * @return
	 * ycc
	 */
	String findNumber(Object[] id);
	/**
	 * 根据当前业务员的用户id查询对应的车间id
	 * @param userId
	 * @return
	 * ycc
	 */
	int getPlantId(Long userId);
	/**
	 * 获取标段价格
	 * @return
	 * ycc
	 */
	String getPrice();
	
	/**
	 * 
	 * 根据类型查询 
	 * @param type
	 * @return
	 */
	List<PropertyEntity> findListByType(Integer type);
	/**
	 * 根据类型名称查对应的code
	 * @param numberType
	 * @return
	 * ycc
	 */
	PropertyEntity getCodeByType(String numberType);
	/**
	 * 根据区域名称查对应的code
	 * @param numberArea
	 * @return
	 * ycc
	 */
	PropertyEntity getCodeByArea(String numberArea);
	
	/**
	 * 根据车间名称查对应的id
	 * @param numberArea
	 * @return
	 * ycc
	 */
	PropertyEntity getCodeByPlant(String plant);
	/**
	 * -查询车间code 
	 * @param id
	 * @return
	 */
	String getPlantCode(int id);
}
