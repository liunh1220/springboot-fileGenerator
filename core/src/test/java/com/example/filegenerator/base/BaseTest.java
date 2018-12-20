package com.example.filegenerator.base;

import com.example.filegenerator.FileGeneratorApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liunanhua on 2018/3/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileGeneratorApplication.class)
@ActiveProfiles(profiles = "dev")
public class BaseTest {


}
