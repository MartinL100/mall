package com.lovo.sscafter.orderManagement.controller;

import com.lovo.sscafter.orderManagement.entity.OrderForGoodsEntity;
import com.lovo.sscafter.orderManagement.service.IGoodsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsManagementController {
    @Autowired
    IGoodsManagementService goodsManagementService;
    @RequestMapping("findGoods/{orderNum}/{currentPage}/{rows}")
    @ResponseBody
    public List<OrderForGoodsEntity> findGoods(@PathVariable("orderNum")String orderNum,
                                               @PathVariable("currentPage")int currentPage, @PathVariable("rows")int rows){
        List<OrderForGoodsEntity> list = goodsManagementService.findGoods(orderNum,currentPage,rows);

        return  list;
    }

    @RequestMapping("findGoodsAfter")
    @ResponseBody
    public Map<String,Object> findGoodsAfter(String orderNum, int page, int rows){
        List<OrderForGoodsEntity> list = goodsManagementService.findGoods(orderNum,page,rows);

        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("total",goodsManagementService.findGoodsRows(orderNum));
        for (int i=0;i<list.size();i++) {
            if("0".equals(list.get(i).getGoodsStatus())){list.get(i).setGoodsStatus("正常"); continue;}
            else if("1".equals(list.get(i).getGoodsStatus())){list.get(i).setGoodsStatus("退货中");continue;}
            else if("2".equals(list.get(i).getGoodsStatus())){list.get(i).setGoodsStatus("已退货");continue;}
        }
        map.put("rows",list);
        return map;
    }
    @RequestMapping("findGoodsRows/{orderNum}")
    @ResponseBody
    public int findGoods(@PathVariable("orderNum")String orderNum){

        int tempRows = goodsManagementService.findGoodsRows(orderNum);
        return  tempRows;
    }
}
