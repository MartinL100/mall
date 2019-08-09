package com.lovo.sscafter.customerRetention.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.customerRetention.Entity.DTO.PreserveMessageDTO;
import com.lovo.sscafter.customerRetention.Entity.DTO.PreserveResultDTO;
import com.lovo.sscafter.customerRetention.Entity.DTO.UserUnfreezeDto;
import com.lovo.sscafter.customerRetention.Entity.DTO.UserUnfrozenUpsetDto;
import com.lovo.sscafter.customerRetention.Entity.UserEntity;
import com.lovo.sscafter.customerRetention.controller.loginController;
import com.lovo.sscafter.customerRetention.util.MqUtil;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MQ {

    //远程调用的模板
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @JmsListener(destination = "requestUnfreezeMQ")
    //监听前台解冻MQ
    public void thawUserMQ(String userUnfreezeStr){
        ObjectMapper om = new ObjectMapper();
        try{
        UserUnfreezeDto userUnfreezeDto = om.readValue(userUnfreezeStr,UserUnfreezeDto.class);
        //创建审核对象
        PreserveMessageDTO predto = new PreserveMessageDTO();
        predto.setUserNameStr(userUnfreezeDto.getUserName());
        predto.setAuditTime(userUnfreezeDto.getAuditTime());
        predto.setAuditOpinion(userUnfreezeDto.getApplyReason());
        predto.setAuditType("解冻");
        predto.setMaintenanceManager(loginController.userName);

        //审核对象放入mq
        String predtoStr = om.writeValueAsString(predto);

        ActiveMQQueue queue=new ActiveMQQueue("frozenOrUnfrozenAccountsMessageMQ");
        jmsMessagingTemplate.convertAndSend(queue,predtoStr);

        //放入队列, 方便页面刷新

            MqUtil.queue.put("true");
        }catch (Exception e){e.printStackTrace();}
    }
    //监听后台审核MQ
    public void verifyUserMQ(String preresultStr){
        ObjectMapper om = new ObjectMapper();
        try{
        PreserveResultDTO preresult = om.readValue(preresultStr,PreserveResultDTO.class);
        //1的话就是解冻,像前段发送MQ
        if("1".equals(preresult.getUserState())){
            for (String userName:preresult.getUserNameArray()) {
                restTemplate.getForEntity("http://sscafter/update2/"+userName+"/"+preresult.getUserState(),String.class).getBody();
                //将理由等放入MQ
                ActiveMQQueue queue=new ActiveMQQueue("frozenOrUnfrozenUpsetMQ");
                UserUnfrozenUpsetDto userUnfrozenUpsetDto = new UserUnfrozenUpsetDto();
                userUnfrozenUpsetDto.setUserName(userName);
                userUnfrozenUpsetDto.setAuditOpinion(preresult.getAuditOpinion());
                userUnfrozenUpsetDto.setAuditReplyTime(preresult.getAuditReplyTime());
                String userUnfrozenUpsetStr = om.writeValueAsString(userUnfrozenUpsetDto);
                jmsMessagingTemplate.convertAndSend(queue,userUnfrozenUpsetStr);
            }
        }else {
            //这里为冻结,
            for (String userName:preresult.getUserNameArray()) {
                //直接调用接口修改用户状态
                restTemplate.getForEntity("http://sscafter/update2/"+userName+"/"+preresult.getUserState(),String.class).getBody();
            }
        }
        }catch (Exception e){e.printStackTrace();}
    }



}
