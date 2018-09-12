package com.lhh.common.state;

public enum Evaluationer {

    artificial("包含人工打分", 0),
    system("系统打分", 1);

    private String name;
    private int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private Evaluationer(String name, int code) {
        this.name = name;
        this.code = code;
    }
}
