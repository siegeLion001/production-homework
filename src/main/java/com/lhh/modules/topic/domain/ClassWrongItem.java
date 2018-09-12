package com.lhh.modules.topic.domain;

import com.lhh.modules.stutopic.entity.StuTopicEntity;
import com.lhh.modules.topic.entity.TopicEntity;

import java.util.Date;
import java.util.List;

public class ClassWrongItem extends TopicEntity {

    //创建时间
    //题目最后一次使用时间
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 错误率
     */
    private Float wrongRate;
    /**
     * 错题数
     */
    private Integer wrong;
    /**
     * 总的答题数
     */
    private Integer total;

    public Integer getWrong() {
        return wrong;
    }

    public void setWrong(Integer wrong) {
        this.wrong = wrong;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    List<StuTopicEntity> stuTopicList;

    public List<StuTopicEntity> getStuTopicList() {
        return stuTopicList;
    }

    public void setStuTopicList(List<StuTopicEntity> stuTopicList) {
        this.stuTopicList = stuTopicList;
    }

    public Float getWrongRate() {
        return wrongRate;
    }

    public void setWrongRate(Float wrongRate) {
        this.wrongRate = wrongRate;
    }
}
