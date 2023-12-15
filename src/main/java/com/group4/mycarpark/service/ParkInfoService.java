package com.group4.mycarpark.service;

import com.group4.mycarpark.dao.ParkInfoMapper;
import com.group4.mycarpark.entity.ParkInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkInfoService {
    @Autowired
    private ParkInfoMapper parkInfoMapper;

    public void saveParkInfo(ParkInfo parkInfo){
        parkInfoMapper.insertParkInfo(parkInfo);
    }

    public ParkInfo findParkInfo(String carnum){
        return parkInfoMapper.selectByCarNum(carnum);
    }


    public int clearRecord(String carnum){
        return parkInfoMapper.deleteByCarNum(carnum);
    }


    public List<ParkInfo> findAll(){
        return parkInfoMapper.selectAllParkAInfo();
    }
}
