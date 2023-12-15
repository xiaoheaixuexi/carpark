package com.group4.mycarpark.util;

import java.util.Date;

//计费
public class CalculateCost {
//    public static double calculateDuration(Date begin, Date end){
//        long intervalTime = (end.getTime() - begin.getTime());
//
//        double duration = (double) intervalTime/1000;
//
//        return duration;
//    }

    public static double calculateCost(long duration){
        double price  = 1.0;
        return duration * price / 1000;
    }
}
