package com.group4.mycarpark.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ParkInfoAll {
    private int id;
    private String cardnum;
    private int parknum;
    private String carnum;
    private Date parkin;
    private Date parkout;
    private int parktemp;
    private int uid;
}
