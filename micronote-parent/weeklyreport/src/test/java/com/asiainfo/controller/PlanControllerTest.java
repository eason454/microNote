package com.asiainfo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by eason on 2017/1/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlanControllerTest {
    //AutoConfigureMockMvc自动配置MockMVC，mock一些spring mvc的对象，不需要一个运行的容器
    @Autowired
    private MockMvc mockMvc;
    ////Required to Generate JSON content from Java objects
//    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Test
    public void queryNextWeekPlan() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.
            get("/queryNextWeekPlan/{user_id}","U47ACEF7895044DD9817EA672F811DFB").
            accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string("test"));
    }
}
