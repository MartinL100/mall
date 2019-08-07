package com.lovo.sscafter.goodsStock.service.impl;

import com.lovo.sscafter.goodsStock.dao.IGoodsTypeDao;
import com.lovo.sscafter.goodsStock.entity.GoodsTypeEntity;
import com.lovo.sscafter.goodsStock.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements IGoodsTypeService {
    @Autowired
    private IGoodsTypeDao goodsTypeDao;
    @Override
    public List<GoodsTypeEntity> findAll() {


        return(List<GoodsTypeEntity>) goodsTypeDao.findAll();
    }
}
