package com.example.filegenerator.api.dtos;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by liulanhua on 2018/6/7.
 */
public class HtmlGenerateReq extends ReqBodyBase implements Serializable{

    @NotEmpty(message = "生成HTML内容")
    private String fileContent;

    /**
     * HTML类型:PC,APP,H5...
     */
    @NotEmpty(message = "生成HTML类型")
    private String fileType;

    @NotEmpty(message = "生成HTML ID")
    private String serviceId;

    @NotNull(message = "访问时间戳")
    private Long timeReq;


    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Long getTimeReq() {
        return timeReq;
    }

    public void setTimeReq(Long timeReq) {
        this.timeReq = timeReq;
    }

    @Override
    public String toString() {
        return "HtmlGenerateReq{" +
                "fileContent='" + fileContent + '\'' +
                ", fileType=" + fileType +
                ", serviceId='" + serviceId + '\'' +
                ", timeReq=" + timeReq +
                '}';
    }
}
