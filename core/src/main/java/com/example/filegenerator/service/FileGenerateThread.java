package com.example.filegenerator.service;

import com.example.filegenerator.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * Created by liulanhua on 2018/6/11.
 */
public class FileGenerateThread implements Callable<Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(FileGenerateThread.class);

    private String fileContent = null;
    private String saveFilePath = null;
    private String fileName = null;

    public FileGenerateThread(String fileContent, String saveFilePath, String fileName) {
        this.fileContent = fileContent;
        this.saveFilePath = saveFilePath;
        this.fileName = fileName;
    }

    @Override
    public Boolean call() throws Exception {
        Thread.currentThread().setName("fileGenerateThread" + System.currentTimeMillis());
        boolean fileGenerate = false;
        try {
            fileGenerate = FileUtils.fileGenerate(fileContent, saveFilePath, fileName);
        } catch (Exception e) {
            logger.error("FileGenerateThread.call()异常", e);
        }
        return fileGenerate;
    }


}
