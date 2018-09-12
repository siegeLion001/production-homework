package com.lhh.modules.topiccluster.service;

import com.lhh.modules.topiccluster.domain.TopicClusterVo;
import com.lhh.modules.topiccluster.entity.TopicClusterEntity;

import java.util.List;
import java.util.Map;

/**
 * 学生作业表
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-18 09:44:45
 */
public interface TopicClusterService {

    TopicClusterEntity queryObject(Integer id);

    List<TopicClusterEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(TopicClusterEntity topicCluster);

    void update(TopicClusterEntity topicCluster);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    void delete4Map(Map<String, Object> params);

    List<TopicClusterVo> queryList4Vo(Map<String, Object> map);

}
