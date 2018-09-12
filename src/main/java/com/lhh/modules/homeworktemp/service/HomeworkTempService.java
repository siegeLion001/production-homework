package com.lhh.modules.homeworktemp.service;

import com.lhh.modules.homework.entity.HomeworkEntity;

import java.util.List;
import java.util.Map;

/**
 * 作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-23 21:38:23
 */
public interface HomeworkTempService {

    HomeworkEntity queryObject(Integer id);

    List<HomeworkEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(HomeworkEntity homeworkTemp);

    void update(HomeworkEntity homeworkTemp);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);
}
