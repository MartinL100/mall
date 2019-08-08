package com.lovo.sscafter.goodsStock.service.impl;

import com.lovo.sscafter.goodsStock.dao.IOrderDao;
import com.lovo.sscafter.goodsStock.dao.IOrderGoodsCrudDao;
import com.lovo.sscafter.goodsStock.entity.OrderEntity;
import com.lovo.sscafter.goodsStock.entity.OrderGoodsEntity;
import com.lovo.sscafter.goodsStock.service.IOrderServicr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderServicr")
public class IOrderServiceImpl implements IOrderServicr {
    @Autowired
    private IOrderDao orderDao;
    @Override
    public void saveOrder(OrderEntity orderGoodsEntity) {
        orderDao.save(orderGoodsEntity);
    }
}
