package com.lhh.modules.sad.vo;


//                     "_id": "5b20841fc6ff2244fc3d3d66",
//                    "title": "防守打法",
//                    "brief": "手动阀是的",
//                    "section": "小学",
//                    "grade": {
//                    "_id": "5ab3ec078ae9a3cdcf922904",
//                    "name": "四年级",
//                    "belong": "小学",
//                    "order": 4
//                    },
//                    "subject": {
//                    "name": "化学",
//                    "_id": "5aa3ec078ae9a3cdcf922905"
//                    },
//                    "semester": "last",
//                    "publisher": "发斯蒂芬斯蒂芬"
public class SADBook {

    private String _id;
    private String title;
    private String brief;
    private String section;
    private SADGrade grade;
    private SADSubject subject;
    private String semester;
    private String publisher;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public SADGrade getGrade() {
        return grade;
    }

    public void setGrade(SADGrade grade) {
        this.grade = grade;
    }

    public SADSubject getSubject() {
        return subject;
    }

    public void setSubject(SADSubject subject) {
        this.subject = subject;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
