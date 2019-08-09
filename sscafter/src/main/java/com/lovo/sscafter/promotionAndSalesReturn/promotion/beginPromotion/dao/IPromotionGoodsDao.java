package com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.dao;

import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;


import java.util.List;

/**
 * 促销dao
 */
public interface IPromotionGoodsDao{

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
    public long findCount(String goodsName, String goodsType);

    /**
     * 根据id查询商品集合
     * @param listId id集合
     * @return 商品集合
     */
    public List<GoodsEntity> findByGoodsId(List<String> listId);



    public List<GoodsEntity> findList(String goodsState);
}
