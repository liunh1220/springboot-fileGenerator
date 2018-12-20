package com.example.filegenerator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liulanhua on 2018/6/7.
 */
@Configuration
public class Globals {

    /**
     * html保存路径
     */
    @Value("${cg.file.path}")
    private String htmlPath;
    /**
     * html访问域名和端口
     */
    @Value("${cg.file.link}")
    private String htmlLink;

    @Value("${cg.file.password}")
    private String password;


    public String getHtmlPath() {
        return htmlPath;
    }

    public void setHtmlPath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public String getHtmlLink() {
        return htmlLink;
    }

    public void setHtmlLink(String htmlLink) {
        this.htmlLink = htmlLink;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
