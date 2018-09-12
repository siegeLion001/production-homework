package com.lhh.modules.resourcecollect.dao;


import com.lhh.modules.resourcecollect.entity.ResourceCollectEntity;
import com.lhh.modules.sys.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 资源收藏表 用于表示当前用户收藏的外部资源
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-09 15:42:12
 */
public interface ResourceCollectDao extends BaseDao<ResourceCollectEntity> {

    List<String> queryResourceIds(Map<String, Object> map);

}
