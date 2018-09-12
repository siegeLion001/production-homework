package com.lhh.common.type;

public enum ResourceType {

    /**
     * 学生互批
     */
    Paper("21世纪卷库", 1),
    Question("21世纪题库", 2);


    private String name;
    private Integer code;


    ResourceType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
