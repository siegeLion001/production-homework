package com.lhh.modules.stuhomework.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lhh.modules.homework.domain.ReadContent;
import com.lhh.modules.stuhomework.entity.StuHomeworkEntity;
import com.lhh.modules.stutopiccluster.domain.StuTopicClusterVo;
import com.lhh.modules.topiccluster.domain.TopicClusterVo;


/**
 * 学生作业表
 *
 * @author wangcheng
 * @date 2018年5月16日 下午8:23:37
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StuHomeworkVo extends StuHomeworkEntity {

    // 主观题数量
    private Integer subjective;
    // 客观题数量
    private Integer objective;

    /**
     * 分数详情 与数据库无关
     */
    private Map actualScoreDetail;

    //学生作业id
    private Integer stuHomeworkId;
    /**
     * 是否公开  该字段与数据库无关 是提供给app的标识
     */
    private Boolean isPublish;
    /**
     * 教师id
     */
    private String tchId;
    /**
     * 班级id
     */
    private String[] classIds;
    /**
     * 科目信息
     */
    private String subject;
    /**
     * 学段信息
     */
    private String period;
    /**
     * 册别信息
     */
    private String book;
    /**
     * 作业类型  1 日常作业 2 口语评测 3 同步练习 4 阶段检测
     */
    private String type;
    /**
     * 标题
     */
    private String title;
    /**
     * 备注
     */
    private String remark;
    /**
     * 题目内容
     */
    private List<String> content;
    /**
     * 答题卡带答案
     */
    private TopicClusterVo[] answerCard;
    /**
     * 完成时间
     */
    private Date finishTime;
    /**
     * 发布时间
     */
    private Date releaseTime;
    /**
     * 公布时间
     */
    private Date publishTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 作业状态（为批改/已批改[归档]）
     */
    private Integer state;
    /**
     * 正确答案(图片)
     */
    private List<String> answerImgs;
    /**
     * 逻辑删除标示
     */
    private Integer delMark;
    /**
     * 语音题内容 存放特殊json对象
     */
    private ReadContent readContent;
    /**
     * 1 中文评测 2 自由评测 3 英文评测
     */
    private String tinyType;

    /**
     * stu题簇数组
     */
    private List<StuTopicClusterVo> topicClusterList;
    /**
     * 作业总人数
     */
    private Integer headCount;
    /**
     * 已提交人数
     */
    private Integer submitCount;
    /**
     * 未批阅人数
     */
    private Integer notReadCount;
    /**
     * 订正人数
     */
    private Integer correctCount;

    private boolean allTopicMark;

    private Integer rightCount;

    private Float actualScore;

    public Integer getRightCount() {
        return rightCount;
    }

    public void setRightCount(Integer rightCount) {
        this.rightCount = rightCount;
    }

    @Override
    public Float getActualScore() {
        return actualScore;
    }

    @Override
    public void setActualScore(Float actualScore) {
        this.actualScore = actualScore;
    }

    /**
     * 批改类型 1学生自批 2 班级互批 3 组长批 4组内互批
     */
    private Integer correctType;

    public Integer getStuHomeworkId() {
        return stuHomeworkId;
    }

    public void setStuHomeworkId(Integer stuHomeworkId) {
        this.stuHomeworkId = stuHomeworkId;
    }

    public String getTchId() {
        return tchId;
    }

    public void setTchId(String tchId) {
        this.tchId = tchId;
    }

    public String[] getClassIds() {
        return classIds;
    }

    public void setClassIds(String[] classIds) {
        this.classIds = classIds;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public TopicClusterVo[] getAnswerCard() {
        return answerCard;
    }

    public void setAnswerCard(TopicClusterVo[] answerCard) {
        this.answerCard = answerCard;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<String> getAnswerImgs() {
        return answerImgs;
    }

    public void setAnswerImgs(List<String> answerImgs) {
        this.answerImgs = answerImgs;
    }

    public Integer getDelMark() {
        return delMark;
    }

    public void setDelMark(Integer delMark) {
        this.delMark = delMark;
    }

    public ReadContent getReadContent() {
        return readContent;
    }

    public void setReadContent(ReadContent readContent) {
        this.readContent = readContent;
    }

    public String getTinyType() {
        return tinyType;
    }

    public void setTinyType(String tinyType) {
        this.tinyType = tinyType;
    }

    public Boolean getPublish() {
        return isPublish;
    }

    public void setPublish(Boolean publish) {
        isPublish = publish;
    }

    public List<StuTopicClusterVo> getTopicClusterList() {
        return topicClusterList;
    }

    public void setTopicClusterList(List<StuTopicClusterVo> topicClusterList) {
        this.topicClusterList = topicClusterList;
    }

    public Integer getHeadCount() {
        return headCount;
    }

    public void setHeadCount(Integer headCount) {
        this.headCount = headCount;
    }

    public Integer getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
    }

    public Integer getNotReadCount() {
        return notReadCount;
    }

    public void setNotReadCount(Integer notReadCount) {
        this.notReadCount = notReadCount;
    }

    public Integer getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(Integer correctCount) {
        this.correctCount = correctCount;
    }

    public Map getActualScoreDetail() {
        return actualScoreDetail;
    }

    public void setActualScoreDetail(Map actualScoreDetail) {
        this.actualScoreDetail = actualScoreDetail;
    }

    public boolean isAllTopicMark() {
        return allTopicMark;
    }

    public void setAllTopicMark(boolean allTopicMark) {
        this.allTopicMark = allTopicMark;
    }

    public Integer getSubjective() {
        return subjective;
    }

    public void setSubjective(Integer subjective) {
        this.subjective = subjective;
    }

    public Integer getObjective() {
        return objective;
    }

    public void setObjective(Integer objective) {
        this.objective = objective;
    }

    public Integer getCorrectType() {
        return correctType;
    }

    public void setCorrectType(Integer correctType) {
        this.correctType = correctType;
    }
}
