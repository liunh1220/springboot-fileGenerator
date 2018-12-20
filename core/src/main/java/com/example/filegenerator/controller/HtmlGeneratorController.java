package com.example.filegenerator.controller;

import com.example.filegenerator.api.clients.ServiceUrl;
import com.example.filegenerator.api.dtos.HtmlGenerateReq;
import com.example.filegenerator.api.dtos.ReqBodyBase;
import com.example.filegenerator.api.dtos.RespBodyBase;
import com.example.filegenerator.config.Globals;
import com.example.filegenerator.service.FileGenerateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;

/**
 * Created by liulanhua on 2018/6/7.
 */
@RestController
@Api(description = "cg停服")
public class HtmlGeneratorController {

    private static final Logger logger = LoggerFactory.getLogger(HtmlGeneratorController.class);

    @Autowired
    private FileGenerateService fileGenerateService;
    @Autowired
    private Globals globals;

    /**
     * 生成HTML静态页面
     *
     * @param req 生成HTML的参数
     * @return 访问地址
     */
    @ApiOperation("生成HTML静态页面")
    @RequestMapping(value = ServiceUrl.HTML_GENERATOR, method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public RespBodyBase<String> htmlGenerate(@RequestBody @Valid HtmlGenerateReq req) {
        logger.info("cg停服生成HTML页面操作，接收数据:{}", req);

        String saveFilePath = globals.getHtmlPath();
        //生成HTML
        String returnUrl = fileGenerateService.htmlFileGenerate(req, saveFilePath);
        returnUrl = globals.getHtmlLink() + File.separatorChar + "templates" + File.separatorChar + returnUrl;
        RespBodyBase<String> respBodyBase = new RespBodyBase<>();
        if (StringUtils.isNotBlank(returnUrl)) {
            respBodyBase.setCode("200");
            respBodyBase.setDescription("生成HTML成功");
            returnUrl = returnUrl + "?t=" + req.getTimeReq();
            logger.info("生成HTML成功");
        } else {
            respBodyBase.setCode("500");
            respBodyBase.setDescription("生成HTML失败");
            logger.error("生成HTML失败");
        }
        logger.info("HTML页面访问路径:{}", returnUrl);
        respBodyBase.setRespData(returnUrl);
        return respBodyBase;
    }


    @ApiOperation("生成停服状态文件")
    @RequestMapping(value = ServiceUrl.TXT_GENERATOR, method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public RespBodyBase<Boolean> txtGenerate(String fileContent) {
        logger.info("生成停服状态文件操作，接收数据：{}", fileContent);

        RespBodyBase<Boolean> respBodyBase = new RespBodyBase<>();
        respBodyBase.setRespData(Boolean.FALSE);
        String saveFilePath = globals.getHtmlPath() + "file";
        //生成txt
        if (null != fileContent && fileGenerateService.txtFileGenerate(fileContent, saveFilePath)) {
            logger.info("生成停服状态文件txt成功");
            respBodyBase.setRespData(Boolean.TRUE);
        }
        respBodyBase.setCode("1");
        respBodyBase.setDescription("生成停服状态文件成功");
        return respBodyBase;
    }

    @ApiOperation("获取停服状态")
    @RequestMapping(value = ServiceUrl.TXT_GETFSTATUS, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public RespBodyBase<Integer> getTfStatus(ReqBodyBase reqBodyBase) {
        logger.info("获取停服状态操作,请求服务名：{}", reqBodyBase.getServiceName());

        String saveFilePath = globals.getHtmlPath() + "file" + File.separatorChar + "tfAllClose.txt";
        boolean isAllClosing = fileGenerateService.isAllClosing(saveFilePath);
        RespBodyBase<Integer> respBodyBase = new RespBodyBase<>();
        respBodyBase.setRespData(-1);
        if (isAllClosing) {
            respBodyBase.setRespData(1);
        }
        respBodyBase.setCode("1");
        respBodyBase.setDescription("获取停服状态成功,停服状态是：" + (respBodyBase.getRespData() == 1 ? "停服中" : "开服中"));
        logger.info("获取停服状态成功,返回的停服状态是：{}", respBodyBase.getRespData());
        return respBodyBase;
    }

    /**
     * 获取全站停服url
     * @param urlType
     * @return
     */
    @RequestMapping(value = ServiceUrl.TXT_GETTFCONTENT, method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public RespBodyBase<String> getAllClosingContent(@RequestParam("urlType") String urlType) {
        String saveFilePath = globals.getHtmlPath() + "file" + File.separatorChar + "tfAllClose.txt";

        RespBodyBase<String> respBodyBase = new RespBodyBase<>();
        boolean allClosing = fileGenerateService.isAllClosing(saveFilePath);
        if (allClosing){
            respBodyBase.setDescription("停服中");
        }else {
            respBodyBase.setDescription("已开服");
        }
        String url = fileGenerateService.getAllClosingContent(saveFilePath, urlType);
        respBodyBase.setRespData(url);
        respBodyBase.setCode("1");
        return respBodyBase;
    }


}
