package com.lhh.common.type;

public enum TopicSourceType {

    auxiliary("1", "教辅"),
    FineTopic("2", "精品试题"),
    school("2", "精品试题"),
    FinePaper("2", "精品试题");

    private String name;
    private String code;

    TopicSourceType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
