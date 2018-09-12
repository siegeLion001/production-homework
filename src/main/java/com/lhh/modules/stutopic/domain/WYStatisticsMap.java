package com.lhh.modules.stutopic.domain;

import java.util.Map;

public class WYStatisticsMap {
    // 未批改
    private Integer stuTopicClusterUnCorrect;

    // 已批改
    private Integer stuTopicClusterCorrect;



    //教师订正
    private Integer tchClusterRevising;

    //学生修改
    private Integer stuClusterRevisinged;


    public Integer getTchClusterRevising() {
        return tchClusterRevising;
    }

    public void setTchClusterRevising(Integer tchClusterRevising) {
        this.tchClusterRevising = tchClusterRevising;
    }

    public Integer getStuClusterRevisinged() {
        return stuClusterRevisinged;
    }

    public void setStuClusterRevisinged(Integer stuClusterRevisinged) {
        this.stuClusterRevisinged = stuClusterRevisinged;
    }

    private Map<String, WYStatistics> map;

    public Integer getStuTopicClusterUnCorrect() {
        return stuTopicClusterUnCorrect;
    }

    public void setStuTopicClusterUnCorrect(Integer stuTopicClusterUnCorrect) {
        this.stuTopicClusterUnCorrect = stuTopicClusterUnCorrect;
    }

    public Integer getStuTopicClusterCorrect() {
        return stuTopicClusterCorrect;
    }

    public void setStuTopicClusterCorrect(Integer stuTopicClusterCorrect) {
        this.stuTopicClusterCorrect = stuTopicClusterCorrect;
    }

    public Map<String, WYStatistics> getMap() {
        return map;
    }

    public void setMap(Map<String, WYStatistics> map) {
        this.map = map;
    }

    public WYStatistics get(String sNum) {
        WYStatistics wyStatistics = map.get(sNum);
        if (wyStatistics == null) {
            wyStatistics = new WYStatistics();
            wyStatistics.setCorrect(0);
            wyStatistics.setUnCorrect(0);
            return wyStatistics;
        } else {
            return wyStatistics;
        }

    }
}
