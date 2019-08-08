package com.lovo.csc.service.closeService.impl;

import com.lovo.csc.dao.closeDao.IGoodsDao;
import com.lovo.csc.entity.GoodsEntity;
import com.lovo.csc.service.closeService.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="goodsService")
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private IGoodsDao goodsDao;
    @Override
    public void saveGoodsCount(GoodsEntity goods) {
        goodsDao.save(goods);
    }
}
