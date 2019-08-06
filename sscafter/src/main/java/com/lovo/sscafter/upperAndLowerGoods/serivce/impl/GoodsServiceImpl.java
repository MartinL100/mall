package com.lovo.sscafter.upperAndLowerGoods.serivce.impl;

import com.lovo.sscafter.upperAndLowerGoods.dao.IGoodsDao;
import com.lovo.sscafter.upperAndLowerGoods.dao.IGoodsQueryDao;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import com.lovo.sscafter.upperAndLowerGoods.serivce.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "goodsService")
public class  GoodsServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;
    @Autowired
    private IGoodsQueryDao goodsQueryDao;


    @Override
    public List<GoodsEntity> getGoodsList(int page,int rows,String goodsState, String goodsType, String goodsBooking, String goodsName) {
        return goodsQueryDao.getGoodsList(page,rows,goodsState,goodsType,goodsBooking,goodsName);
    }

    public void saveGoods(GoodsEntity goods) {
        goodsDao.save(goods);
    }

    @Override
    public long getGoodsCount(String goodsState, String goodsType, String goodsBooking, String goodsName) {
        return goodsQueryDao.getGoodsCount(goodsState,goodsType,goodsBooking,goodsName);
    }
}
