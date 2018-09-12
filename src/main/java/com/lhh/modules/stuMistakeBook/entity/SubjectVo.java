package com.lhh.modules.stuMistakeBook.entity;

/**
 * 科目Vo
 *
 * @ClassName: SubjectVo
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.modules.stuMistakeBook.entity
 * @CreateTime: 2018-07-25
 */
public class SubjectVo {
    /**科目id*/
    private String _id;
    /**科目名称*/
    private String name;
    /**科目下待复习题目数*/
    private Integer unGraspCount = 0;
    /**科目下已掌握题目数*/
    private Integer graspCount = 0;
    /**科目id Mine*/
    private String subjctId;
    /**学生Id*/
    private String stuId;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
        this.subjctId = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnGraspCount() {
        return unGraspCount;
    }

    public void setUnGraspCount(Integer unGraspCount) {
        this.unGraspCount = unGraspCount;
    }

    public Integer getGraspCount() {
        return graspCount;
    }

    public void setGraspCount(Integer graspCount) {
        this.graspCount = graspCount;
    }

    public String getSubjectId() {
        return subjctId;
    }

    public void setSubjectId(String subjectId) {
        this.subjctId = subjectId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}