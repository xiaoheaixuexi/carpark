package com.group4.mycarpark.dao;

import com.group4.mycarpark.entity.Card;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMapper {
    Card selectByCarNum(String carnum);

    int updateMoney(int id, double change);
}
