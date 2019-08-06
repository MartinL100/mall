package com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.service.imp;

import com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.dao.IPromotionGoodsDao;
import com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.service.IPromotionGoodsService;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 促销service实现
 */

@Service
public class PromotionGoodsServiceImp implements IPromotionGoodsService {

    @Autowired
    private IPromotionGoodsDao dao;

    /**
     * 动态查询所有已上架的商品并分页
     * @param goodsName 商品名
     * @param goodsType 商品类型
     * @param pageNumber 当前页
     * @param pageLine 每页显示的行数
     * @return 商品集合
     */
    @Override
    public List<GoodsEntity> findBygoodsNameAndgoodsState(String goodsName, String goodsType, int pageNumber, int pageLine) {
        return dao.findBygoodsNameAndgoodsState(goodsName,goodsType,pageNumber,pageLine);
    }


    /**
     * 动态查询总行数
     * @param goodsName 商品名
     * @param goodsType 商品类型
     * @return 总行数
     */
    @Override
    public long findCount(String goodsName, String goodsType) {
        return dao.findCount(goodsName,goodsType);
    }


    /**
     * 根据id查询商品集合
     * @param listId id集合
     * @return 商品集合
     */
    @Override
    public List<GoodsEntity> findByGoodsId(List<String> listId) {

        return dao.findByGoodsId(listId);
    }
}
