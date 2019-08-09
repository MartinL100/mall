package com.lovo.csc.util.promotionutil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public String getNow(){
        long now = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(now);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        return dateFormat.format(date);
    }

    public  boolean checkDate(String date){
        //  判断日期格式
        String regexTime = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        if (date != null && date.length() > 0 && !date.matches(regexTime)) {
            return false;
        }
        return  true;
    }





}
