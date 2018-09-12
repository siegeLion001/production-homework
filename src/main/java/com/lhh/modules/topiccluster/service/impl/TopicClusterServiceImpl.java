package com.lhh.modules.topiccluster.service.impl;

import com.lhh.modules.topiccluster.dao.TopicClusterDao;
import com.lhh.modules.topiccluster.domain.TopicClusterVo;
import com.lhh.modules.topiccluster.entity.TopicClusterEntity;
import com.lhh.modules.topiccluster.service.TopicClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("topicClusterService")
public class TopicClusterServiceImpl implements TopicClusterService {
    @Autowired
    private TopicClusterDao topicClusterDao;

    @Override
    public TopicClusterEntity queryObject(Integer id) {
        return topicClusterDao.queryObject(id);
    }

    @Override
    public List<TopicClusterEntity> queryList(Map<String, Object> map) {
        return topicClusterDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return topicClusterDao.queryTotal(map);
    }

    @Override
    public void save(TopicClusterEntity topicCluster) {
        topicClusterDao.save(topicCluster);
    }

    @Override
    public void update(TopicClusterEntity topicCluster) {
        topicClusterDao.update(topicCluster);
    }

    @Override
    public void delete(Integer id) {
        topicClusterDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        topicClusterDao.deleteBatch(ids);
    }

    @Override
    public void delete4Map(Map<String, Object> params) {
        topicClusterDao.delete4Map(params);
    }

    @Override
    public List<TopicClusterVo> queryList4Vo(Map<String, Object> map) {
        return topicClusterDao.queryList4Vo(map);
    }

}
