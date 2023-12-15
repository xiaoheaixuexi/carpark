package com.group4.mycarpark.dao;

import com.group4.mycarpark.entity.ParkInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkInfoMapper {
    List<ParkInfo> selectAllParkAInfo();
    ParkInfo selectByCarNum(String carnum);
    int insertParkInfo(ParkInfo parkInfo);
    int deleteByCarNum(String carnum);
}
