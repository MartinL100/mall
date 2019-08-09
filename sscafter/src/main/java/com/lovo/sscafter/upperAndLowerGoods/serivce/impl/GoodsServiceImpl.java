package com.lovo.sscafter.upperAndLowerGoods.serivce.impl;

import com.lovo.sscafter.upperAndLowerGoods.dao.IGoodsDao;
import com.lovo.sscafter.upperAndLowerGoods.dao.IGoodsQueryDao;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import com.lovo.sscafter.upperAndLowerGoods.serivce.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    public void updateStateUpper(String  goodsId) {
        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
        String shelfTime= date.format(new Date());
        goodsDao.updateStateUpper(goodsId,"上架", shelfTime);

    }


    public void updateStateLower(String goodsId) {
        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
        String lowerfTime= date.format(new Date());
        goodsDao.updateStateLower(goodsId,"下架",lowerfTime);
    }


    public void updateStateBooking(String goodsId) {
        goodsDao.updateStateBooking(goodsId,"预售");
    }

    @Override
    public void updateStateBooking1(String goodsId) {
        goodsDao.updateStateBooking1(goodsId,"未预售");
    }

    @Override
    public void updateGoodsLowerTime(String goodsId, String lowerTime) {
        goodsDao.updateGoodsLowerTime(goodsId,lowerTime);
    }

    @Override
    public List<GoodsEntity> findAllGoods() {
        List<GoodsEntity> list= (List<GoodsEntity>) goodsDao.findAll();
        return list ;
    }
}
