package com.lovo.sscafter.orderManagement.util;

import java.util.concurrent.ArrayBlockingQueue;

public class MqUtil {

    public static ArrayBlockingQueue<String> orderQueue=new ArrayBlockingQueue<>(100);
}
