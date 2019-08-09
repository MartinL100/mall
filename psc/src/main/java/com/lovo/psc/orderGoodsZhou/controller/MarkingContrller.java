package com.lovo.psc.orderGoodsZhou.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.psc.entity.IndentEntity;
import com.lovo.psc.entity.SupplierEntity;
import com.lovo.psc.entity.SupplyCenterEntity;
import com.lovo.psc.orderGoodsZhou.service.IIdentService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MarkingContrller {
    @Autowired
    private IIdentService service;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @RequestMapping("marking.lovo")
    @ResponseBody
    public Map<String,Object> getListIndent(int page,int rows,@RequestParam("indentStatus") String indentStatus,HttpServletRequest request){
        SupplierEntity supplier= (SupplierEntity) request.getSession().getAttribute("supplier");
        Map<String,Object> map=new HashMap<>();
        List<IndentEntity> list=service.getNoPayList(supplier.getSupplierId(),indentStatus,page,rows);
        map.put("rows",list);
        map.put("page",page);
        int total=list.size();
        map.put("total",total);
        return map;
    }
    @RequestMapping("goodsDetails.lovo/{indentId}")
    public ModelAndView getListGoods(@PathVariable("indentId") String indentId, HttpServletRequest request){
        request.getSession().setAttribute("indentId",indentId);
        ModelAndView mv=new ModelAndView("/static/pageTwo/goodsDetails.html");
        return mv;
    }
    @RequestMapping("goodsDetails.lovo")
    @ResponseBody
    public Map<String,Object> getListGoods2(int page,int rows,HttpServletRequest request){
        String indentId= (String) request.getSession().getAttribute("indentId");
        Map<String,Object> map=new HashMap<>();
        List<SupplyCenterEntity> list=service.getGoodsList(indentId,page,rows);
        map.put("rows",list);
        map.put("page",page);
        int total=list.size();
        map.put("total",total);
        return map;
    }
    @RequestMapping("goodsPrice.lovo")
    @ResponseBody
    public float  getListGoods2(HttpServletRequest request){
        String indentId= (String) request.getSession().getAttribute("indentId");
        List<SupplyCenterEntity> list=service.getGoodsList2(indentId);
        float f=0;
        for (SupplyCenterEntity s:list) {
           f+=s.getSupplyNum()*s.getSupplyPrice();
        }
        return f;
    }
    @RequestMapping("pastSupplyMain.lovo")
    @ResponseBody
    public Map<String,Object> getPastListIndent(int page,int rows,HttpServletRequest request,@RequestParam("indentStatus") String indentStatus,@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
        SupplierEntity supplier= (SupplierEntity) request.getSession().getAttribute("supplier");
        Map<String,Object> map=new HashMap<>();
        List<IndentEntity> list=service.getYesPayList(supplier.getSupplierId(),indentStatus,startDate,endDate,page,rows);
        map.put("rows",list);
        map.put("page",page);
        int total=list.size();
        map.put("total",total);
        return map;
    }
    @RequestMapping("sendMQ.lovo")
    @ResponseBody
    public String getPastListIndent(@RequestParam("JsonIndent") String JsonIndent){
        //创建队列
        ActiveMQQueue queue=new ActiveMQQueue("scopeMQ");
        //发送json字符串到这个MQ
        jmsMessagingTemplate.convertAndSend(queue,JsonIndent);
        //把供货的库存减去
        List<String> stringList=null;
        List<SupplyCenterEntity> goodsList2=null;
        ObjectMapper mapper=new ObjectMapper();
        //把大订单编号的json转成数组对象
        try {
            stringList=mapper.readValue(JsonIndent,new TypeReference<List<String>>() { });
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s:stringList) {
            goodsList2=service.getGoodsList2(s);
            for (SupplyCenterEntity su:goodsList2) {
                //修改库存
                service.updateByCodeId(su.getSupplierGoodsEntity().getCodeId(),new Long(su.getSupplyNum()).intValue());
            }

        }
        return "ok";
    }
}
