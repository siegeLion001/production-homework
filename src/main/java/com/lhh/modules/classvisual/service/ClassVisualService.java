package com.lhh.modules.classvisual.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lhh.modules.classvisual.entity.ClassVisualEntity;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-11 09:14:29
 */
public interface ClassVisualService {

    ClassVisualEntity queryObject(Integer id);

    List<ClassVisualEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(ClassVisualEntity classVisual);

    void update(ClassVisualEntity classVisual);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    // 批量插入
    void batchSave(List<? extends ClassVisualEntity> classVisualEntities);

    void delete4Map(HashMap<String,Object> params);
}
