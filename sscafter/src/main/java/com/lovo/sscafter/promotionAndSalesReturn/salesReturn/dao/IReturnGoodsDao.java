package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.dao;

import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnGoodsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 商品dao
 */
public interface IReturnGoodsDao extends CrudRepository<ReturnGoodsEntity,String> {

    /**
     * 根据退货单编号查询全部退货商品信息
     * @param returnOrderId 退货订单编号
     * @return  商品集合
     */
    @Query(value = "SELECT g.* FROM sys_returngoods g LEFT JOIN sys_returnorder r ON \n" +
            "r.oder_id=g.return_oder_id  WHERE\n" +
            " g.return_oder_id=(SELECT oder_num FROM sys_returnorder WHERE oder_id=?1)",nativeQuery = true)
    public List<ReturnGoodsEntity> findByReturnOderId(String returnOrderId);
}
