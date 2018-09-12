package com.lhh.modules.stuhomework.domain;

import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterVo;

/**
 * 订正和打分二合一
 */
public class ScoreVo extends StuHomeworkEntity {

    //订正类型  1 按人批改 打分  2 按题批改打分
    private Integer scoreType;

    //学生作业id
    private Integer stuHomeworkId;

    private String type;

    private StuTopicClusterVo[] topicClusterList;

    public Integer getStuHomeworkId() {
        return stuHomeworkId;
    }

    public void setStuHomeworkId(Integer stuHomeworkId) {
        this.stuHomeworkId = stuHomeworkId;
    }

    public StuTopicClusterVo[] getTopicClusterList() {
        return topicClusterList;
    }

    public void setTopicClusterList(StuTopicClusterVo[] topicClusterList) {
        this.topicClusterList = topicClusterList;
    }

    public Integer getScoreType() {
        return scoreType;
    }

    public void setScoreType(Integer scoreType) {
        this.scoreType = scoreType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
