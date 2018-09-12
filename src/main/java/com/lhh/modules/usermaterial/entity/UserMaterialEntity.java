package com.lhh.modules.usermaterial.entity;

import java.io.Serializable;


/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-31 17:25:32
 */
public class UserMaterialEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer userMaterialId;
    //教师id
    private String tchId;
    //教材id
    private String bookId;
    //教材名称
    private String bookName;
    //学科
    private String subject;
    //年级
    private String grade;
    //班级id 目前无用
    private String classId;

    /**
     * 获取：主键
     */
    public Integer getUserMaterialId() {
        return userMaterialId;
    }

    /**
     * 设置：主键
     */
    public void setUserMaterialId(Integer userMaterialId) {
        this.userMaterialId = userMaterialId;
    }

    /**
     * 获取：教师id
     */
    public String getTchId() {
        return tchId;
    }

    /**
     * 设置：教师id
     */
    public void setTchId(String tchId) {
        this.tchId = tchId;
    }

    /**
     * 获取：教材id
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 设置：教材id
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取：教材名称
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置：教材名称
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取：学科
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置：学科
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取：年级
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置：年级
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取：班级id 目前无用
     */
    public String getClassId() {
        return classId;
    }

    /**
     * 设置：班级id 目前无用
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }
}
