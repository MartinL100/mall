package com.lovo.sscbfore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.common.entity.GoodsDTO;
import com.lovo.common.entity.OrderDTO;
import com.lovo.common.entity.OrderForGoodsDTO;
import com.lovo.common.entity.OrderManagementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OrderDelController {
    @Autowired
    ObjectMapper objectMapper;
    @RequestMapping("orderDel")
    public String orderDel(int page,int limit){

        List<OrderManagementDTO>orderManagementDTOlist=new ArrayList<>();
        OrderManagementDTO orderDto=new OrderManagementDTO();
        orderDto.setOrderNum("J16820190801");
        orderDto.setOrderType("已完结");
        orderDto.setGoodsSize(110);
        orderDto.setOrderDate("2018-08-01");
        orderDto.setPayMethod("支付宝");
        orderDto.setOrderMoney(21000);

        OrderManagementDTO orderDto1=new OrderManagementDTO();
        orderDto1.setOrderNum("J16820190801");
        orderDto1.setOrderType("待付款");
        orderDto1.setGoodsSize(200);
        orderDto1.setOrderDate("2018-08-06");
        orderDto1.setPayMethod("预存款");
        orderDto1.setOrderMoney(20000);

        OrderManagementDTO orderDto2=new OrderManagementDTO();
        orderDto2.setOrderNum("J16820190801");
        orderDto2.setOrderType("退货");
        orderDto2.setGoodsSize(100);
        orderDto2.setOrderDate("2018-06-01");
        orderDto2.setPayMethod("银联支付");
        orderDto2.setOrderMoney(40000);

        OrderManagementDTO orderDto3=new OrderManagementDTO();
        orderDto3.setOrderNum("J16820190401");
        orderDto3.setOrderType("已完结");
        orderDto3.setGoodsSize(100);
        orderDto3.setOrderDate("2018-04-01");
        orderDto3.setPayMethod("支付宝");
        orderDto3.setOrderMoney(22000);

        OrderManagementDTO orderDto4=new OrderManagementDTO();
        orderDto4.setOrderNum("J16820190711");
        orderDto4.setOrderType("退货");
        orderDto4.setGoodsSize(99);
        orderDto4.setOrderDate("2018-07-11");
        orderDto4.setPayMethod("银联支付");
        orderDto4.setOrderMoney(38000);

        orderManagementDTOlist.add(orderDto);
        orderManagementDTOlist.add(orderDto1);
        orderManagementDTOlist.add(orderDto2);
        orderManagementDTOlist.add(orderDto3);
        orderManagementDTOlist.add(orderDto4);
        String goodInfo="";
        try {
            goodInfo = objectMapper.writeValueAsString(orderManagementDTOlist);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        goodInfo= "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+goodInfo+"}";
//    String info="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":" +
//                "[{\"ordernum\":\"J16820190801\",\"ordertype\":\"已完结\",\"goodssize\":\"10\",\"orderdate\":" +
//                "\"2018-08-01\",\"paymethod\":\"支付宝\",\"orderMoney\":\"21000元\"},{\"ordernum\":\"J16820190701\",\"ordertype\":" +
//                "\"待付款\",\"goodssize\":\"12\",\"orderdate\":\"2018-07-01\",\"paymethod\":" +
//                "\"预存款\",\"orderMoney\":\"10000元\"},{\"ordernum\":\"J16820190801\",\"ordertype\":\"已完结\",\"goodssize\":" +
//                "\"10\",\"orderdate\":\"2018-08-01\",\"paymethod\":\"银联支付\",\"orderMoney\":\"11000元\"},{\"ordernum\":\"J16820190808\",\"ordertype\":" +
//                "\"退货\",\"goodssize\":\"100\",\"orderdate\":\"2018-08-08\",\"paymethod\":\"支付宝\",\"orderMoney\":\"18000元\"}," +
//            "{\"ordernum\":\"J16820190601\",\"ordertype\":\"已完结\",\"goodssize\":\"50\",\"orderdate\":\"2018-06-01\",\"paymethod\":\"预存款\",\"orderMoney\":\"2000元\"}]}";
        return goodInfo;
    }

    @RequestMapping("goodsDel")
    public String goodsDel(String goodsNum){
        List<OrderForGoodsDTO>orderForGoodsDTOlist=new ArrayList<>();
        OrderForGoodsDTO orderGoodsDto=new OrderForGoodsDTO();
        orderGoodsDto.setGoodsName("AK47-A");
        orderGoodsDto.setGoodsNum(100);
        orderGoodsDto.setGoodsPrice(1000);
        orderGoodsDto.setGoodsStatus("正常");

        orderForGoodsDTOlist.add(orderGoodsDto);

        String goodsInfo="";
        try {
            goodsInfo= objectMapper.writeValueAsString(orderForGoodsDTOlist);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        goodsInfo="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+goodsInfo+"}";


//        String info="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":" +
//                "[{\"goodsName\":\"Ak47-A\",\"goodsNum\":\"100支\",\"goodsPrice\":\"1000元/支\",\"goodsStatus\":" +
//                "\"已退货\"},{\"goodsName\":\"Ak47\",\"goodsNum\":" +
//                "\"100支\",\"goodsPrice\":\"1000元/支\",\"goodsStatus\":\"正常\"},{\"goodsName\":\"火麒麟\",\"goodsNum\":\"100支\",\"goodsPrice\":" +
//                "\"1000元/支\",\"goodsStatus\":\"正常\"},{\"goodsName\":\"激光\",\"goodsNum\":" +
//                "\"100支\",\"goodsPrice\":\"8888元/支\",\"goodsStatus\":\"退货中\"}," +
//                "{\"goodsName\":\"毁灭\",\"goodsNum\":\"100支\",\"goodsPrice\":\"8880元/支\",\"goodsStatus\":\"正常\"}]}";
        return goodsInfo;
    }
}
