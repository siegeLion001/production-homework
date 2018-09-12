package com.lhh.modules.classvisual.dao;

import java.util.HashMap;
import java.util.List;

import com.lhh.modules.classvisual.entity.ClassVisualEntity;
import com.lhh.modules.sys.dao.BaseDao;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
public interface ClassVisualDao extends BaseDao<ClassVisualEntity> {


    // 批量插入
    void batchSave(List<? extends ClassVisualEntity> classVisualEntities);

    void delete4Map(HashMap<String,Object> params);
}
