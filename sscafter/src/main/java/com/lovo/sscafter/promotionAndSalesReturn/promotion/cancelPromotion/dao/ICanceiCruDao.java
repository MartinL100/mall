package com.lovo.sscafter.promotionAndSalesReturn.promotion.cancelPromotion.dao;

import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 继承crud的退货维护实现
 */
public interface ICanceiCruDao extends CrudRepository<GoodsEntity,String> {



    /**
     * 根据商品id修改促销状态(审核中),和折扣率（改为100）
     * @param goodsId 商品id
     * @param goodsDiscount 商品折扣率
     * @param promotionState 促销状态
     */
    @Modifying
    @Query("update GoodsEntity set promotionState=?2 ,goodsDiscount=?3 where goodsId=?1")
    public void updatPromotion(String goodsId, String promotionState, int goodsDiscount);

    /**
     * 根据id修改商品促销状态（改为未促销）
     * @param goodsId 商品id
     * @param promotionState 促销状态
     */
    @Modifying
    @Query("update GoodsEntity set promotionState=?2 where goodsId=?1")
    public void updateGoodspromotionState(String goodsId, String promotionState);


}
