package com.lovo.sscafter.goodsStock.service.impl;

import com.lovo.sscafter.goodsStock.dao.IGoodsStockCurdDao;
import com.lovo.sscafter.goodsStock.dao.IGoodsStockDao;
import com.lovo.sscafter.goodsStock.entity.GoodsStockEntity;
import com.lovo.sscafter.goodsStock.service.IGoodsStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("goodsStockService")

public class GoodsStockServiceImpl implements IGoodsStockService {
    @Autowired
    private IGoodsStockDao goodsStockDao;
    @Autowired
    private IGoodsStockCurdDao goodsStockCurdDao;

    @Override
    public List<GoodsStockEntity> findAllGoodsStock(String goodsName, String goodsType, int currentPage, int rows) {


        return goodsStockDao.findAllGoodsStock(goodsName,goodsType,currentPage,rows);
    }

    @Override
    public long findAllGoodsStockCount(String goodsName, String goodsType) {
        return goodsStockDao.findAllGoodsStockCount(goodsName,goodsType);
    }

    @Override

    public boolean updateGoodsNum(String tag,long goodsNum,String goodsId) {
      if("1".equals(tag)){
         goodsStockCurdDao.updateGoodsNum(-goodsNum,goodsId)  ;
      }else{
          goodsStockCurdDao.updateGoodsNum(goodsNum,goodsId)  ;
      }
   return true;
    }

    @Override
    public long findGoodsNumId(String Id) {

        return goodsStockCurdDao.findGoodsNumId(Id);
    }

    @Override
    public void saveGoods(GoodsStockEntity goods) {
        goodsStockCurdDao.save(goods);
    }

    @Override
    public void updateGoodsMinNum(long goodsMinNum, String goodsId) {
        goodsStockCurdDao.updateGoodsMinNum(goodsMinNum,goodsId);

    }

    @Override
    public void upDateGoodsTag(String tag, String goodsId) {

        goodsStockCurdDao.upDateGoodsTag(tag,goodsId);
    }

    @Override
    public String findSupplyIdByGoodsId(String goodsId) {

        return goodsStockDao.findSupplyIdByGoodsId(goodsId);
    }

    @Override
    public GoodsStockEntity findByNameTypeAnAndNorms(String name, String type, String norms) {


        return goodsStockCurdDao.findByNameTypeAnAndNorms(name,type,norms);
    }

    @Override
    public GoodsStockEntity findGoodsStockEntityByGoodsId(String goodsId) {


        return goodsStockCurdDao.findGoodsStockEntityByGoodsId(goodsId);
    }

    @Override
    public List<GoodsStockEntity> findAllGoodsStockCloud(String goodsName, String goodsType, int currentPage, int rows) {
        return goodsStockDao.findAllGoodsStockCloud(goodsName,goodsType,currentPage,rows);
    }

    @Override
    public long findAllGoodsStockCountCloud(String goodsName, String goodsType) {
        return goodsStockDao.findAllGoodsStockCountCloud(goodsName,goodsType);
    }

    @Override
    public void updateGoodsTag1ById(String id) {
        goodsStockCurdDao.updateGoodsTag1ById(id);
    }
}
