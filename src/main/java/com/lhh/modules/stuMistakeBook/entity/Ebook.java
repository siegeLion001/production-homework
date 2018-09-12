package com.lhh.modules.stuMistakeBook.entity;

import java.util.Date;
import java.util.List;

/**
 * Ebook  章节树
 *
 * @ClassName: Ebook
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.modules.stuMistakeBook.entity
 * @CreateTime: 2018-06-25
 */
public class Ebook {
    /*章节id*/
    private String id;
    /*父节点*/
    private String parentId;
    /*册别id*/
    private String ebook;
    /*章节名称*/
    private String name;
    /**/
    private Integer startPage;
    /**/
    private String displayPage;
    /*子节点*/
    private List<Ebook> nodes;


    //----   自定义
    //题目内容
    private List<String> topContent;
    //题目解析
    private List<String> topAnalysis;
    //正确答案
    private List<String> correctAnswer;
    //题目类型
    private Integer topType;
    //错误原因
    private String mistakeReason;
    //难度
    private Integer difficulty;
    //创建日期
    private Date createTime;
    //修改日期
    private Date updateTime;
    /*是否掌握*/
    private Integer isGrasp;
    /* 待复习统计 */
    private Integer unGraspCount = 0;
    /* 已掌握数量统计 */
    private Integer graspCount = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getEbook() {
        return ebook;
    }

    public void setEbook(String ebook) {
        this.ebook = ebook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public String getDisplayPage() {
        return displayPage;
    }

    public void setDisplayPage(String displayPage) {
        this.displayPage = displayPage;
    }

    public List<Ebook> getNodes() {
        return nodes;
    }

    public void setNodes(List<Ebook> nodes) {
        this.nodes = nodes;
    }

    public List<String> getTopContent() {
        return topContent;
    }

    public void setTopContent(List<String> topContent) {
        this.topContent = topContent;
    }

    public List<String> getTopAnalysis() {
        return topAnalysis;
    }

    public void setTopAnalysis(List<String> topAnalysis) {
        this.topAnalysis = topAnalysis;
    }

    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(List<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getTopType() {
        return topType;
    }

    public void setTopType(Integer topType) {
        this.topType = topType;
    }

    public String getMistakeReason() {
        return mistakeReason;
    }

    public void setMistakeReason(String mistakeReason) {
        this.mistakeReason = mistakeReason;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsGrasp() {
        return isGrasp;
    }

    public void setIsGrasp(Integer isGrasp) {
        this.isGrasp = isGrasp;
    }

    public Integer getUnGraspCount() {

        for (Ebook node : nodes) {
            Integer count = node.getUnGraspCount();
            this.unGraspCount = count+unGraspCount;
        }

        return unGraspCount;
    }

    public void setUnGraspCount(Integer unGraspCount) {
        this.unGraspCount = unGraspCount;
    }

    public Integer getGraspCount() {

        for (Ebook node : nodes) {
            Integer count = node.getGraspCount();
            this.graspCount = count+graspCount;
        }
        return graspCount;
    }

    public void setGraspCount(Integer graspCount) {
        this.graspCount = graspCount;
    }
}