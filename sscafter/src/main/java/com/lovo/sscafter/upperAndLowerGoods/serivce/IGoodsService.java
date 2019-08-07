package com.lovo.sscafter.upperAndLowerGoods.serivce;

import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;

import java.util.List;


public interface  IGoodsService {

    // 动态查询商品信息
    public List<GoodsEntity> getGoodsList(int page,int rows,String goodsState, String goodsType, String goodsBooking, String goodsName);

    /**
     * 保存商品
     * @param goods
     */
    public void saveGoods(GoodsEntity goods);

    /**
     * 查询总行数
     * @param goodsState
     * @param goodsType
     * @param goodsBooking
     * @param goodsName
     * @return
     */
    public long  getGoodsCount( String goodsState, String goodsType,
                                String goodsBooking,String goodsName);

    /**
     * 上架
     */
    public void updateStateUpper(String goodsId);

    /**
     * 下架
     * @param goodsId
     */
    public void updateStateLower(String goodsId);

    /**
     * 预售
     * @param goodsId
     */
    public void updateStateBooking(String goodsId);

    /**
     * 未预售
     * @param goodsId
     */
    public void updateStateBooking1(String goodsId);

}
