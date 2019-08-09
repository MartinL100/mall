package com.lovo.csc.service.salesReturnService.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovo.csc.dao.salesReturnDao.IGoodsDao;
import com.lovo.csc.dao.salesReturnDao.IOrderGoodsDao;
import com.lovo.csc.entity.salesReturnEntity.GoodsEntity;
import com.lovo.csc.entity.salesReturnEntity.OrderGoodsEntity;
import com.lovo.csc.entity.salesReturnEntity.OrderGoodsVo;
import com.lovo.csc.service.salesReturnService.IOrderGoodsService;
import com.lovo.csc.util.MyStringUtil;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "orderGoodsService")
public class OrderGoodsServiceImpl implements IOrderGoodsService {
    @Autowired
    private IOrderGoodsDao orderGoodsDao;
    @Autowired
    private IGoodsDao goodsDao;
//    @Autowired
//    private IGoodDao goodDao;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private ActiveMQQueue accountsRegistrationAuditResultMQ;

    @Override
    public OrderGoodsEntity savaOrderGoods(OrderGoodsEntity orderGoodsEntity) {
        return orderGoodsDao.save(orderGoodsEntity);
    }

    @Override
    public String updateOrderGoods(OrderGoodsEntity orderGoodsEntity, String goodsState) {
        orderGoodsEntity.setGoodsState(goodsState);
        String userStates=orderGoodsEntity.getGoodsState();
//        if (!"审核通过".equals(userStates)){
//        }
        orderGoodsDao.save(orderGoodsEntity);
        OrderGoodsVo vo=new OrderGoodsVo();
        vo.setOrderName(orderGoodsEntity.getOrderName());
        vo.setGoodsDate(goodsState);
        vo.setAuditOpinion(orderGoodsEntity.getAuditOpinion());
        vo.setGoodsDate(orderGoodsEntity.getAuditOpinion());
        vo.setOrderId(orderGoodsEntity.getOrderId());
        String json="";
        try {
            json =  MyStringUtil.transitionObjectToString(vo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        jmsMessagingTemplate.convertAndSend(accountsRegistrationAuditResultMQ,json);
        return orderGoodsEntity.getOrderId();
    }

    @Override
    public List<OrderGoodsEntity> findByOrderId(String goodsState, String orderId, String orderName, int pageNumber, int pageSize) {

        pageNumber= pageNumber-1;
        List<OrderGoodsEntity>list=goodsDao.findByOrderId(goodsState,orderId,orderName,pageNumber,pageSize);
        return list;
    }

    @Override
    public long getPageListOrderGoodsEntityCount(String goodsState, String orderId, String orderName) {

        return goodsDao.getPageListOrderGoodsEntityCount(goodsState,orderId,orderName);
    }

//    @Override
//    public List<GoodsEntity> findByOrderGoods(String orderId) {
//
//        return orderGoodsDao.findByOrderGoods(orderId);
//    }

    @Override
    public List<GoodsEntity> findByGoods(String goodsId, int pageNumber, int pageSize) {
        pageNumber= pageNumber-1;
        List<GoodsEntity>list=goodsDao.findByGoods(goodsId,pageNumber,pageSize);
        return list;
    }

    @Override
    public long getPageListGoodsCount(String goodsId) {
        return goodsDao.getPageListGoodsCount(goodsId);
    }


}
