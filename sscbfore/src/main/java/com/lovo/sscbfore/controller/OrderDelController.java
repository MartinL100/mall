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


    @RequestMapping("orderDel")
    public String orderDel(){
    String info="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":" +
                "[{\"ordernum\":\"J16820190801\",\"ordertype\":\"已完结\",\"goodssize\":\"10\",\"orderdate\":" +
                "\"2018-08-01\",\"paymethod\":\"支付宝\",\"orderMoney\":\"21000元\"},{\"ordernum\":\"J16820190701\",\"ordertype\":" +
                "\"待付款\",\"goodssize\":\"12\",\"orderdate\":\"2018-07-01\",\"paymethod\":" +
                "\"预存款\",\"orderMoney\":\"10000元\"},{\"ordernum\":\"J16820190801\",\"ordertype\":\"已完结\",\"goodssize\":" +
                "\"10\",\"orderdate\":\"2018-08-01\",\"paymethod\":\"银联支付\",\"orderMoney\":\"11000元\"},{\"ordernum\":\"J16820190808\",\"ordertype\":" +
                "\"退货\",\"goodssize\":\"100\",\"orderdate\":\"2018-08-08\",\"paymethod\":\"支付宝\",\"orderMoney\":\"18000元\"}," +
            "{\"ordernum\":\"J16820190601\",\"ordertype\":\"已完结\",\"goodssize\":\"50\",\"orderdate\":\"2018-06-01\",\"paymethod\":\"预存款\",\"orderMoney\":\"2000元\"}]}";
        return info;
    }
    @RequestMapping("goodsDel")
    public String goodsDel(){
        String info="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":" +
                "[{\"goodsName\":\"Ak47-A\",\"goodsNum\":\"100支\",\"goodsPrice\":\"1000元/支\",\"goodsStatus\":" +
                "\"已退货\"},{\"goodsName\":\"Ak47\",\"goodsNum\":" +
                "\"100支\",\"goodsPrice\":\"1000元/支\",\"goodsStatus\":\"正常\"},{\"goodsName\":\"火麒麟\",\"goodsNum\":\"100支\",\"goodsPrice\":" +
                "\"1000元/支\",\"goodsStatus\":\"正常\"},{\"goodsName\":\"激光\",\"goodsNum\":" +
                "\"100支\",\"goodsPrice\":\"8888元/支\",\"goodsStatus\":\"退货中\"}," +
                "{\"goodsName\":\"毁灭\",\"goodsNum\":\"100支\",\"goodsPrice\":\"8880元/支\",\"goodsStatus\":\"正常\"}]}";
        return info;
    }
}
