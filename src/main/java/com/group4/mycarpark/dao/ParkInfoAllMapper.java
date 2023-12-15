package com.group4.mycarpark.dao;

import com.group4.mycarpark.entity.ParkInfoAll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkInfoAllMapper {
    List<ParkInfoAll> selectAllParkAInfoAll();
    int insertParkInfoAll(ParkInfoAll parkInfoAll);
}
