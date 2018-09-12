package com.lhh.modules.sys.dao;

import com.lhh.modules.sys.entity.SysRoleDeptEntity;

import java.util.List;

/**
 * 角色与部门对应关系
 * 
 * @author ma.zd
 * @email mazengdi@lanhaihui.net
 * @date 2017年6月21日 23:33:46
 */
public interface SysRoleDeptDao extends BaseDao<SysRoleDeptEntity> {
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<Long> queryDeptIdList(Long roleId);
}
