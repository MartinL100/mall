package com.lovo.psc.wangwei.controller;


import com.alibaba.fastjson.JSONArray;
import com.lovo.psc.entity.SupplierEntity;
import com.lovo.psc.entity.SupplierGoodsEntity;
import com.lovo.psc.wangwei.service.ISupplierGoodsQueryService;
import org.apache.activemq.command.ActiveMQQueue;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;


import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GoodsController4 {


    @Autowired
    private ISupplierGoodsQueryService supplierGoodsQueryService;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("pageGoods")
    public Map<String,Object> page(int page, int rows, String goodsName, String supplierName, String goodsType, String codeId,HttpServletRequest request) {


//        SupplierEntity   a  = (SupplierEntity) request.getSession().getAttribute("SupplierEntity");
//        supplierName =a.getSupplierId();
        supplierName="1";
        List<SupplierGoodsEntity> list = supplierGoodsQueryService.getPageListGoods(goodsName, supplierName, goodsType, codeId,page, rows);
          for(int i=0;i<list.size();i++){
              list.get(i).setSupplier(null);
          }

        Map<String, Object> map = new HashMap<>();


        map.put("rows", list);
        map.put("page", page);
        long total = supplierGoodsQueryService.getPageListGoodsCount(goodsName, supplierName, goodsType, codeId);
        map.put("total", total);
        System.out.println(map.toString());

        return map;
    }


    @RequestMapping("savaGoods")
    public String saveGoods(String goodsName, String goodsNoms, String goodsType, String goodsUnit, int goodsNum,HttpServletRequest requst) {
     SupplierGoodsEntity s=new SupplierGoodsEntity();


        SupplierEntity   s1  = (SupplierEntity) requst.getSession().getAttribute("SupplierEntity");




     s.setGoodsUnit(goodsUnit);
     s.setGoodsNum(goodsNum);
     s.setGoodsType(goodsType);
     s.setGoodsNoms(goodsNoms);
     s.setGoodsName(goodsName);
     s.setSupplierStatus("等待审核");
     s.setSupplierType("上架");
     s.setSupplier(s1);


    supplierGoodsQueryService.savaGoods(s);

        Object obj = JSONArray.toJSON(s);
        String json = obj.toString();

        ActiveMQQueue gueue=new ActiveMQQueue("SupplierGoodsAudit");
        jmsMessagingTemplate.convertAndSend(gueue,json);


        System.out.println(json);
        return "{'errorMsg':false}";

    }

    @RequestMapping("updateGoods")
    public String updateStudent(String goodsName, String goodsNoms, String goodsType, String goodsUnit, int goodsNum,String codeId) {
       supplierGoodsQueryService.update( goodsName,  goodsNoms,  goodsType,  goodsUnit,  goodsNum,codeId);
        return "{'errorMsg':false}";

    }

    @RequestMapping("delGoods")
    @ResponseBody
    public String delGoods(String codeId) {
      SupplierGoodsEntity s=  supplierGoodsQueryService.finByiD(codeId);
        s.setSupplierType("下架");
        s.setSupplierStatus("");
   supplierGoodsQueryService.savaGoods(s);

        Object obj = JSONArray.toJSON(s);
        String json = obj.toString();
        ActiveMQQueue gueue = new ActiveMQQueue("SupplierGoodsAudit1111");
        jmsMessagingTemplate.convertAndSend(gueue, json);



        return "OK";
    }

    @JmsListener(destination = "returnSupplierGoodsAudit ")
    public void getPointMessage(String message){
        ObjectMapper  o=new ObjectMapper();
        try {
        SupplierGoodsEntity s1=    o.readValue(message,SupplierGoodsEntity.class);
            String supplierStatus=s1.getSupplierStatus();
            String supplierType=s1.getSupplierType();
            String CodeId=s1.getCodeId();
            SupplierGoodsEntity s=  supplierGoodsQueryService.finByiD(CodeId);
            if(supplierType=="上架"){
                s.setSupplierStatus(supplierStatus);
                supplierGoodsQueryService.savaGoods(s);
            }
            if(supplierType=="下架"&&supplierStatus=="取消供应"){
                supplierGoodsQueryService.delgoods(CodeId);
            }




        } catch (IOException e) {
            e.printStackTrace();
        }



        //回执消息
        ActiveMQQueue queue=new ActiveMQQueue("ResultQueue");
        jmsMessagingTemplate.convertAndSend(queue,"已经收到");
    }

    @JmsListener(destination = "SupplierGoodsAudit")
    public void getPointMessage1(String message) {
       if(message!=null){
    System.out.println(message+"接收到消息");
}
    }

    @RequestMapping("findSupplierByID/{Id}")
    @ResponseBody
    public SupplierEntity findGoodsByID(@PathVariable("Id") String Id){

        SupplierEntity s=   supplierGoodsQueryService.fininId(Id);



        return  s;
    }

}
