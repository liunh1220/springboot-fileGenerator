package com.example.filegenerator.api.enums;

/**
 * Created by liulanhua on 2018/5/17.
 */
public enum PagesType {

    PC("pc","pc页面"),

    H5("h5","h5页面"),

    APP("app","app页面");

    private String code;
    private String desc;

    private PagesType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
