package com.lhh.modules.topic.domain;

import com.lhh.modules.stutopic.entity.StuTopicEntity;
import com.lhh.modules.topic.entity.TopicEntity;

import java.util.List;

public class WrongTopic extends TopicEntity {

    private List<StuTopicEntity> stuTopics;
    // 正确的数量
    private Integer correct;
    // 错误的数量
    private Integer error;

    public List<StuTopicEntity> getStuTopics() {
        return stuTopics;
    }

    public void setStuTopics(List<StuTopicEntity> stuTopics) {
        this.stuTopics = stuTopics;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }
}
