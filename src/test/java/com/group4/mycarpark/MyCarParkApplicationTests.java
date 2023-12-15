package com.group4.mycarpark;

import com.group4.mycarpark.entity.Msg;
import com.group4.mycarpark.util.CalculateCost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootTest
class MyCarParkApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
        String url = "http://127.0.0.1:8082/msgService";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        Msg msg = new Msg();
        msg.setCode(0);
        msg.setMsg("hello");
        paramMap.add("msg",msg);

        Msg res = restTemplate.postForObject(url,paramMap,Msg.class);

        System.out.println(msg);
    }

}
