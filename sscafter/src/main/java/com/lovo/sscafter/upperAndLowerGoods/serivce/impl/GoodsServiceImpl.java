package com.lovo.sscafter.upperAndLowerGoods.serivce.impl;

import com.lovo.sscafter.upperAndLowerGoods.dao.IGoodsDao;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import com.lovo.sscafter.upperAndLowerGoods.serivce.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "goodsService")
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private IGoodsDao goodsDao;

    public void saveGoods(GoodsEntity goods) {
        goodsDao.save(goods);
    }
}
