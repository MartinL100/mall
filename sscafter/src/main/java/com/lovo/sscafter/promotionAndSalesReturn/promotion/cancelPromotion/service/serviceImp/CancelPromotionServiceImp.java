package com.lovo.sscafter.promotionAndSalesReturn.promotion.cancelPromotion.service.serviceImp;

import com.lovo.sscafter.promotionAndSalesReturn.promotion.cancelPromotion.dao.ICanceiCruDao;
import com.lovo.sscafter.promotionAndSalesReturn.promotion.cancelPromotion.dao.ICancelPromotionDao;
import com.lovo.sscafter.promotionAndSalesReturn.promotion.cancelPromotion.service.ICancelPromotionService;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 取消促销service实现类
 */
@Service
public class CancelPromotionServiceImp implements ICancelPromotionService {

    @Autowired
    private ICancelPromotionDao dao;

    @Autowired
    private ICanceiCruDao cruDao;

    /**
     * 动态查询所有正在促销的商品并分页（促销状态写死）
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
     * 动态查询正在促销的商品总行数
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



    /**
     * 根据id修改商品促销状态(改为审核中)
     * @param goodsId 商品id
     * @param promotionState 促销状态
     */
    @Override
    public void updateGoodspromotionState(String goodsId, String promotionState) {
        cruDao.updateGoodspromotionState(goodsId, promotionState);
    }



    /**
     * 根据商品id修改促销状态(审核中，正在促销，未促销,促销审核未通过)，和折扣率（改为100）
     * @param goodsId 商品id
     * @param goodsDiscount 商品折扣率
     * @param promotionState 促销状态
     */
    @Override
    public void updatPromotion(String goodsId, String promotionState, int goodsDiscount) {
        cruDao.updatPromotion(goodsId, promotionState, goodsDiscount);
    }

}
