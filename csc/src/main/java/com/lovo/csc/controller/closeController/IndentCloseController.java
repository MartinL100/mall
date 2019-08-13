package com.lovo.csc.controller.closeController;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.entity.supplierEntity.IndentEntity;
import com.lovo.csc.service.closeService.IIndentCloseService;
import com.lovo.csc.util.NowTime;
import com.lovo.csc.util.promotionutil.DateFormat;
import com.lovo.csc.vo.IndentDto;
import com.lovo.csc.vo.IndentSupplyDto;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
public class IndentCloseController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private ActiveMQQueue scopeMQ2;
    @Autowired
    private IIndentCloseService indentCloseService;

    @RequestMapping("findIndentCloseList.lovo")
    public Map<String,Object> page(int page, int rows, String startTime, String endTime, String indentStatus){
        Map<String,Object> map=new HashMap<>();
        List<IndentEntity> list= indentCloseService.findIndentList((page-1)*rows,rows,startTime,endTime,indentStatus);
        map.put("rows",list);
        map.put("page",page);
        long total=indentCloseService.countIndentList(startTime,endTime,indentStatus);
        map.put("total",total);
        return  map;
    }

    @RequestMapping("findSupplyCloseList.lovo")
    public Map<String,Object> findBySupplierId(int page, int rows,String indentId){
        Map<String,Object> map=new HashMap<>();
        List<IndentSupplyDto> list=indentCloseService.findSupplyListByIndentId((page-1)*rows,rows,indentId);

//        System.out.println(list.size());

        map.put("rows",list);
        map.put("page",page);
        long total=indentCloseService.countIndentSupplyListByIndentId(indentId);
        map.put("total",total);
        return  map;
    }

    @RequestMapping("indentCloseUpdate.lovo")
    public void updateIndent( String indentId){
        IndentEntity indentEntity=indentCloseService.findByIndentId(indentId);

        String now=NowTime.getNowTime();
        indentEntity.setCloseTime(new DateFormat().getNow());
        indentEntity.setIndentStatus("已结算");
        indentCloseService.save(indentEntity);

        //建立MQ并返回数据
        IndentDto indentDto=new IndentDto();
        indentDto.setIndentId(indentId);
        indentDto.setIndentDate(new DateFormat().getNow());
        ObjectMapper mapper=new ObjectMapper();
        try {
            String v=mapper.writeValueAsString(indentDto);
            jmsMessagingTemplate.convertAndSend(scopeMQ2,v);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    //监听scopeMQ1并修改订单状态为未结算
    @JmsListener(destination = "scopeMQ1")
    public void updateIndentStatusFirst(String message) {
        List<String> list=new ArrayList<String>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
//            indentDto = objectMapper.readValue(message, IndentDto.class);
            list=objectMapper.readValue(message,new TypeReference<List<String>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String str:list) {
            String indentId = str;
            IndentEntity indentEntity = indentCloseService.findByIndentId(indentId);
            indentEntity.setIndentStatus("未结算");
            indentCloseService.save(indentEntity);
        }
    }
}
