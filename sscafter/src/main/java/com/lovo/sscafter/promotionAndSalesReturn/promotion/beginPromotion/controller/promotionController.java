package com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.goodsStock.entity.GoodsTypeEntity;
import com.lovo.sscafter.goodsStock.service.IGoodsTypeService;
import com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.service.IPromotionGoodsService;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 促销controller
 */
@Controller
@RequestMapping("promotion")
public class promotionController {

    @Autowired
    private IPromotionGoodsService service;

    @Autowired
    private IGoodsTypeService goodsTypeService;

    //调用atvicmq的模板
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    //初始化页面
    @RequestMapping("/findAll")
    @ResponseBody//只返回数据
    public Map<String,Object> findAll(String goodsName, String goodsType, int page, int rows){
        if ("不限".equals(goodsType)){
            goodsType=null;
        }
        //动态查询总行数
      int pageCount= (int)service.findCount(goodsName,goodsType);

      //计算起始位置
      int nowPage=(page-1)*rows;

       List<GoodsEntity> list= service.findBygoodsNameAndgoodsState(goodsName,goodsType,nowPage,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("rows",list);
        map.put("pageTwo",nowPage);
        map.put("total",pageCount);
        return map;
    }

    //初始化下拉
    @RequestMapping("initialize")
    @ResponseBody
    public List<GoodsTypeEntity> initialize(){
        //查询全部下拉
        List<GoodsTypeEntity> goodsTypeList= goodsTypeService.findAll();
        return goodsTypeList;
    }


    //根据页面传入的id集合，返回到设置促销页面
    @RequestMapping("/showGoods")
    @ResponseBody
    public String findRestList(String list, HttpServletRequest request,HttpServletResponse response) {
        List<String> listString=null;
        ObjectMapper mapper=new ObjectMapper();
        try {
            //将json字符串转换为list集合
          listString=  (List<String>)mapper.readValue(list,Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String info="";
        if ( !"".equals(listString) && null!=listString){
        //查询数据为空则返回no
            info="yes";
        }else {
            //不为空则返回yes
            info="no";
        }
        //将id集合放入到session中,传入到跳转的controller
        request.setAttribute("listString",listString);
        request.getSession().setAttribute("listString",listString);
        return info;
    }



    //接收session中的数据
    @RequestMapping("/promotionAll")
    @ResponseBody
    public Map<String,Object> promotionAll(int page, int rows,HttpServletRequest request,HttpServletResponse response){
    Map<String,Object> map=new HashMap<>();

        //得到请求转发的数据(id的集合)
    List<String> goodsIdList=(List<String>)request.getSession().getAttribute("listString");

        //根据id集合在从数据库查询
    List<GoodsEntity> goodsList=service.findByGoodsId(goodsIdList);

    int nowPage=(page-1)*rows;
    map.put("rows",goodsList);
    map.put("pageTwo",nowPage);
    map.put("total",goodsList.size());
    return map;
    }



    //接收到准备促销传入的数据,listId:id集合字符串，discount:折扣率
    @RequestMapping("/discount")
    @ResponseBody
    public String discount(String listId,int discount,HttpServletRequest request) throws JsonProcessingException {

        //得到登录的用户
     // userName=  request.getSession().getAttribute("");


        List<String> list=null;
        //将listId转换为list集合
        ObjectMapper mapper=new ObjectMapper();
        try {
             list=(List<String>) mapper.readValue(listId,Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //将促销状态改为审核中
        for (String f:list) {
            service.updateGoodspromotionState(f,"审核中");
        }

        //根据id集合查询对象
        List<GoodsEntity> goodsList= service.findByGoodsId(list);
       Map<String,Object> map=new HashMap<>();
       map.put("goodsList",goodsList);
       map.put("goodsDiscount",discount);
//        map.put("userName",userName);
        //创建消息队列并取名
        ActiveMQQueue queue=new ActiveMQQueue("CuXiaoMQ");
        //将map转换为字符串
      String json=  mapper.writeValueAsString(map);
        //将消息队列和数据放入到mq中
        jmsMessagingTemplate.convertAndSend(queue,json);
        String info="yes";
        return info;
    }


    //监听促销审核返回的MQ
    @RequestMapping("/getCuXiaoMQ")
    @ResponseBody
    @JmsListener(destination = "CuXiaoResultMQ")
    public String getCuXiaoMQ(String resultMq) throws IOException {

   // 将监听到的消息转换为map
    ObjectMapper mapper=new ObjectMapper();
    Map<String,Object> map= mapper.readValue(resultMq,Map.class);
       String goodsId= (String)map.get("goodId");
       String auditInfo=(String)map.get("auditInfo");
       int goodsDiscount=(int)map.get("goodsDiscount");

       //根据得到的id进行修改促销状态和修改审核状态
       String info="";
        System.out.printf(""+goodsId);
        if ("审核通过".equals(auditInfo)){
            service.updatPromotion(goodsId,"正在促销",goodsDiscount);
        info="yes";
        }else {
            service.updateGoodspromotionState(goodsId,"促销审核未通过");
            info="no";
        }
        return info;
    }

    @RequestMapping("/ffff")
    @ResponseBody
    public String fndsss(){

        return "yes";
    }
}
