package com.lhh.vo;

import java.io.Serializable;

public class AnswerSheet implements Serializable {

    private String name;

    private Part[] parts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Part[] getParts() {
        return parts;
    }

    public void setParts(Part... parts) {
        this.parts = parts;
    }
}
