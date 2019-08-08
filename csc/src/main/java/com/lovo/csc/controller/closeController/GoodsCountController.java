package com.lovo.csc.controller.closeController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.entity.CenterOrderGoods;
import com.lovo.csc.entity.OrderEntity;
import com.lovo.csc.entity.dto.GoodsDto;
import com.lovo.csc.entity.dto.OrderDto;
import com.lovo.csc.service.clientService.IUserAuditService;
import com.lovo.csc.service.closeService.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GoodsCountController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserAuditService userAuditService;

    //验证用户余额是否充足，计算实际支付金额,参数跟在地址后面
    @RequestMapping(value = "countMoney/{userName}/{allPrice}/{payMethod}",method = RequestMethod.GET)
    public double countMoney(@PathVariable("userName")String userName,
                             @PathVariable("allPrice")double allPrice,
                             @PathVariable("payMethod")String payMethod){
        System.out.println(userName+"/"+allPrice+"/"+payMethod);
      double discount=   userAuditService.getUserDiscount(userName);//计算用户的折扣
//    double   payMoney=allPrice*discount;
//       if(payMethod.equals("deposit")){
//            //用预付款支付
//          payMoney= findDepositDiscount(userName,payMoney);
//        return payMoney;
        return 0;
    }
    //结算订单，数据以json传输,添加订单信息
    @RequestMapping("checkOrder")
    public String addOrder(String jsonStr) throws JsonProcessingException {
        //将json字符串转换为对象
         jsonStr="{\"orderNum\":\"092019070823578\",\"orderDate\":\"07/03/2019\",\"userName\":\"小王\"" +
                ",\"orderMoney\":25235.2,\"payMoney\":0.0,\"payMethod\":\"deposit\",\"goodsList\":[{" +
                "\"goodsId\":\"352\",\"goodsName\":\"梨子\",\"goodsNorms\":\"大\",\"goodsPrice\":120.0" +
                ",\"goodsNum\":200,\"goodsType\":\"水果\",\"goodsUnit\":\"一筐\"},{" +
                " \"goodsId\":\"242\",\"goodsName\":\"桃子\",\"goodsNorms\":\"大\",\"goodsPrice\":89.0" +
                ",\"goodsNum\":200,\"goodsType\":\"水果\",\"goodsUnit\":\"一筐\"}]}";
        OrderDto orderDto=null;
        try {
            orderDto=new ObjectMapper().readValue(jsonStr, OrderDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String userName=orderDto.getUserName();
        double discount=  userAuditService.getUserDiscount(userName);//计算用户的折扣

        double   payMoney=orderDto.getOrderMoney()*discount;

        if("deposit".equals(orderDto.getPayMethod())){
            //用预付款
            //         payMoney= deductionDeposit(userName,payMoney);
        }
        if(payMoney==-1){
            return  "{'payMoney':0,'errorInfo':'预付款余额不足，支付失败'}";
        }else{
            return  "{'payMoney':"+payMoney+",'errorInfo':'null'}";
        }

    }
    //根据id查询订单详细信息
    @RequestMapping("showOrder")
    public Map<String,Object> showOrder(int page,int rows,String orderNum) throws JsonProcessingException {
        if(null==orderNum){
            return null;
        }
        OrderEntity orderEntity= orderService.findbyId(orderNum);
        //得到商品dto数据
        List<CenterOrderGoods> centerOrderGoodsList= orderEntity.getOrderGoods();
        List<GoodsDto> dtoList=new ArrayList<>();
        for (CenterOrderGoods center:centerOrderGoodsList) {
            GoodsDto goodsDto=new GoodsDto();
             goodsDto.setGoodsId(center.getGoods().getGoodsId());
             goodsDto.setGoodsName(center.getGoods().getGoodsName());
             goodsDto.setGoodsNorms(center.getGoods().getGoodsNorms());
             int goodsNum= Integer.parseInt(center.getGoodsNum());
             goodsDto.setGoodsNum(center.getGoodsNum());
             double goodsPrice=Double.parseDouble(center.getGoodsPrice());
             goodsDto.setGoodsPrice(Double.parseDouble(center.getGoodsPrice()));
             goodsDto.setGoodsType(center.getGoods().getGoodsType());
             goodsDto.setGoodsUnit(center.getGoods().getGoodsUnit());
             goodsDto.setPayMoney(goodsNum*goodsPrice);
            dtoList.add(goodsDto);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("rows",dtoList);
        map.put("page",page);
        map.put("total",dtoList.size());
        return map;

    }

    //动态查询
    @RequestMapping("orderList")
    public Map<String,Object> orderList(int page,int rows,String userName,
                                        String orderNum,String startTime,String endTime){
        Map<String,Object> map=new HashMap<>();
        int pageNumber=(page-1)*rows;
        List<OrderEntity> orderList= orderService.findBySome(pageNumber,rows,userName,orderNum,startTime,endTime);

        for (OrderEntity o:orderList) {
            o.setOrderGoods(null);
         int tag=   o.getTag();
         if(tag==1){
             o.setTagStr("支付成功");
         }else{
             o.setTagStr("支付失败");
         }
        }
        map.put("rows",orderList);
        map.put("page",page);
        long total=orderService.count(userName,orderNum,startTime,endTime);
        map.put("total",total);
        return map;
    }
    //查询状态
    @RequestMapping("tagOrderList")
    public Map<String,Object> tagOrderList(int page,int rows,int tag){
        Map<String,Object> map=new HashMap<>();
        int pageNumber=(page-1)*rows;
        List<OrderEntity> tagOrderList= orderService.findByTag(pageNumber,rows,tag);
        for (OrderEntity o:tagOrderList
             ) {o.setOrderGoods(null);
        }
        map.put("rows",tagOrderList);
        map.put("page",page);
        long total=orderService.count(tag);
        map.put("total",total);
        return map;
    }

}
