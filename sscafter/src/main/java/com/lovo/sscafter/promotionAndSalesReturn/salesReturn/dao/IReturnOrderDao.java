package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.dao;

import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnGoodsEntity;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * 退货订单dao
 */

public interface IReturnOrderDao extends CrudRepository<ReturnOrderEntity,String> {
//
//    /**
//     * 根据退货单编号查询全部退货商品信息
//     * @param returnOrderId 退货订单编号
//     * @return  商品集合
//     */
//    public List<ReturnGoodsEntity> findGoodsByReturnOderNum(String returnOrderId);


//    @Query(" from ReturnOrderEntity r left join r.listGoods where ")
//    public ReturnGoodsEntity findByReturnOderId(String id);
}
