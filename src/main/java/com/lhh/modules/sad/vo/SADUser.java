package com.lhh.modules.sad.vo;

import java.lang.reflect.Type;

public class SADUser implements Type {

    private String _id;

    private String username;

    private String name;

    private SADRole role;

    private SADInfo info;

    private String headPic;

    public SADInfo getInfo() {
        return info;
    }

    public void setInfo(SADInfo info) {
        if (info != null) {
            this.headPic = info.getHeadPic();
        }
        this.info = info;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SADRole getRole() {
        return role;
    }

    public void setRole(SADRole role) {
        this.role = role;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
}
