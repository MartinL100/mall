package com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.service;

import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;


import java.util.List;

/**
 * 促销service接口
 */
public interface IPromotionGoodsService {
    /**
     * 动态查询所有已上架的商品并分页
     * @param goodsName 商品名
     * @param goodsType 商品类型
     * @param pageNumber 当前页
     * @param pageLine 每页显示的行数
     * @return 商品集合
     */
    public List<GoodsEntity> findBygoodsNameAndgoodsState(String goodsName, String goodsType, int pageNumber, int pageLine);


    /**
     * 动态查询总行数
     * @param goodsName 商品名
     * @param goodsType 商品类型
     * @return 总行数
     */
    public long findCount(String goodsName,String goodsType);


    /**
     * 根据id查询商品集合
     * @param listId id集合
     * @return 商品集合
     */
    public List<GoodsEntity> findByGoodsId(List<String> listId);


    /**
     * 根据商品id修改促销状态(审核中，正在促销，未促销,促销审核未通过)，和折扣率
     * @param goodsId 商品id
     * @param goodsDiscount 商品折扣率
     * @param promotionState 促销状态
     */
    public void updatPromotion(String goodsId,String promotionState,int goodsDiscount);


    /**
     * 根据id修改商品促销状态(改为审核中)
     * @param goodsId 商品id
     * @param promotionState 促销状态
     */
    public void updateGoodspromotionState(String goodsId,String promotionState);
}
