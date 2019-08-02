package com.lovo.sscafter.orderManagement.service.impl;

import com.lovo.sscafter.orderManagement.dao.IGoodsManagementDao;
import com.lovo.sscafter.orderManagement.entity.OrderForGoodsEntity;
import com.lovo.sscafter.orderManagement.service.IGoodsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsManagmentServiceImpl implements IGoodsManagementService {
    @Autowired
    IGoodsManagementDao goodsManagementDao;
    @Override
    public List<OrderForGoodsEntity> findGoods(String orderNum, int currentPage, int rows) {
        Pageable page = new PageRequest(currentPage-1,rows);
        return goodsManagementDao.findGoodsAndOrder(orderNum,page);
    }
}
