package com.lhh.modules.classLabel.dao;


import java.util.List;
import java.util.Map;

import com.lhh.modules.classLabel.entity.ClassLabelEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * 标签表
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-09 13:50:28
 */
public interface ClassLabelDao extends BaseDao<ClassLabelEntity> {

    List<ClassLabelEntity> queryLabelList(Map<String,Object> params);
}
