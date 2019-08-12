//package com.lovo.sscbfore.controller;
//
//        import com.fasterxml.jackson.core.JsonProcessingException;
//        import com.fasterxml.jackson.databind.ObjectMapper;
//        import com.lovo.common.entity.OrderForGoodsDTO;
//        import com.lovo.common.entity.OrderManagementDTO;
//        import com.lovo.sscbfore.order.entity.OrderInfoDto;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.web.bind.annotation.RequestMapping;
//        import org.springframework.web.bind.annotation.RestController;
//        import org.springframework.web.client.RestTemplate;
//
//        import java.io.IOException;
//        import java.util.ArrayList;
//        import java.util.HashMap;
//        import java.util.List;
//        import java.util.Map;
//
//@RestController
//public class OrderController {
//
//    @Autowired
//    ObjectMapper objectMapper;
//    //远程调用的模板
//    @Autowired
//    private RestTemplate restTemplate;
//    //根据订单号删除
//    @RequestMapping("goodsDel")
//    public  String  orderDel(String orderNum){
//
//        String s= restTemplate.getForEntity("http://sscAfter/delOrderInfo/"+orderNum+"/",String.class).getBody();
//        try {
//          OrderInfoDto info=  objectMapper.readValue(s, OrderInfoDto.class);
//          String str=info.getInfo();
//            return str;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//   }
//String userName="zhaoyun";
////根据下单日期,订单类型,用户名模糊查询
//    @RequestMapping("orderDel")
//    public String orderFindByDateAndTypeAndName(String orderDate, String orderType,int page ,int limit, String userName)throws JsonProcessingException {
//        //远程调用接口
//        List<OrderManagementDTO> order= restTemplate.getForEntity("http://sscAfter/findOrderInfo/"+orderDate+"/"+orderType+"/"+page+"/"+limit+"/"+userName+"/",List.class).getBody();
//        String goodInfo="";
//        try {
//            goodInfo = objectMapper.writeValueAsString(order);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        goodInfo= "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+goodInfo+"}";
//        return goodInfo;
//
//    }
//    //根据下单日期,订单类型,用户名 查询总行数
//    @RequestMapping("orderDel")
//    public int orderDateAndTypeAndNameNum(String orderDate, String orderType, String userName){
//
//        return restTemplate.getForEntity("http://sscAfter/findOrderRows/"+orderDate+"/"+orderType+"/"+userName+"/",int.class).getBody();
//    }
////根据订单号查询商品的集合 进行分页
//    @RequestMapping("goodsDel")
//    public String orderFindByOrderNum(String orderNum,int page ,int limit){
//
//      List<OrderForGoodsDTO> goosDto=  restTemplate.getForEntity("http://sscAfter/findGoodsRows/"+orderNum+"/"+page+"/"+limit+"/",List.class).getBody();
//        String goodInfo="";
//        try {
//            goodInfo = objectMapper.writeValueAsString(goosDto);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        goodInfo= "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+goodInfo+"}";
//        return goodInfo;
//    }
////根据订单号 查询商品的总行数
//    @RequestMapping("goodsDel")
//    public int goodsNum(String orderNum){
//
//        return restTemplate.getForEntity("http://sscAfter/findGoodsRows/"+orderNum+"/",int.class).getBody();
//    }
//}
