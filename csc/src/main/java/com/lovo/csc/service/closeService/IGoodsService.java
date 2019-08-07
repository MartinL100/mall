package com.lovo.csc.service.closeService;

import com.lovo.csc.entity.CenterOrderGoods;
import com.lovo.csc.entity.GoodsEntity;

public interface IGoodsService {
    /**
     * 保存商品信息
     * @param goods
     */
    public void saveGoodsCount(GoodsEntity goods);
}
