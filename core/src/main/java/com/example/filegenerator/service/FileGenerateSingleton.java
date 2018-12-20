package com.example.filegenerator.service;

import com.example.filegenerator.utils.FileUtils;

/**
 * Created by liulanhua on 2018/6/22.
 */
public enum FileGenerateSingleton {

    INSTANCE;

    private FileGenerateSingleton(){
    }

    public Boolean fileGenerate(String fileContent, String saveFilePath, String fileName) {
        return FileUtils.fileGenerate(fileContent, saveFilePath, fileName);
    }



}
