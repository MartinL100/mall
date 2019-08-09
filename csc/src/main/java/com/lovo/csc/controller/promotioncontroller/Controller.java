package com.lovo.csc.controller.promotioncontroller;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.entity.promotionentity.SuperSaleAudit;
import com.lovo.csc.service.promotionService.ISuperSaleAuditService;
import com.lovo.csc.util.promotionutil.DateFormat;
import com.lovo.csc.util.WebSocketServer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private ISuperSaleAuditService superSaleAuditService;
    @Autowired
    private WebSocketServer webSocketServer;
    private ActiveMQQueue activeMQQueue=new ActiveMQQueue("CuXiaoResultMQ");
    private DateFormat  date_format=new DateFormat();
    private ObjectMapper objectMapper=new ObjectMapper();
    private int number;
    @JmsListener(destination = "CuXiaoMQ")
    public void getPointMessage(String message) throws Exception {
        String jsonstr=message;
        Map jsonMap = JSON.parseObject(jsonstr,Map.class);
        List list= (List) jsonMap.get("goodsList");
        String applyMan= (String) jsonMap.get("applyMan");
        String applyTime=date_format.getNow();
        for (Object  object :list) {
            Map map=(Map) object;
            SuperSaleAudit superSaleAudit=new SuperSaleAudit
                    (map.get("goodsId").toString(), map.get("goodsName").toString(), map.get("goodsPrice").toString(),  map.get("goodsType").toString() , map.get("goodsNorms").toString() , map.get("goodsUnit").toString() , map.get("goodsNum").toString() ,map.get("goodsBid").toString() ,map.get("goodsDiscount").toString() ,applyMan , applyTime);
            superSaleAuditService.saveSuperSaleAudit(superSaleAudit);//放入数据库
        }
        number++;
//        while(webSocketServer.getSession()!=null){
//        webSocketServer.sendMessage("有新的待审核信息");}
    }
    @RequestMapping("checkAdd")
    public String checkAdd() {
        return number+"";
    }

    @RequestMapping("/findSuperSaleList")
    public Map findSuperSaleList(HttpServletRequest request,int rows) {
        //从request中获取数据
        String auditResult = request.getParameter("auditResult");
        String goodsName = request.getParameter("goodsName");
        String startTime = request.getParameter("startTime");
        String pagestr = request.getParameter("pageTwo");
        int page=1;
        if (request.getParameter("pageTwo")!=null){
             page=Integer.parseInt(pagestr);
        }
        if (startTime!=null&&startTime.length()>0){
            String[] strArr = startTime.split("\\/");
            startTime=strArr[2]+"-"+strArr[0]+"-"+strArr[1];
        }
        String endTime = request.getParameter("endTime");
        if (endTime!=null&&endTime.length()>0){
            String[] strArr = endTime.split("\\/");
            endTime=strArr[2]+"-"+strArr[0]+"-"+strArr[1];
        }
//        if(!date_format.checkDate(startTime)){
//            return "时间格式错误";
//        }
//        if(!date_format.checkDate(endTime)){
//            return "时间格式错误";
//        }

        Map<String,Object> map=new HashMap<>();
        List<SuperSaleAudit> lists= superSaleAuditService.findSuperSaleAudits
                ("", "", auditResult, "","", "");
        int firstResult=rows*(page-1);
        List<SuperSaleAudit> list = superSaleAuditService.findSuperSaleAudits
                (startTime, endTime, auditResult, goodsName, firstResult+"", rows+"");
        map.put("rows",list);
        map.put("pageTwo",page);
        long total=lists.size();
        map.put("total",total);
        return  map;

    }

    @RequestMapping("/findSuperSale")
    public SuperSaleAudit findSuperSale(HttpServletRequest request) {
        //从request中获取数据
        String id = request.getParameter("id");
       SuperSaleAudit superSaleAudit = superSaleAuditService.findSuperSaleAuditById(Integer.parseInt(id));
        return  superSaleAudit;
    }
    @RequestMapping("/updateSuperSale")
    public String updateSuperSale(HttpServletRequest request) throws JsonProcessingException {
        //从request中获取数据
        String str =request.getParameter("jsonlist");
        List jsonList = JSON.parseObject(str,List.class);
        for (Object object :jsonList) {
            Map maps= (Map)object;
            String id = maps.get("id")+"";
             String goodsId = maps.get("goodsId")+"";//mq传递的唯一标识
            String goodsDiscount =maps.get("goodsDiscount")+"";
            String auditResult =maps.get("auditResult")+"";

//        String auditMan = request.getSession().getAttribute("userName").toString();
            String auditTime= date_format.getNow();//获取当前时间
            superSaleAuditService.updateSuperSaleAudit("1号审核员",auditTime,auditResult,Integer.parseInt(id));
            Map map=new HashMap();
            map.put("goodId",goodsId);
            map.put("goodsDiscount",goodsDiscount);
            map.put("auditInfo",auditResult);
            //将返回信息放入mq
            jmsMessagingTemplate.convertAndSend(activeMQQueue,objectMapper.writeValueAsString(map));
        }
        return  "审核成功";
    }
}
