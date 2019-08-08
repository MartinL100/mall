package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.service.imp;

import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.dao.IReturnGoodsDao;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnGoodsEntity;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.service.IReturnGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品service实现类
 */
@Service
public class ReturnGoodsServiceImp implements IReturnGoodsService {

    @Autowired
    private IReturnGoodsDao dao;

    @Override
    public void sava(ReturnGoodsEntity goodsEntity) {
        dao.save(goodsEntity);
    }


    /**
     * 根据退货单编号查询全部退货商品信息
     * @param returnOrderId 退货订单编号
     * @return  商品集合
     */
    @Override
    public List<ReturnGoodsEntity> findByReturnOderId(String returnOrderId) {
        return dao.findByReturnOderId(returnOrderId);
    }

}
