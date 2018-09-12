package com.lhh.common.type;

import java.util.Calendar;

/**
 * 日期类型
 *
 * @ClassName: DateType
 * @Author: cuihp
 * @BelongsProject: production-homework
 * @BelongsPackage: com.lhh.common.type
 * @CreateTime: 2018-06-27
 */
public enum DateType {
    DAY("今天", "1"),
    WEEK("本周", "2"),
    MONTH("本月", "3"),
    ALL("全部", "4"),
    ONE_WEEK("近一周", "5", Calendar.DAY_OF_MONTH, -7),
    ONE_MONTH("近一个月", "6", Calendar.MONTH, -1),
    THREE_MONTH("近三个月", "7", Calendar.MONTH, -3),
    HALF_YEAR("近半年", "8", Calendar.MONTH, -6);


    private String name;
    private String code;
    private Integer type;
    private Integer num;

    DateType(String name, String code, Integer type, Integer num) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.num = num;
    }

    DateType(String name, String code) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}