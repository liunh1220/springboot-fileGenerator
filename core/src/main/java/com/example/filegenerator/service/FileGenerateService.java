package com.example.filegenerator.service;

import com.alibaba.fastjson.JSONObject;
import com.example.filegenerator.api.dtos.HtmlGenerateReq;
import com.example.filegenerator.api.enums.PagesType;
import com.example.filegenerator.config.Globals;
import com.example.filegenerator.utils.BeanUtil;
import com.example.filegenerator.utils.DateUtil;
import com.example.filegenerator.utils.DesUtil;
import com.example.filegenerator.utils.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;

/**
 * Created by liulanhua on 2018/6/12.
 */
@Component
public class FileGenerateService {

    private static final Logger logger = LoggerFactory.getLogger(FileGenerateService.class);

    @Autowired
    private Globals globals;


    /**
     * 生成停服状态文件
     *
     * @param fileContent
     * @param saveFilePath
     * @return
     */
    public boolean txtFileGenerate(String fileContent, String saveFilePath) {
        return FileUtils.fileGenerate(fileContent, saveFilePath, "tfAllClose.txt");
    }

    /**
     * 是否全部停服中
     *
     * @param fileName
     * @return TRUE 停服中
     */
    public boolean isAllClosing(String fileName) {
        String readFileComtent = FileUtils.readFileComtent(fileName);
        logger.info("读出的文件内容为：{}", readFileComtent);
        if (StringUtils.isNotBlank(readFileComtent)) {
            JSONObject jsonObject = JSONObject.parseObject(readFileComtent);
            logger.info("startTime: {}; endTime: {}", jsonObject.get("startTime"), jsonObject.get("endTime"));

            Date startTime = DateUtil.parse(String.valueOf(jsonObject.get("startTime")));
            Date endTime = DateUtil.parse(String.valueOf(jsonObject.get("endTime")));
            if (startTime != null && endTime != null && startTime.before(new Date()) && endTime.after(new Date())
                    && !"2".equalsIgnoreCase(String.valueOf(jsonObject.get("soStatus")))) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }


    public String htmlFileGenerate(HtmlGenerateReq htmlGenerateReq, String saveFilePath) {
        try {
            HtmlGenerateReq req = decodeFileContent(htmlGenerateReq);
            if (null == req || StringUtils.isBlank(req.getFileContent())) {
                return null;
            }

            String fileSeparator = String.valueOf(File.separatorChar);
            //访问地址
            String returnUrl = null;
            //文件名
            String fileName = req.getServiceId() + ".html";
            if (PagesType.PC.getCode().equalsIgnoreCase(req.getFileType())) {
                saveFilePath = saveFilePath + PagesType.PC.getCode();
                returnUrl = PagesType.PC.getCode();
            } else {
                saveFilePath = saveFilePath + PagesType.APP.getCode();
                returnUrl = PagesType.APP.getCode();
            }
            String formatDate = DateUtil.formatDate(DateUtil.parse(req.getTimeReq()), "yyyyMMdd");
            saveFilePath = saveFilePath + fileSeparator + formatDate;
            returnUrl = returnUrl + fileSeparator + formatDate + fileSeparator + fileName;

            //生成HTML
            /*ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1,
                    0, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<Runnable>(2),
                    new ThreadPoolExecutor.DiscardOldestPolicy());
            Future<Boolean> result = threadPool.submit(new FileGenerateThread(req.getFileContent(), saveFilePath, fileName));
            threadPool.shutdown();
            if (null != result.get() && result.get()) {
                return returnUrl;
            }*/
            Boolean result = FileGenerateSingleton.INSTANCE.fileGenerate(req.getFileContent(), saveFilePath, fileName);
            if (null != result && result) {
                return returnUrl;
            }
        } catch (Exception e) {
            logger.error("FileGenerateService.fileGenerate()异常", e);
        }
        logger.error("生成html文件失败");
        return null;
    }


    private HtmlGenerateReq decodeFileContent(HtmlGenerateReq req) {
        try {
            String fileContent = DesUtil.decode3Des(globals.getPassword(), req.getFileContent());
            HtmlGenerateReq htmlGenerateReq = new HtmlGenerateReq();
            BeanUtil.copyProperties(req, htmlGenerateReq);
            htmlGenerateReq.setFileContent(fileContent);
            return htmlGenerateReq;
        } catch (Exception e) {
            logger.info("解密html异常");
            return null;
        }
    }

    /**
     * 获取全站停服文件内容
     *
     * @param fileName
     * @param urlType
     */
    public String getAllClosingContent(String fileName, String urlType) {
        String readFileComtent = FileUtils.readFileComtent(fileName);
        logger.info("读出的文件内容为：{}", readFileComtent);

        String url = null;
        if (StringUtils.isNotBlank(readFileComtent)) {
            JSONObject jsonObject = JSONObject.parseObject(readFileComtent);
            logger.info("startTime: {}; endTime: {};pcLink: {};h5Link:{}", jsonObject.get("startTime"),
                    jsonObject.get("endTime"), jsonObject.get("pcLink"), jsonObject.get("h5Link"));

            Object pcLink = jsonObject.get("pcLink");
            Object h5Link = jsonObject.get("h5Link");
            if (pcLink != null && pcLink != "" && PagesType.PC.getCode().equalsIgnoreCase(urlType)){
                url = String.valueOf(pcLink);
            }
            if (h5Link != null && h5Link != "" && (PagesType.APP.getCode().equalsIgnoreCase(urlType)
                    || PagesType.H5.getCode().equalsIgnoreCase(urlType))){
                url = String.valueOf(h5Link);
            }
        }
        return url;
    }


}
