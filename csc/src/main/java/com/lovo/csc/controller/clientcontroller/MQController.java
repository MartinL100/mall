package com.lovo.csc.controller.clientcontroller;


import com.lovo.csc.service.clientService.IUserAuditService;
import org.springframework.beans.factory.annotation.Autowired;

public class MQController {
    @Autowired
    private IUserAuditService userAuditService;
    //从MQ中取出用户信息并保存到数据库中
    public void savaUserAuditMessage(String message){

    }
}
