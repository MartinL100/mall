package com.lovo.csc.service.closeService.impl;

import com.lovo.csc.dao.closeDao.ICenterOrderDao;
import com.lovo.csc.entity.CenterOrderGoods;
import com.lovo.csc.service.closeService.ICenterOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="centerOrderService")
public class CenterOrderServiceImpl implements ICenterOrderService {
    @Autowired
    private ICenterOrderDao centerOrderDao;
    @Override
    public double countMoney(String userName, Double allPrice) {
        return 0;
    }

    @Override
    public void saveGoodsCount(CenterOrderGoods centerOrder) {
        centerOrderDao.save(centerOrder);
    }

    @Override
    public List<CenterOrderGoods> findbyOrderId(String orderId) {
        return centerOrderDao.findbyOrderId(orderId);
    }
}
