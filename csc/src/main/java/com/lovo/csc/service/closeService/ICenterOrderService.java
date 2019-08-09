package com.lovo.csc.service.closeService;

import com.lovo.csc.entity.CenterOrderGoods;

import java.util.List;

public interface ICenterOrderService {
    /**
     * 保存中间表
     * @param centerOrderGoods
     */
    public void addCenterOrderService(CenterOrderGoods centerOrderGoods);
    /**
     * 根据用户名和总价计算折后价格，返回折后价格，
     * @param userName
     * @param allPrice
     * @return
     */
    public double countMoney(String userName, Double allPrice);

    /**
     * 保存商品订单信息
     * @param goodsCount
     */
    public void saveGoodsCount(CenterOrderGoods goodsCount);
    public List<CenterOrderGoods> findbyOrderId(String orderId);
}
