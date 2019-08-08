package com.lovo.sscafter.promotionAndSalesReturn.promotion.cancelPromotion.dao;

import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;

import java.util.List;

/**
 * 取消促销dao接口
 */
public interface ICancelPromotionDao {


        /**
         * 动态查询所有正在促销的商品并分页（促销状态写死）
         * @param goodsName 商品名
         * @param goodsType 商品类型
         * @param pageNumber 当前页
         * @param pageLine 每页显示的行数
         * @return 商品集合
         */
        public List<GoodsEntity> findBygoodsNameAndgoodsState(String goodsName, String goodsType, int pageNumber, int pageLine);


        /**
         * 动态查询正在促销的商品总行数
         * @param goodsName 商品名
         * @param goodsType 商品类型
         * @return 总行数
         */
        public long findCount(String goodsName,String goodsType);


        /**
         * 根据id集合查询商品集合
         * @param listId id集合
         * @return 商品集合
         */
        public List<GoodsEntity> findByGoodsId(List<String> listId);


}
