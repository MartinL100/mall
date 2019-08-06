package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.service.imp;

import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnOrderEntity;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.dao.IReturnOrderDao;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.service.IReturnOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 退货订单service实现类
 */
@Service
public class ReturnOrderServiceImp implements IReturnOrderService {

    @Autowired
    private IReturnOrderDao dao;

    @Override
    public void savaReturnOrder(ReturnOrderEntity orderEntity) {
        dao.save(orderEntity);
    }
}
