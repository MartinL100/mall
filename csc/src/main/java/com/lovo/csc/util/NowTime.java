package com.lovo.csc.util;



import java.text.SimpleDateFormat;
import java.util.Date;

public class NowTime {

    public static String getNowTime(){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(getNowTime());
    }

}
