package com.lovo.sscafter.customerRetention.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.customerRetention.Entity.DTO.CustomerDTO;
import com.lovo.sscafter.customerRetention.Entity.DTO.PreserveMessageDTO;
import com.lovo.sscafter.customerRetention.Entity.UserEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class freezeController {
//    //远程调用的模板
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @HystrixCommand(fallbackMethod = "getInfoError")
    @RequestMapping("freezePage")
    @ResponseBody
    public Map<String,Object> freezePage(int page, int rows, String userName, String userState){
        //远程调用,假设得到list
        if(null == userName ||"".equals(userName) ){
            userName = "no";
        }
        if(null == userState ){
            userState = "0";
        }
        List<CustomerDTO> list = restTemplate.getForEntity("http://servicename/userList/{userName}/{userState}/{currentPage}/{rows}",List.class).getBody();
//        //----
//        List<CustomerDTO> list = new ArrayList<>();
//        for (int i =0;i<10;i++){
//            CustomerDTO customerDTO = new CustomerDTO();
//            customerDTO.setUserId("asdasd"+i);
//            customerDTO.setUserName("lw"+i);
//            customerDTO.setPassword("123456"+i);
//            customerDTO.setTrueName("廖文"+i);
//            customerDTO.setSex("男");
//            customerDTO.setTelephone("15184483910");
//            customerDTO.setUserState("正常");
//
//            list.add(customerDTO);
//        }
        //----
        Map map = new HashMap();
        map.put("rows",list);
        map.put("page",page);
        //假设总行数为 远程调用
        int userRows = restTemplate.getForEntity("http://servicename/userRows/{userName}/{userState}",Integer.class).getBody();
        map.put("total",userRows);
        return map;
    }

    @RequestMapping("freezeUser")
    @ResponseBody
    public void freezeUser(String jsonStr, HttpServletRequest request,String account) throws Exception{
        ObjectMapper om = new ObjectMapper();
        List<String> list = null;
        try {
            list = (List<String>)om.readValue(jsonStr,Object.class);

        }catch (Exception e){e.printStackTrace();}
        StringBuffer userNameStr = new StringBuffer();
        for (String user:list) {
            userNameStr.append(user +",");
        }
        PreserveMessageDTO predto = new PreserveMessageDTO();
        predto.setUserNameStr(userNameStr+"");
        predto.setAuditType("冻结");
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String AuditTime = sdf.format(System.currentTimeMillis());
        predto.setAuditTime(AuditTime);

        predto.setMaintenanceManager(((UserEntity)(request.getSession().getAttribute("userName"))).getUserName1());
        predto.setAuditOpinion(account);
        //放入mq
        ActiveMQQueue queue=new ActiveMQQueue("frozenOrUnfrozenAccountsMessageMQ");
        ObjectMapper om1 = new ObjectMapper();
        String predtoStr = om1.writeValueAsString(predto);
        jmsMessagingTemplate.convertAndSend(queue,predtoStr);
    }
}
