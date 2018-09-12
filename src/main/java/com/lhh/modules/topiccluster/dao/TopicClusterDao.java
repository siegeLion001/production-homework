package com.lhh.modules.topiccluster.dao;

import com.lhh.modules.sys.dao.BaseDao;
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
public interface TopicClusterDao extends BaseDao<TopicClusterEntity> {

    void delete4Map(Map<String, Object> params);

    List<TopicClusterVo> queryList4Vo(Map<String, Object> params);

}
