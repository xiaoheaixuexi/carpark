package com.group4.mycarpark.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Card {

    private int id;

    private String cardnum;

    private int type;

    private double money;

    private Date time;

    private int islose;

    private int illegalcount;

    private Date deductedtime;

}
