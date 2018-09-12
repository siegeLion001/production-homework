package com.lhh.modules.homework.domain;

import com.lhh.modules.homework.entity.HomeworkEntity;
import com.lhh.modules.topiccluster.domain.TopicClusterVo;

import java.util.List;

/**
 * FileName: HomeworkVo
 * Author: cuihp
 * Date: 2018/5/30
 * Description: 作业表vo类
 */
public class HomeworkVo extends HomeworkEntity {

    private List<TopicClusterVo> topicClusterList;

    public List<TopicClusterVo> getTopicClusterList() {
        return topicClusterList;
    }

    public void setTopicClusterList(List<TopicClusterVo> topicClusterList) {
        this.topicClusterList = topicClusterList;
    }

    /**
     * 一下是统计结果
     */

    //作业未交人数
    private Integer unCommittedCount;

    //作业已交人数
    private Integer submitCount;

    //未批改人数
    private Integer noPerusel;
    //已批改人数
    private Integer perusel;

    public Integer getNoPerusel() {
        return noPerusel;
    }

    public void setNoPerusel(Integer noPerusel) {
        this.noPerusel = noPerusel;
    }

    public Integer getPerusel() {
        return perusel;
    }

    public void setPerusel(Integer perusel) {
        this.perusel = perusel;
    }

    public Integer getUnCommittedCount() {
        return unCommittedCount;
    }

    public void setUnCommittedCount(Integer unCommittedCount) {
        this.unCommittedCount = unCommittedCount;
    }

    public Integer getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
    }
}
