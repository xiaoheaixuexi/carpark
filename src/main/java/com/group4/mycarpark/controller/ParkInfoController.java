package com.group4.mycarpark.controller;


import com.group4.mycarpark.entity.ParkInfo;
import com.group4.mycarpark.service.ParkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parkInfo")
public class ParkInfoController {

    @Autowired
    private ParkInfoService parkInfoService;

    //查询在停的车辆
    @GetMapping("/findAll")
    public List<ParkInfo> findAllParkInfo(){
        return parkInfoService.findAll();
    }
}
