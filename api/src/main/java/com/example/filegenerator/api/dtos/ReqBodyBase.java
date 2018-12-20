package com.example.filegenerator.api.dtos;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 服务请求参数
 * Created by liunanhua on 2018/3/27.
 */
public class ReqBodyBase {

    @NotEmpty(message = "userDevice 客户端设备类型")
    private String userDevice;

    @NotEmpty(message = "serviceName 服务名称不能为空")
    private String serviceName;


    public String getUserDevice() {
        return userDevice;
    }

    public void setUserDevice(String userDevice) {
        this.userDevice = userDevice;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
