package com.lhh.common.type;

public enum CorrectType {

    /**
     * 学生互批
     */
    Default("正常批改", 1),
    OneSelf("学生自批", 2),
    ClassMutual("班级互批", 3),
    GroupLeader("组长批", 4),
    GroupInside("组内互批", 5);

    private String name;
    private Integer code;


    CorrectType(String name, Integer code) {
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
