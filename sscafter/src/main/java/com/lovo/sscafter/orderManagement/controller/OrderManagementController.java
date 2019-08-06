package com.lovo.sscafter.orderManagement.controller;

import com.lovo.common.entity.OrderDTO;
import com.lovo.sscafter.orderManagement.entity.OrderManagementEntity;
import com.lovo.sscafter.orderManagement.service.IOrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderManagementController {
    @Autowired
    private IOrderManagementService orderManagementService;
    @RequestMapping("delOrderInfo/{orderNum}")
    @ResponseBody
    public String delOrderInfo(@PathVariable("orderNum")String orderNum){
        boolean temp = orderManagementService.updateOrderDelType(orderNum);
        return "{'info':'"+temp+"'}";
    }

    @RequestMapping("findOrderInfo/{orderDate}/{orderType}/{currentPage}/{rows}/{userName}")
    @ResponseBody
    public List<OrderManagementEntity> findOrderInfo(@PathVariable("orderDate")String orderDate,@PathVariable("orderType")int orderType,
                                                     @PathVariable("currentPage")int currentPage, @PathVariable("rows")int rows,@PathVariable("userName")String userName){
        List<OrderManagementEntity> list = orderManagementService.findTrendsOrderInfo(orderDate,orderType,currentPage,rows,userName);
        return list;
    }
    @RequestMapping("findOrderRows/{orderDate}/{orderType}/{userName}")
    @ResponseBody
    public int findOrderRows(String orderDate,@PathVariable("orderType")int orderType,
                             String userName){
        int temp = orderManagementService.findOrderRows(orderDate,orderType,userName);
        return temp;
    }
    @RequestMapping("findOrderInfoAfter")
    @ResponseBody
    public Map<String,Object> findOrderInfoAfter(String orderDate, Integer orderType,int page,int rows, String userName){
        if(null == orderDate || "".equals(orderDate)){
            orderDate="no";
        }
        if(null == userName || "".equals(userName)){
            userName="no";
        }
        if(null == orderType || "".equals(userName)){
            orderType=3;
        }
        List<OrderManagementEntity> list = orderManagementService.findTrendsOrderInfo(orderDate,orderType,page,rows,userName);
        for (int i=0;i<list.size();i++){
            //0就是正常,1就是退货中,2就是已退货
            if("0".equals(list.get(i).getOrderType())){
                list.get(i).setOrderType("正常");
            }else if("1".equals(list.get(i).getOrderType())){
                list.get(i).setOrderType("未付款");
            }else if("2".equals(list.get(i).getOrderType())){
                list.get(i).setOrderType("有退货");
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("rows",list);
        map.put("page",page);

        map.put("total",orderManagementService.findOrderRows(orderDate,orderType,userName));
        return map;
    }
    @RequestMapping("receiveOrder")
    public void receiveOrder(@RequestBody OrderDTO orderDTO){

    }
}
