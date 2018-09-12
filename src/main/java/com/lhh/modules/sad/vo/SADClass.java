package com.lhh.modules.sad.vo;

import java.lang.reflect.Type;

public class SADClass implements Type {

    private SADUser[] teachers;

    private SADUser[] students;

    private String section;

    private String name;

    private String type;

    private SADGrade grade;

    private SADInfo sadInfo;

    public SADUser[] getTeachers() {
        return teachers;
    }

    public void setTeachers(SADUser[] teachers) {
        this.teachers = teachers;
    }

    public SADUser[] getStudents() {
        return students;
    }

    public void setStudents(SADUser[] students) {
        this.students = students;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SADGrade getGrade() {
        return grade;
    }

    public void setGrade(SADGrade grade) {
        this.grade = grade;
    }

    public SADInfo getSadInfo() {
        return sadInfo;
    }

    public void setSadInfo(SADInfo sadInfo) {
        this.sadInfo = sadInfo;
    }
}
