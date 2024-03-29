package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.dao;


import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnGoodsEntity2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 商品dao
 */
public interface IReturnGoodsDao extends CrudRepository<ReturnGoodsEntity2,String> {

    /**
     * 根据退货单编号查询全部退货商品信息
     * @param returnOrderId 退货订单编号
     * @return  商品集合
     */
    @Query(value = "SELECT g.* FROM sys_returngoods g LEFT JOIN sys_returnorder r ON \n" +
            "r.oder_id=g.return_oder_id  WHERE\n" +
            " g.return_oder_id=(SELECT oder_num FROM sys_returnorder WHERE oder_id=?1)",nativeQuery = true)
    public List<ReturnGoodsEntity2> findByReturnOderId(String returnOrderId);


    /**
     * 根据商品
     * @param goodsId
     * @param returnOredrState
     */
    @Modifying
    @Query("update ReturnGoodsEntity set returnOredrState=?2 where goodsId=?1")
    public void updat(String goodsId, String returnOredrState);

    /**
     * 根据退货订单号修改商品退货状态
     * @param orderId 退货订单编号
     * @param goodsState 商品退货状态
     */
    @Modifying
    @Query("update ReturnGoodsEntity set returnOredrState=?2 where returnOderId=?1 ")
    public void updatGoodsState(String orderId, int goodsState);
}
