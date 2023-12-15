package com.group4.mycarpark.dao;

import com.group4.mycarpark.entity.Income;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IncomeMapper {
    public int insertOneItem(Income income);

    public List<Income> findAll();

}
