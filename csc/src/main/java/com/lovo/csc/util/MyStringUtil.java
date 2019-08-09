package com.lovo.csc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.vo.clientvo.PreserveResultVo;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MyStringUtil {

    /**
     * 验证字符串是否为空
     * @param strs
     * @return
     */
    public static boolean verifyString(String... strs){
        boolean bl=false;
        for (String str:strs) {
          //只要任何一次循环为空，就改为true
            if(null==str||"".equals(str)) {
                bl = true;
                return  bl;
            }
        }
        return  bl;
    }

    /**
     * 图片重命名
     * @return 重命名后的数据
     */
    public  static  String restFileName(){
        String newFileName=UUID.randomUUID().toString()+ System.currentTimeMillis()+"."+"png";
        return newFileName;
    }

    //得到格式化时间
    public  static String getFormMatTime(){
        return  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public  static String getFormMatTime(Date date){
        return  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    //将字符串转换成数组
    public static  String[] getUserNameArray(String userNameStr){

        return userNameStr.split(",");
    }
    //将对象装换成json
    public static String transitionObjectToString(Object obj) throws JsonProcessingException {
        return  new ObjectMapper().writeValueAsString(obj);
    }

    public static void main(String[] args)  {
//        System.out.println(getFormMatTime());
        String s="张三,李四";
       // System.out.println(getUserNameArray(s).length);
        PreserveResultVo vo=new PreserveResultVo();
        vo.setAuditReplyTime(getFormMatTime());
        vo.setAuditOpinion("wuwuwuuw");
        vo.setUserNameArray(getUserNameArray(s));
        vo.setUserState("审核通过");
        String str= null;
        try {
            str = transitionObjectToString((Object)vo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(str);

    }
}


