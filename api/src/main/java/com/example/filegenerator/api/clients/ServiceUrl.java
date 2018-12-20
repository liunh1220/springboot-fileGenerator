package com.example.filegenerator.api.clients;

/**
 * Created by liunanhua on 2018/3/27.
 */
public interface ServiceUrl {

    String SERVICE_NAME = "CG-HTMLGENERATOR-SERVICE";

    String SERVICE_HOSTNAME = "http://" + SERVICE_NAME;

    String URL_PRE = "/cg/webSite";

    /**
     * 生成HTML静态页面
     */
    String HTML_GENERATOR = URL_PRE+"/html/add";

    /**
     * 生成全站停服状态文件
     */
    String TXT_GENERATOR = URL_PRE+"/html/addTxt";

    /**
     * 获取停服状态
     */
    String TXT_GETFSTATUS = URL_PRE+"/html/status";

    /**
     * 获取全站停服url
     */
    String TXT_GETTFCONTENT = URL_PRE+"/html/allClosingUrl";


}
