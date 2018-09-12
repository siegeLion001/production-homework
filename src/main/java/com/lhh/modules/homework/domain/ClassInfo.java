package com.lhh.modules.homework.domain;

public class ClassInfo {
    //班级id  暂时废弃
    private String classId;
    //学生id数组
    private String[] stuIds;
    //教师id数组
    private String[] tchIds;
    /*为批改/批改人数  统计  */
    private int number;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String[] getStuIds() {
        return stuIds;
    }

    public void setStuIds(String[] stuIds) {
        this.stuIds = stuIds;
    }

    public String[] getTchIds() {
        return tchIds;
    }

    public void setTchIds(String[] tchIds) {
        this.tchIds = tchIds;
    }

    public int getNumber() {
        if (this.stuIds !=null && this.tchIds != null){
            int stuLength = this.stuIds.length;
            int tchLength = this.tchIds.length;
            this.number = stuLength >= tchLength ? stuLength:tchLength;
            return number;
        } else if(this.stuIds !=null){
            return this.stuIds.length;
        }else if(this.tchIds !=null){
            return this.tchIds.length;
        }
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
