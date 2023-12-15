package com.group4.mycarpark.service;

import com.group4.mycarpark.dao.ParkInfoAllMapper;
import com.group4.mycarpark.entity.ParkInfoAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkInfoAllService {
    @Autowired
    private ParkInfoAllMapper parkInfoAllMapper;

    public int saveParkInfoAll(ParkInfoAll parkInfoAll){
        return parkInfoAllMapper.insertParkInfoAll(parkInfoAll);
    }

    public List<ParkInfoAll> findAll(){
        return parkInfoAllMapper.selectAllParkAInfoAll();
    }
}
