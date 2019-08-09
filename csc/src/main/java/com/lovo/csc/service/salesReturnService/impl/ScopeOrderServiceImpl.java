package com.lovo.csc.service.salesReturnService.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovo.csc.dao.salesReturnDao.IOrderDao;
import com.lovo.csc.dao.salesReturnDao.IScopeOrderDao;
import com.lovo.csc.dao.salesReturnDao.IScopeOrderWSDao;
import com.lovo.csc.entity.salesReturnEntity.OrderEntity;
import com.lovo.csc.entity.salesReturnEntity.ScopeOrderEntity;
import com.lovo.csc.entity.salesReturnEntity.ScopeOrderVo;
import com.lovo.csc.service.salesReturnService.IScopeOrderService;
import com.lovo.csc.util.MyStringUtil;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "scopeOrderService")
public class ScopeOrderServiceImpl implements IScopeOrderService {
    @Autowired
    private IScopeOrderDao scopeOrderDao;
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IScopeOrderWSDao scopeOrderWSDao;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private ActiveMQQueue accountsRegistrationAuditResultMQ;

    @Override
    public List<ScopeOrderEntity> findByScopeOrder(String orderState, String orderId, String supplierName, int pageNumber, int pageSize) {
        pageNumber= pageNumber-1;
        List<ScopeOrderEntity>list=orderDao.findByScopeOrder(orderState,orderId,supplierName,pageNumber,pageSize);
        return list;
    }

    @Override
    public long getPageListScopeOrderCount(String orderState, String orderId, String supplierName) {
        return orderDao.getPageListScopeOrderCount(orderState,orderId,supplierName);
    }
    @Override
    public ScopeOrderEntity sava(ScopeOrderEntity scopeOrderEntity) {

        return scopeOrderWSDao.save(scopeOrderEntity);
    }



    @Override
    public List<OrderEntity> findByScopeOrder(String Id) {
        List<OrderEntity> list=scopeOrderDao.findByScopeOrder(Id);
        return list;
    }

    @Override
    public String updateScopeOrder(ScopeOrderEntity scopeOrderEntity, String orderState) {
        scopeOrderEntity.setOrderState(orderState);
        String userState=scopeOrderEntity.getOrderState();
        scopeOrderWSDao.save(scopeOrderEntity);
        ScopeOrderVo vo=new ScopeOrderVo();
        vo.setOrderId(scopeOrderEntity.getOrderId());
        vo.setAuditOpinion(scopeOrderEntity.getOrderOpinion());
        vo.setCloseTime(scopeOrderEntity.getCloseTime());
        vo.setOrderState(orderState);
        vo.setSupplierName(scopeOrderEntity.getSupplierName());
        String json="";
        try {
            json =  MyStringUtil.transitionObjectToString(vo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        jmsMessagingTemplate.convertAndSend(accountsRegistrationAuditResultMQ,json);

        return scopeOrderEntity.getOrderId();
    }

    @Override
    public List<OrderEntity> findByOrder(String Id, int pageNumber, int pageSize) {
        pageNumber= pageNumber-1;
        List<OrderEntity>list=orderDao.findByOrder(Id,pageNumber,pageSize);
        return list;
    }

    @Override
    public long getPageListOrderCount(String Id) {
        return orderDao.getPageListOrderCount(Id);
    }
}
