package com.lovo.sscafter.upperAndLowerGoods.serivce;

import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;


public interface IGoodsService {
    /**
     * 保存商品
     * @param goods
     */
    public void saveGoods(GoodsEntity goods);
}
