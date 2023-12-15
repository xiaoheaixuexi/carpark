package com.group4.mycarpark.controller;

import com.group4.mycarpark.entity.Income;
import com.group4.mycarpark.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping("findAll")
    public List<Income> findAll(){
        return incomeService.findAll();
    }
}
