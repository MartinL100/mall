package com.lovo.csc.controller.depositController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.entity.depositEntity.DepositEntity;
import com.lovo.csc.service.depositService.IDepositInfoService;
import com.lovo.csc.service.depositService.IDepositService;
import com.lovo.csc.vo.DepositVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预存款
 */
@RestController
public class DepositController {
    @Autowired
    private IDepositService depositService;

    /**
     * 添加预存款
     *
     * @param userName  用户名
     * @param saveMoney 添加金额
     * @return 预存款对象
     */
    //  添加预存款  http://csc/getDepositObj/{userName}/{saveMoney}
    @RequestMapping("getDepositObj/{userName}/{saveMoney}")
    public String getDepositObj(@PathVariable(name = "userName") String userName, @PathVariable(name = "saveMoney") Double saveMoney) throws JsonProcessingException {
        DepositEntity deposit = depositService.saveDepositMoney(userName, saveMoney);
        String lv= deposit.getDepositMoneyLevel();
        if ("0".equals(lv)){
            deposit.setDepositMoneyLevel("大众会员");
        } else if ("1".equals(lv)){
            deposit.setDepositMoneyLevel("黄金会员");
        }else {
            deposit.setDepositMoneyLevel("砖石会员");
        }
        ObjectMapper objectMapper=new ObjectMapper();
         String depositJosn=  objectMapper.writeValueAsString(deposit);
        return depositJosn;
    }

    //查询该用户的预存款信息
    // http://csc/findDeposit/{userName}
@RequestMapping("findDeposit/{userName}")
    public String findDeposit(@PathVariable(name = "userName")String userName) throws JsonProcessingException {
        DepositEntity depositEntity=  depositService.findDepositByUserName(userName);
        ObjectMapper objectMapper=new ObjectMapper();
        String  str=  objectMapper.writeValueAsString(depositEntity);
        return  str;
    }



}
