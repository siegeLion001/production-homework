package com.lhh.modules.topic.service;

import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.topic.domain.ClassWrongItem;
import com.lhh.modules.topic.domain.TopicVo;
import com.lhh.modules.topic.domain.WrongTopic;
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
public interface TopicService {

    TopicEntity queryObject(Integer id);

    List<TopicEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(TopicEntity topic);

    void update(TopicEntity topic);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    void batchSave(List<TopicEntity> topicList);

    void delete4Map(Map<String, Object> params);

    List<TopicVo> queryList4Vo(Map<String, Object> map);

    List<WrongTopic> queryClassWrongTopicList(Map<String, Object> map);

    /**
     * 班级错题本 查询某个班级全部学生的错题信息
     *
     * @param map
     * @return
     */
    List<ClassWrongItem> classWrongTopics(Map<String, Object> map);

    int classWrongTopicsTotal(Map<String, Object> map);

    /**
     * 班级错题本 查询学生的错题信息
     *
     * @param params
     * @return
     */
    List<StuTopicVo> stuWrongTopics(Map<String, Object> params);

    int stuWrongTopicsTotal(Map<String, Object> params);

}
