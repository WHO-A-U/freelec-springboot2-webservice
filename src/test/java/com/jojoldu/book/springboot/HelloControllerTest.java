package com.jojoldu.book.springboot;


import org.junit.Test;
import org.junit.runner.RunWith; //테스트를 할때 필요한 진행자 (SpringRunner) 를 불러옴
import org.springframework.beans.factory.annotation.Autowired; //Bean 주입기능
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; // 선언시 Controller만 사용가능함
import org.springframework.test.context.junit4.SpringRunner; //
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }

}