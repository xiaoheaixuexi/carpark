package com.group4.mycarpark.service;

import com.group4.mycarpark.dao.CardMapper;
import com.group4.mycarpark.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardMapper cardMapper;

    public Card findCardByCarNum(String carNum){
        return cardMapper.selectByCarNum(carNum);
    }

    public int changeMoney(int id, double money){
        return cardMapper.updateMoney(id, money);
    }
}
