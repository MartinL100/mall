package com.lovo.sscafter.customerRetention.util;

import java.util.concurrent.ArrayBlockingQueue;

public class MqUtil {

    public static ArrayBlockingQueue<String> queue=new ArrayBlockingQueue<>(100);
}
