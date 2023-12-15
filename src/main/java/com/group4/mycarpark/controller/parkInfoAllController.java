package com.group4.mycarpark.controller;


import com.group4.mycarpark.entity.ParkInfoAll;
import com.group4.mycarpark.service.ParkInfoAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parkInfoAll")
public class parkInfoAllController {

    @Autowired
    private ParkInfoAllService parkInfoAllService;

    //查询所有的停车记录
    @GetMapping("findAll")
    public List<ParkInfoAll> findAll(){
        return parkInfoAllService.findAll();
    }
}
