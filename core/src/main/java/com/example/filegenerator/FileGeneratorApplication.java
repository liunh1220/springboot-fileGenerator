package com.example.filegenerator;

import com.example.filegenerator.config.FileGeneratorConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by liulanhua on 2018/6/7.
 */
@SpringBootApplication
@Import({FileGeneratorConfiguration.class})
@EnableAsync
public class FileGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileGeneratorApplication.class, args);
    }


}