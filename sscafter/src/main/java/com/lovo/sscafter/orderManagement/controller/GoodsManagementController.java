package com.lovo.sscafter.orderManagement.controller;

import com.lovo.sscafter.orderManagement.entity.OrderForGoodsEntity;
import com.lovo.sscafter.orderManagement.service.IGoodsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GoodsManagementController {
    @Autowired
    IGoodsManagementService goodsManagementService;
    @RequestMapping("findGoods/{orderNum}/{currentPage}/{rows}")
    public List<OrderForGoodsEntity> findGoods(@PathVariable("orderNum")String orderNum,
                                               @PathVariable("currentPage")int currentPage, @PathVariable("rows")int rows){

        List<OrderForGoodsEntity> list = goodsManagementService.findGoods(orderNum,currentPage,rows);
        for (OrderForGoodsEntity o:list) {
            System.out.println(o.getGoodsName());
        }
        return  list;
    }
}
