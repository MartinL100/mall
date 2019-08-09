package com.lovo.csc.service.clientService.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovo.csc.dao.clientdao.IFrozenOrUnfrozenAccountsDao;
import com.lovo.csc.dao.clientdao.IUserAuditNormalDao;
import com.lovo.csc.dao.clientdao.IUserRegistraTionAuditDao;
import com.lovo.csc.entity.SysFrozenOrUnfrozenAccountsEntity;
import com.lovo.csc.entity.SysUserAuditInformationEntity;
import com.lovo.csc.service.clientService.IUserAuditService;
import com.lovo.csc.util.MyStringUtil;
import com.lovo.csc.vo.clientvo.PreserveResultVo;
import com.lovo.csc.vo.clientvo.RegisterResultVo;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userAuditService")
public class UserAuditServiceImpl implements IUserAuditService {
    @Autowired
    private IFrozenOrUnfrozenAccountsDao frozenOrUnfrozenAccountsDao;
    @Autowired
    private IUserAuditNormalDao userAuditNormalDao;
    @Autowired
    private IUserRegistraTionAuditDao userRegistraTionAuditDao;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private ActiveMQQueue accountsRegistrationAuditResultMQ;
    @Autowired
    private ActiveMQQueue frozenOrUnfrozenAccountsResultMQ;
    Log log= LogFactory.getLog(this.getClass());


    @Override
    public SysUserAuditInformationEntity findSysUserAuditInformationEntityByName(String userName) {
        return userRegistraTionAuditDao.findByUserName(userName);
    }

    @Override
    public List<SysUserAuditInformationEntity> PageInitList(int page,int rows) {
        Pageable pa=PageRequest.of(page,rows);
        return   userRegistraTionAuditDao.PageInitList(pa);
    }


    @Override
    public long getPageInitCount() {
        return userRegistraTionAuditDao.getPageInitCount();
    }


    @Override
    public String savaUserAuditMessage(SysUserAuditInformationEntity userAuditInformationEntity) {
        return userRegistraTionAuditDao.
                save(userAuditInformationEntity).
                getUserAuditInformationId();
    }

    @Override
    public String updateUserAuditMessage(SysUserAuditInformationEntity userAuditInformationEntity,String auditPerson) {
        String userName=userAuditInformationEntity.getUserName();
        //根据name查找用户信息
        SysUserAuditInformationEntity infoBean=
                findSysUserAuditInformationEntityByName(userName);
        //
       // userAuditInformationEntity.setAuditReplyTime(MyStringUtil.getFormMatTime());



        String userState=userAuditInformationEntity.getUserState();
        infoBean.setAuditPerson(auditPerson);
        infoBean.setAuditReplyTime(MyStringUtil.getFormMatTime());
        infoBean.setMaintenanceManager(auditPerson);
        infoBean.setUserState(userState);
        infoBean.setAuditOpinion(userAuditInformationEntity.getAuditOpinion());
        String state="";
        //vo中默认返回结果状态为1
        state="1";
        //如果审核不通过 将结果状态改成2
        if (!"审核通过".equals(userState)){
            infoBean.setIdentityImg("");
            infoBean.setAptitudeImg("");
           state="2";
        }
                userRegistraTionAuditDao.save(infoBean);
                RegisterResultVo vo =new RegisterResultVo();
                vo.setAuditOpinion(userAuditInformationEntity.getAuditOpinion());
                vo.setAuditReplyTime(MyStringUtil.getFormMatTime());
                vo.setUserGrade(userAuditInformationEntity.getUserGrade());
                vo.setUserState(state);
                vo.setUserName(userAuditInformationEntity.getUserName());
        String json="";
        try {
          json =  MyStringUtil.transitionObjectToString(vo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        jmsMessagingTemplate.convertAndSend(accountsRegistrationAuditResultMQ,json);
                return userAuditInformationEntity.getUserAuditInformationId();

    }



    @Override
    public String savaFrozenOrUnfrozenAccountsEntity(SysFrozenOrUnfrozenAccountsEntity SysFrozenOrUnfrozenAccountsEntity) {
        return frozenOrUnfrozenAccountsDao.
                save(SysFrozenOrUnfrozenAccountsEntity).
                getFrozenOrUnfrozenAccountsMessageId();
    }


    @Override
    public double getUserDiscount(String userName) {
     String level=userRegistraTionAuditDao.findByUserName(userName).getUserGrade();
     if (level.equals("会员")){
         return 0.9;
     }
        return  1.0;
    }


    @Override
    public SysUserAuditInformationEntity getSysUserAuditInformationEntity(String userName) {
        return userRegistraTionAuditDao.findByUserName(userName);
    }


    @Override
    public List<SysUserAuditInformationEntity> DynamicQueryAuditInformation(String userState, String startTime, String endTime, Pageable page) {
        return userAuditNormalDao.DynamicQueryAuditInformation
                (userState,startTime,endTime,page);
    }


    @Override
    public long DynamicQueryAuditInformationCount(String userState, String startTime, String endTime) {
        return userAuditNormalDao.DynamicQueryAuditInformationCount
                (userState,startTime,endTime);
    }


    @Override
    public List<SysFrozenOrUnfrozenAccountsEntity> DynamicQuerySysFrozenOrUnfrozenAccountsEntity(String auditState,String auditType, String startTime, String endTime, Pageable page) {
        return userAuditNormalDao.DynamicQuerySysFrozenOrUnfrozenAccountsEntity
                (auditState,auditType,startTime,endTime,page);
    }


    @Override
    public long DynamicQuerySysFrozenOrUnfrozenAccountsEntityCount(String auditState,String auditType, String startTime, String endTime) {
        return userAuditNormalDao.DynamicQuerySysFrozenOrUnfrozenAccountsEntityCount
                (auditState, auditType,startTime,endTime);
    }


    @Override
    public SysFrozenOrUnfrozenAccountsEntity findSysFrozenOrUnfrozenAccountsEntityById(String id) {
        return frozenOrUnfrozenAccountsDao.getFrozenOrUnfrozenAccountsMessageById(id);
    }





    //
    public String CycleUpdateUserAuditInformation
    (SysFrozenOrUnfrozenAccountsEntity obj,String auditPerson){

        String userNameStr = obj.getUserNameStr();
        String auditOpinion=obj.getAuditOpinion();
        //得到审核结果
        String auditResult=obj.getAuditResult();
        //得到请求类型
         String auditType=   obj.getAuditType();
         //得到审核管理员
        String maintenanceManager=obj.getMaintenanceManager();
        String Opinion = "";
        String State="";
        String re="操作失败";
        String objAuditType="";
        String objAuditResultState="";

        if ("冻结".equals(auditType)) {
            if ("审核通过".equals(auditResult)){
                objAuditType=auditType;
                objAuditResultState="已冻结";
                Opinion="已冻结";
                State="已冻结";
            }
            else{
                objAuditType="用户注册审核";
                Opinion=obj.getAuditOpinion();
                State="审核通过";
            }
        }
        else{
            if ("审核通过".equals(auditResult)){
                objAuditType=auditType;
                objAuditResultState="审核通过";
                Opinion="审核通过";
                State="审核通过";
            }
            else{
                objAuditType="冻结";
                Opinion=obj.getAuditOpinion();
                State="已冻结";
            }
        }

        //得到用户名数组
        String[] userNameArray = userNameStr.split(",");
        for (String userName : userNameArray) {
            SysUserAuditInformationEntity info=
                    userRegistraTionAuditDao.findByUserName(userName);
                    info.setAuditOpinion(auditOpinion);
                    //设置账户状态
                    info.setUserState(State);
                    info.setAuditPerson(auditPerson);
                    info.setMaintenanceManager(maintenanceManager);
                    info.setAuditReplyTime(MyStringUtil.getFormMatTime());
                    //设置请求类型
                    info.setAuditType(objAuditType);
                    userRegistraTionAuditDao.save(info);

        }
         PreserveResultVo resultVo=new PreserveResultVo();
        //冻结审核通过,返还数据给维护管理员
        resultVo.setAuditOpinion(Opinion);
        resultVo.setAuditReplyTime(MyStringUtil.getFormMatTime());
        //判断状态
        if("已冻结".equals(State)){
            resultVo.setUserState("3");

        }
        else{
            resultVo.setUserState("1");
        }
        resultVo.setUserNameArray(userNameArray);
        String json="";
        try {
            json=MyStringUtil.transitionObjectToString(resultVo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //将该数据状态改成已处理
        obj.setAuditState("已审核");

        frozenOrUnfrozenAccountsDao.save(obj);
        //将处理结果发送给审核管理员
        jmsMessagingTemplate.convertAndSend(frozenOrUnfrozenAccountsResultMQ,json);
        re="操作成功";
        return re;
    }

}

