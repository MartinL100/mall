package com.lovo.csc.util;


import com.lovo.csc.service.clientService.IUserAuditService;
import org.springframework.beans.factory.annotation.Autowired;

public class DBMethod {

   @Autowired
    private static IUserAuditService userAuditService;
    public static double getUserDiscount(String userName){
       return userAuditService.getUserDiscount(userName);
    }
}
