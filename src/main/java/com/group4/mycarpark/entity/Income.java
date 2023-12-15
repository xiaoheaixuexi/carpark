package com.group4.mycarpark.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Income {
    private int id;
    private double money;
    //收入方式（0现金，1支付宝，2微信，9从卡中扣费）
    private int  method;
    //收入类型（0临时停车，1普通卡，2月卡，3年卡）
    private int type;
    private String carnum;
    private String cardnum;
    //是否有违规
    private int isillegal;
    //收入来源，0充值卡，1出库收费
    private int source=1;
    //收入时间
    private Date time;
    //停车时长
    private long duration;
}
