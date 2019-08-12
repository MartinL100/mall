package com.lovo.csc.controller.closeController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.common.entity.GoodsDTO;
import com.lovo.common.entity.OrderDTO;
import com.lovo.csc.entity.CenterOrderGoods;
import com.lovo.csc.entity.GoodsEntity;
import com.lovo.csc.entity.OrderEntity;
import com.lovo.csc.service.clientService.IUserAuditService;
import com.lovo.csc.service.closeService.ICenterOrderService;
import com.lovo.csc.service.closeService.IGoodsService;
import com.lovo.csc.service.closeService.IOrderService;
import com.lovo.csc.service.depositService.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
    @Autowired
    private IDepositService depositService;
    @Autowired
    private ICenterOrderService centerOrderService;
    @Autowired
    private IGoodsService goodsService;

    //验证用户余额是否充足，计算实际支付金额,参数跟在地址后面
    @RequestMapping(value = "countMoney/{userName}/{allPrice}/{payMethod}",method = RequestMethod.GET)
    public double countMoney(@PathVariable("userName")String userName,
                             @PathVariable("allPrice")double allPrice,
                             @PathVariable("payMethod")String payMethod) {
//        System.out.println(userName + "/" + allPrice + "/" + payMethod);
        double discount = userAuditService.getUserDiscount(userName);//计算用户的折扣
        double payMoney = allPrice * discount;
        if (payMethod.equals("deposit")) {
            //用预付款支付
            payMoney = depositService.findDepositDiscount(userName, payMoney);
        }
        return payMoney;
    }
    //结算订单，数据以json传输,添加订单信息
    @RequestMapping(value = "checkOrder")
    public String addOrder(@RequestBody String orderInfo) throws JsonProcessingException {
        //将json字符串转换为对象
//         orderInfo="{\"orderNum\":\"092349070823569\",\"orderDate\":\"07/03/2019\",\"userName\":\"小1\"" +
//                ",\"orderMoney\":25235.2,\"payMoney\":\"0.0\",\"payMethod\":\"deposit\",\"goodsDTOList\":[{" +
//                "\"goodsId\":\"352\",\"goodsName\":\"梨子\",\"goodsNorms\":\"大\",\"goodsPrice\":120.0" +
//                ",\"goodsNum\":200,\"goodsType\":\"水果\",\"goodsUnit\":\"一筐\"},{" +
//                " \"goodsId\":\"242\",\"goodsName\":\"桃子\",\"goodsNorms\":\"大\",\"goodsPrice\":89.0" +
//                ",\"goodsNum\":200,\"goodsType\":\"水果\",\"goodsUnit\":\"一筐\"}]}";
        OrderDTO orderDto=null;
        try {
            orderDto=new ObjectMapper().readValue(orderInfo, OrderDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String userName=orderDto.getUserName();
        double discount=  userAuditService.getUserDiscount(userName);//计算用户的折扣

        double   payMoney=orderDto.getOrderMoney()*discount;

        if("deposit".equals(orderDto.getPayMethod())){
            //用预付款
                     payMoney= depositService.deductionDeposit(userName,payMoney);
        }
        if(payMoney==-1){
            this.full(orderDto,2);
            return  "{'payMoney':0,'errorInfo':'预付款余额不足，支付失败'}";
        }else{
            orderDto.setPayMoney(payMoney+"");
            //保存
            this.full(orderDto,1);
            return  "{'payMoney':"+payMoney+",'errorInfo':'null'}";
        }

    }
    //保存数据
    public void full(OrderDTO orderDto,int tag){
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setOrderNum(orderDto.getOrderNum());
        orderEntity.setOrderDate(orderDto.getOrderDate());
        orderEntity.setUserName(orderDto.getUserName());
        orderEntity.setOrderMoney(orderDto.getOrderMoney());
        orderEntity.setPayMoney(Double.parseDouble(orderDto.getPayMoney()));
        orderEntity.setPayMethod(orderDto.getPayMethod());
        orderEntity.setTag(tag);
        orderService.saveOrder(orderEntity);

        List<GoodsDTO> goodsDTOList= orderDto.getGoodsDTOList();
        for (GoodsDTO goods:goodsDTOList) {
            //保存商品
            GoodsEntity goodsEntity=new GoodsEntity();
            goodsEntity.setGoodsId( goods.getGoodsId());
            goodsEntity.setGoodsName(goods.getGoodsName());
            goodsEntity.setGoodsNorms(goods.getGoodsNorms());
            goodsEntity.setGoodsType(goods.getGoodsType());
            goodsEntity.setGoodsUnit(goods.getGoodsUnit());
            goodsService.saveGoodsCount(goodsEntity);
            //保存中间表
            CenterOrderGoods centerOrderGoods=new CenterOrderGoods();
            centerOrderGoods.setOrder(orderEntity);
            centerOrderGoods.setGoods(goodsEntity);
            centerOrderGoods.setGoodsNum(goods.getGoodsNum()+"");
            centerOrderGoods.setGoodsPrice(goods.getGoodsPrice()+"");
            centerOrderService.saveGoodsCount(centerOrderGoods);
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
        List<GoodsDTO> dtoList=new ArrayList<>();
        for (CenterOrderGoods center:centerOrderGoodsList) {
            GoodsDTO goodsDto=new GoodsDTO();
             goodsDto.setGoodsId(center.getGoods().getGoodsId());
             goodsDto.setGoodsName(center.getGoods().getGoodsName());
             goodsDto.setGoodsNorms(center.getGoods().getGoodsNorms());
             int goodsNum= Integer.parseInt(center.getGoodsNum());
             goodsDto.setGoodsNum(Long.parseLong(center.getGoodsNum()));
             double goodsPrice=Double.parseDouble(center.getGoodsPrice());
             goodsDto.setGoodsPrice(Float.parseFloat(center.getGoodsPrice()));
             goodsDto.setGoodsType(center.getGoods().getGoodsType());
             goodsDto.setGoodsUnit(center.getGoods().getGoodsUnit());
//             goodsDto.setPayMoney(goodsNum*goodsPrice);
            dtoList.add(goodsDto);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("rows",dtoList);
        map.put("pageTwo",page);
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
        map.put("pageTwo",page);
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
        for (OrderEntity o:tagOrderList) {
            o.setOrderGoods(null);

            if(tag==1){
                o.setTagStr("支付成功");
            }else{
                o.setTagStr("支付失败");
            }
        }
        map.put("rows",tagOrderList);
        map.put("pageTwo",page);
        long total=orderService.count(tag);
        map.put("total",total);
        return map;
    }

}
