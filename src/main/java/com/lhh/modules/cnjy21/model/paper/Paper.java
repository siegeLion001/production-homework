package com.lhh.modules.cnjy21.model.paper;


import com.lhh.modules.cnjy21.model.common.Province;

import java.util.Date;
import java.util.List;

/**
 * 试题信息
 *
 * @author zhoushubing
 */
public class Paper {


    // 与21世纪无关 我方字段 是否收藏
    private Boolean collect;

    public Boolean getCollect() {
        return collect;
    }

    public void setCollect(Boolean collect) {
        this.collect = collect;
    }

    //    试卷ID
    private Integer id;

    //    试卷标题
    private String title;

    //    试卷类型
    private Integer type;

    //    试卷类型文本
    private String typeText;

    //    所属学段 (1:小学，2:初中，3:高中)
    private Integer stage;

    //    所属学科
    private Integer subjectId;

    //    教材版本ID
    private Integer versionId;

    //    教材
    private Integer bookId;

    //    试卷年份
    private Integer year;

    //试卷适用的章节
    private List categories;

    //试卷适用省份列表
    private Province[] provinces;

    //创建时间
    private Date createdAt;

    //最后更新时间
    private Date updatedAt;


    // 不知道干什么的
    private Integer term;
    // 不知道干什么的
    private String termText;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List getCategories() {
        return categories;
    }

    public void setCategories(List categories) {
        this.categories = categories;
    }

    public Province[] getProvinces() {
        return provinces;
    }

    public void setProvinces(Province[] provinces) {
        this.provinces = provinces;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getTermText() {
        return termText;
    }

    public void setTermText(String termText) {
        this.termText = termText;
    }
}
