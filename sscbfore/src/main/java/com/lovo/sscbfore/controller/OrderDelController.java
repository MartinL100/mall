package com.lovo.sscbfore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.common.entity.GoodsDTO;
import com.lovo.common.entity.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderDelController {


    @RequestMapping("pageDel")
    public String pageDel(){
                String info="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":" +
                "[{\"ordernum\":J16820180805,\"ordertype\":\"退货中\",\"goodssize\":\"10\",\"orderdate\":\"2018-08-05\",\"paymethod\":\"支付宝\"}]}";
        return info;
    }
}
