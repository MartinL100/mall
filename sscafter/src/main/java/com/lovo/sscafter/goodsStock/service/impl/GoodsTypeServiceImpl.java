package com.lovo.sscafter.goodsStock.service.impl;

import com.lovo.sscafter.goodsStock.dao.IGoodsTypeCrudDao;
import com.lovo.sscafter.goodsStock.dao.IGoodsTypeDao;
import com.lovo.sscafter.goodsStock.entity.GoodsTypeEntity;
import com.lovo.sscafter.goodsStock.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements IGoodsTypeService {
    @Autowired
    private IGoodsTypeCrudDao GoodsTypeCrudDao;
    @Autowired
    private IGoodsTypeDao goodsTypeDao;

    @Override
    public List<GoodsTypeEntity> findAll() {


        return(List<GoodsTypeEntity>) GoodsTypeCrudDao.findAll();
    }

    @Override
    public List<GoodsTypeEntity> findGoodsTypeByNmae(String typeName, int page, int rows) {
        return goodsTypeDao.findGoodsTypeByNmae(typeName,page,rows);
    }

    @Override
    public long findGoodsTypeByNmaeCpunt(String typeName) {
        return goodsTypeDao.findGoodsTypeByNmaeCpunt(typeName);
    }

    @Override
    public void saveGoodsType(GoodsTypeEntity goodsTypeEntity) {
        GoodsTypeCrudDao.save(goodsTypeEntity);
    }

    @Override
    public void delGoodstype(String id) {
        GoodsTypeCrudDao.deleteById(id);
    }

    @Override
    public void updateGoodstype(String typeName, String typeId) {
        GoodsTypeCrudDao.updateGoodstype(typeName,typeId);
    }

    @Override
    public GoodsTypeEntity findTypeByKey(String typeKey) {
        return GoodsTypeCrudDao.findTypeByKey(typeKey);
    }


}
