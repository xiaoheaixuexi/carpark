package com.group4.mycarpark.service;

import com.group4.mycarpark.dao.IncomeMapper;
import com.group4.mycarpark.entity.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeMapper incomeMapper;

    public int saveIncome(Income income){
        return incomeMapper.insertOneItem(income);
    }

    public List<Income> findAll(){
        return incomeMapper.findAll();
    }

}
