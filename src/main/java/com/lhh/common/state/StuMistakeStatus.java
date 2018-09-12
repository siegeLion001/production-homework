package com.lhh.common.state;

/**
 * 错题本状态
 *
 * @ClassName: StuMistakeStatus
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.common.state
 * @CreateTime: 2018-06-26
 */
public enum  StuMistakeStatus {
    /*待复习*/
    UNGRASP("待复习",1),
    /*已掌握*/
    ISGRASP("已掌握",2);
    private String name;
    private Integer code;

    StuMistakeStatus(String name, Integer code) {
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