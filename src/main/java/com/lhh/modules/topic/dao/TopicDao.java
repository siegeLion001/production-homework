package com.lhh.modules.topic.dao;

import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.sys.dao.BaseDao;
import com.lhh.modules.topic.domain.ClassWrongItem;
import com.lhh.modules.topic.domain.TopicVo;
import com.lhh.modules.topic.entity.TopicEntity;

import java.util.List;
import java.util.Map;


/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
public interface TopicDao extends BaseDao<TopicEntity> {

    void delete4Map(Map<String, Object> params);

    List<TopicVo> queryList4Vo(Map<String, Object> params);

    List<ClassWrongItem> classWrongTopics(Map<String, Object> params);

    int classWrongTopicsTotal(Map<String, Object> params);

    List<StuTopicVo> stuWrongTopics(Map<String, Object> params);

    int stuWrongTopicsTotal(Map<String, Object> params);

}
