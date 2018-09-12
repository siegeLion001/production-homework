package com.lhh.modules.topic.service.impl;

import com.lhh.modules.stutopic.dao.StuTopicDao;
import com.lhh.modules.stutopic.domain.CorrectAndErrorCount;
import com.lhh.modules.stutopic.domain.StuTopicVo;
import com.lhh.modules.topic.dao.TopicDao;
import com.lhh.modules.topic.domain.ClassWrongItem;
import com.lhh.modules.topic.domain.TopicVo;
import com.lhh.modules.topic.domain.WrongTopic;
import com.lhh.modules.topic.entity.TopicEntity;
import com.lhh.modules.topic.service.TopicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("topicService")
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private StuTopicDao stuTopicDao;

    @Override
    public TopicEntity queryObject(Integer id) {
        return topicDao.queryObject(id);
    }

    @Override
    public List<TopicEntity> queryList(Map<String, Object> map) {
        return topicDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return topicDao.queryTotal(map);
    }

    @Override
    public void save(TopicEntity topic) {
        topicDao.save(topic);
    }

    @Override
    public void update(TopicEntity topic) {
        topicDao.update(topic);
    }

    @Override
    public void delete(Integer id) {
        topicDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        topicDao.deleteBatch(ids);
    }

    @Override
    public void batchSave(List<TopicEntity> topicList) {
        topicDao.saveBatch(topicList);
    }

    @Override
    public void delete4Map(Map<String, Object> params) {
        topicDao.delete4Map(params);
    }

    @Override
    public List<TopicVo> queryList4Vo(Map<String, Object> map) {
        return topicDao.queryList4Vo(map);
    }

    @Override
    public List<WrongTopic> queryClassWrongTopicList(Map<String, Object> map) {
        List<TopicEntity> list = topicDao.queryList(map);
        List<WrongTopic> reList = new ArrayList<>();
        for (TopicEntity topicEntity : list) {
            WrongTopic wrongTopic = new WrongTopic();
            BeanUtils.copyProperties(topicEntity, wrongTopic);
            Map qm = new HashMap();
            qm.put("homeworkId", wrongTopic.getHomeworkId());
            qm.put("bNum", wrongTopic.getBNum());
            qm.put("sNum", wrongTopic.getSNum());
            // 计算正确率
            List<CorrectAndErrorCount> correctAndErrorCount = stuTopicDao.correctAndErrorCount(qm);
            int correct = 0, error = 0;
            for (CorrectAndErrorCount item : correctAndErrorCount) {
                if (item.getState().equals("correct")) {
                    correct = item.getCount();
                }
                if (item.getState().equals("error")) {
                    error = item.getCount();
                }
            }
            wrongTopic.setCorrect(correct);
            wrongTopic.setError(error);
            List stuTopicList = stuTopicDao.queryList(qm);
            wrongTopic.setStuTopics(stuTopicList);
            reList.add(wrongTopic);
        }
        return reList;
    }

    @Override
    public List<ClassWrongItem> classWrongTopics(Map<String, Object> map) {
        List<ClassWrongItem> classWrongItems = topicDao.classWrongTopics(map);
        for (ClassWrongItem classWrongItem : classWrongItems) {
            Map qm = new HashMap();
            String questionId = classWrongItem.getQuestionId();
            qm.put("questionId", questionId);
            qm.put("mark", 1);
            List list = stuTopicDao.queryList(qm);
            classWrongItem.setStuTopicList(list);
        }
        return classWrongItems;
    }

    @Override
    public int classWrongTopicsTotal(Map<String, Object> map) {
        return topicDao.classWrongTopicsTotal(map);
    }

    @Override
    public List<StuTopicVo> stuWrongTopics(Map<String, Object> params) {
        return topicDao.stuWrongTopics(params);
    }

    @Override
    public int stuWrongTopicsTotal(Map<String, Object> params) {
        return topicDao.stuWrongTopicsTotal(params);
    }

}
