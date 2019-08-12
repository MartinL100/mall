package com.lovo.csc.controller.clientcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.entity.AuditEntity;
import com.lovo.csc.entity.SysFrozenOrUnfrozenAccountsEntity;
import com.lovo.csc.service.clientService.IUserAuditService;
import com.lovo.csc.util.WebSocketServerTwo;
import com.lovo.csc.vo.clientvo.PreserveMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
public class AccountsAuditController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private IUserAuditService userAuditService;
    private static int initNum=1;



    //分页
    @RequestMapping("frozenOrUnfrozenAccountsPage.lovo")
    public Map<String, Object> frozenOrUnfrozenAccountsPage(String tag,int page,int rows,String auditState,String auditType, String startTime, String endTime) {
        Map<String, Object> map = new HashMap<>();
        List<SysFrozenOrUnfrozenAccountsEntity> list=null;
        long total=0;

            PageRequest pageable= PageRequest.of((page-1)*rows,rows);
            list =userAuditService.DynamicQuerySysFrozenOrUnfrozenAccountsEntity(auditState,auditType,startTime,endTime,pageable);
            total=userAuditService.DynamicQuerySysFrozenOrUnfrozenAccountsEntityCount(auditState,auditType,startTime,endTime);

        map.put("rows", list);
        map.put("pageTwo", page);
        map.put("total", total);
        return map;
    }


    //保存
    @JmsListener(destination = "frozenOrUnfrozenAccountsMessageMQ ")
    @RequestMapping("saveFrozenOrUnfrozenAccountsEntity.lovo")
    public void saveSysFrozenOrUnfrozenAccountsEntity(String message) {
        if (null==message||"".equals(message)){
            return;
        }
        PreserveMessageVo vo=null;
        try {
            vo=new ObjectMapper().readValue(message,PreserveMessageVo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != vo) {
            ToFrozenOrUnfrozenAccountsMsg(vo);
            try {
                WebSocketServerTwo.blockingQueue.put(vo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //修改
    @RequestMapping("updateFrozenOrUnfrozenAccountsEntity.lovo")
    public String updateFrozenOrUnfrozenAccountsEntity(String frozenOrUnfrozenAccountsMessageId,SysFrozenOrUnfrozenAccountsEntity infoBean, HttpServletRequest request){
       // System.out.println((infoBean.getFrozenOrUnfrozenAccountsMessageId()));
        SysFrozenOrUnfrozenAccountsEntity info=
                userAuditService.findSysFrozenOrUnfrozenAccountsEntityById(frozenOrUnfrozenAccountsMessageId);
              AuditEntity auditEntity= (AuditEntity) request.getSession().getAttribute("auditObj");
             String re=  userAuditService.CycleUpdateUserAuditInformation(info,auditEntity.getAuditPeople());
       // String re=  userAuditService.CycleUpdateUserAuditInformation(infoBean,"光");
        if (null!=re&&"操作成功".equals(re)){
            return "{'successMsg':'操作成功'}";
        }
        return "{'errorMsg':'操作失败'}";
    }

    //根据id查找该数据
    @RequestMapping("findFrozenOrUnfrozenAccountsEntityById.lovo")
    public SysFrozenOrUnfrozenAccountsEntity findById(String id){
        return  userAuditService.findSysFrozenOrUnfrozenAccountsEntityById(id);
    }


    //服务器重启 从MQ中取出数据并保存到数据库
   @RequestMapping("severUserAccountsMQInit.lovo")
    public String severUserAccountsInit(){
        if (initNum!=1){
            return "notInit";
        }
        String MQJson="";
        PreserveMessageVo vo=null;
        boolean lock=true;
        while (lock){
            TextMessage textMessage= (TextMessage)(jmsMessagingTemplate.getJmsTemplate()).
                    receive("frozenOrUnfrozenAccountsMessageMQ");
            try {
                MQJson=textMessage.getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            //取完
            if (null==MQJson||"".equals(MQJson)){
                lock=false;
                return "getEnd";
            }
            else{
                try {
                    vo=  new ObjectMapper().readValue(MQJson,PreserveMessageVo.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ToFrozenOrUnfrozenAccountsMsg(vo);
            }
        }
        return "ok";
    }

    private void ToFrozenOrUnfrozenAccountsMsg(PreserveMessageVo vo) {
        SysFrozenOrUnfrozenAccountsEntity frozenOrUnfrozenAccountsEntity
                = new SysFrozenOrUnfrozenAccountsEntity();
//      String[] userNameArray=  vo.getUserNameStr().split(",");
        frozenOrUnfrozenAccountsEntity.setAuditState("未审核");
        frozenOrUnfrozenAccountsEntity.setAuditType(vo.getAuditType());
        frozenOrUnfrozenAccountsEntity.setAuditOpinion(vo.getAuditOpinion());
        frozenOrUnfrozenAccountsEntity.setUserNameStr(vo.getUserNameStr());
        frozenOrUnfrozenAccountsEntity.setAuditTime(vo.getAuditTime());
        userAuditService.savaFrozenOrUnfrozenAccountsEntity(frozenOrUnfrozenAccountsEntity);
    }


//    @RequestMapping("testSaveAccounts")
//    public void saveAccounts(){
//        System.out.println("==============================================================================");
//        for (int i = 1; i <= 20; i++) {
//        SysFrozenOrUnfrozenAccountsEntity Accounts=
//                new SysFrozenOrUnfrozenAccountsEntity();
//        Accounts.setUserNameStr("zs"+i+",ls"+i);
//        Accounts.setAuditOpinion("非法登录");
//        Accounts.setAuditType("冻结");
//        Accounts.setAuditState("未审核");
//        Accounts.setAuditTime(MyStringUtil.getFormMatTime());
//        Accounts.setMaintenanceManager("大古");
//        userAuditService.savaFrozenOrUnfrozenAccountsEntity(Accounts);
//        System.out.println(Accounts.getFrozenOrUnfrozenAccountsMessageId());
//        }
//        System.out.println("=========================" +
//                "=====================================================");
//    }

}
