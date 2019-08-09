package com.lovo.csc.service.closeService.Mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqController {
    @Autowired
    MyMQService myMQService;
    @RequestMapping("getMq")
    public String getMq(String msg){
//        myMQService.sendMessage(msg);
        myMQService.sendTopic(msg);
        return "发送成功";
    }
    @RequestMapping("sendMq.lovo")
    public String sendMq(){

        myMQService.sendMessage2("r");
        return "发送成功";
    }
}
