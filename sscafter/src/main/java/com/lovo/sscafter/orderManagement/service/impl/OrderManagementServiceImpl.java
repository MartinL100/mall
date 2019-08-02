package com.lovo.sscafter.orderManagement.service.impl;

import com.lovo.sscafter.orderManagement.dao.IOrderManagementDao;
import com.lovo.sscafter.orderManagement.dao.IOrderTrendsDao;
import com.lovo.sscafter.orderManagement.entity.OrderManagementEntity;
import com.lovo.sscafter.orderManagement.service.IOrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderManagementServiceImpl implements IOrderManagementService {
    @Autowired
    IOrderManagementDao orderManagementDao;
    @Autowired
    IOrderTrendsDao orderTrendsDao;
    public boolean updateOrderDelType(String orderNum) {
        int temp = orderManagementDao.updateOrderDelType(orderNum);
        if(temp == 0){
            return false;
        }
        return true;
    }

    @Override
    public List<OrderManagementEntity> findTrendsOrderInfo(String orderDate, int orderType, int currentPage, int rows, String userName) {
        return orderTrendsDao.findTrendsOrderInfo(orderDate, orderType, currentPage, rows, userName);
    }
}
