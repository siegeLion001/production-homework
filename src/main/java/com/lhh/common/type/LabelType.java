package com.lhh.common.type;

public enum LabelType {
    /*分享标签标识*/
    SHARE("分享标签",1),
    /*表扬标签标识*/
    PRAISE("表扬标签",2),
    /*批评标签标识*/
    CRITICISM("批评标签",3);

    LabelType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    private String name;
    private Integer code;


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
