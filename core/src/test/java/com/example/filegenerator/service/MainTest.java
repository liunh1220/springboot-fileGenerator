package com.example.filegenerator.service;

import com.example.filegenerator.utils.FileUtils;

/**
 * Created by liulanhua on 2018/6/23.
 */
public class MainTest {

    public static void main(String[] args){

        for (int i=0;i<100;i++){
            Boolean aBoolean = FileGenerateSingleton.INSTANCE.fileGenerate("文本内容 ：" + i, "D:\\temp\\templates\\file\\", "test" + i + ".txt");
            FileUtils.fileGenerate("文本内容 ：" + i, "D:\\temp\\templates\\file\\"+"file"+i, "test"+ i +".txt");
        }

    }

}
