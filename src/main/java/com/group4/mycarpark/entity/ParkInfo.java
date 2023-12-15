package com.group4.mycarpark.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ParkInfo {
    private int id;
    private int parknum;
    private String cardnum = null;
    private String carnum;
    private Date parkin;
    // 是否临时停车 0正常 1临时
    private int parktemp;
}
