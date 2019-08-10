package com.lovo.sscbfore.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscbfore.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@RestController
public class DepositMoneyController {
    //远程调用的模板
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;
    /**
     * 获取预存款信息
     * @return 预存款信息
     */
    @RequestMapping("getDepositInfo")
    public  String getDepositInfo(){
//        String info ="{\"userName\":\"zhaoyun\",\"countDepositMoney\":\"80000\",\"leftDepositMoney\":\"5000\",\"depositMoneyLevel\":\"钻石五\"}";
        String userName="zhaoyun";
        String info="";
        info=restTemplate
                .getForEntity(UrlUtil.FIND_DEPOSIT_INFO+userName,String.class)
                .getBody();
        return info ;
    }

    /**
     * 存入预存款
     * @param payInfo
     * @return 预存款信息
     */
    @RequestMapping("addDepositMoney")
    public String addDepositMoney(String payInfo) throws IOException {
//        String info ="{\"userName\":\"zhaoyun\",\"countDepositMoney\":\"80000\",\"leftDepositMoney\":\"5000\",\"depositMoneyLevel\":\"钻石五\"}";;
        String info = "";
        String userName = "zhaoyun";
        Map map = objectMapper.readValue(payInfo,new TypeReference<Map<String,String>>(){});
        info= restTemplate
                .getForEntity(UrlUtil.SAVE_DEPOSIT_MONEY_URL+userName+"/"+map.get("addDepositMoney"),String.class)
                .getBody();
        return  info;
    }
}
