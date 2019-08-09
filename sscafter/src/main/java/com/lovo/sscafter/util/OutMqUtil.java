package com.lovo.sscafter.util;

import java.util.concurrent.ArrayBlockingQueue;

public class OutMqUtil {

    //创建消息队列
    public static ArrayBlockingQueue<String> outQueue=new ArrayBlockingQueue<>(100);
}
