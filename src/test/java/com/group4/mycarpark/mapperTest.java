package com.group4.mycarpark;

import com.group4.mycarpark.dao.CardMapper;
import com.group4.mycarpark.dao.ParkInfoAllMapper;

import com.group4.mycarpark.dao.ParkInfoMapper;
import com.group4.mycarpark.entity.ParkInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class mapperTest {
    @Autowired
    private ParkInfoAllMapper parkInfoAllMapper;

    @Autowired
    private ParkInfoMapper parkInfoMapper;

    @Autowired
    private CardMapper cardMapper;

    @Test
    void testparkInfoAllMapper(){
        System.out.println(parkInfoAllMapper.selectAllParkAInfoAll());
    }

    @Test
    void testParkInfoMapper(){
//        ParkInfo parkInfo = new ParkInfo();
//        parkInfo.setCarnum("88888");
//        parkInfo.setParknum(1);
//        parkInfo.setParkin(new Date());
//        parkInfo.setParktemp(0);
//        System.out.println(parkInfoMapper.insertParkInfo(parkInfo));

        System.out.println(parkInfoMapper.selectByCarNum("88888"));
        parkInfoMapper.deleteByCarNum("88888");

    }


    @Test
    void testParkInfoAllMapper(){
        cardMapper.updateMoney(9,-20);
    }
}
