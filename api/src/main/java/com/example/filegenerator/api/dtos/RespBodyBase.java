package com.example.filegenerator.api.dtos;


import java.io.Serializable;

/**
 * Created by liunanhua on 2018/3/27.
 */
public class RespBodyBase<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String SUCCESS = "0";//成功

    public static final String FAIL = "1";//失败

    public static final String NOTEXIST = "2";//不存在


    /**
     * 处理结果
     * SUCCESS：成功
     * FAIL：失败
     */
    private String code;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 业务数据报文，JSON 格式，具体见各接口定义
     */
    private T respData;

    public T getRespData() {
        return respData;
    }

    public void setRespData(T respData) {
        this.respData = respData;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
