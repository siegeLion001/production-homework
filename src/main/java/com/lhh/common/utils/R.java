package com.lhh.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 返回数据
 *
 * @author ma.zd
 * @email mazengdi@lanhaihui.net
 * @date 2016年10月27日 下午9:59:27
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
    }

    public static R error() {
        return error(500, "链接异常 请稍后再试");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R ok(int code) {
        R r = new R();
        r.put("code", code);
        return r;
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
