package com.asiainfo.service.hibertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by eason on 2017/2/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HiberServiceTest {
    //AutoConfigureMockMvc自动配置MockMVC，mock一些spring mvc的对象，不需要一个运行的容器
    @Autowired
    private HiberService service;
    ////Required to Generate JSON content from Java objects
//    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Test
    public void test() throws Exception{
        service.test();
    }
}
