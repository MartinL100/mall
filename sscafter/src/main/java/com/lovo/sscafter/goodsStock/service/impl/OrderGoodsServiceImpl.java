package com.lovo.sscafter.goodsStock.service.impl;


import com.lovo.sscafter.goodsStock.dao.IOrderGoodsDao;
import com.lovo.sscafter.goodsStock.dto.OrderGoodsDTO;
import com.lovo.sscafter.goodsStock.entity.OrderGoodsEntity;
import com.lovo.sscafter.goodsStock.service.IOrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("orderGoodsService")
public class OrderGoodsServiceImpl implements IOrderGoodsService {
    @Autowired
    private IOrderGoodsDao orderGoodsDao;

    @Override
    public List<OrderGoodsDTO> findOrderGoods(String goodsName, String goodsType,
                                              String startDate, String endDate, int currentPage, int rows) {

        return orderGoodsDao.findOrderGoods(goodsName,goodsType,startDate,endDate,currentPage,rows);
    }
}
