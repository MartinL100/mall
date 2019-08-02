package com.lovo.sscafter.orderManagement.controller;

import com.lovo.sscafter.orderManagement.entity.OrderManagementEntity;
import com.lovo.sscafter.orderManagement.service.IOrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public List<OrderManagementEntity> findOrderInfo(@PathVariable("orderDate")String orderDate,@PathVariable("orderType")int orderType,
    @PathVariable("currentPage")int currentPage, @PathVariable("rows")int rows,@PathVariable("userName")String userName){
        List<OrderManagementEntity> list = orderManagementService.findTrendsOrderInfo(orderDate,orderType,currentPage,rows,userName);
        return list;
    }
    @RequestMapping("findOrderRows/{orderDate}/{orderType}/{userName}")
    @ResponseBody
    public int findOrderRows(@PathVariable("orderDate")String orderDate,@PathVariable("orderType")int orderType,
                             @PathVariable("userName")String userName){
        int temp = orderManagementService.findOrderRows(orderDate,orderType,userName);
        return temp;
    }
}
