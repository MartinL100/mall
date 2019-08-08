package com.lovo.sscafter.goodsStock.dao;

import com.lovo.sscafter.goodsStock.entity.GoodsStockEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IGoodsStockDao {

    public List<GoodsStockEntity> findAllGoodsStock(String goodsName,String goodsType,int currentPage,int rows);
//动态查询总行数
   public long findAllGoodsStockCount(String goodsName,String goodsType);

    public String findSupplyIdByGoodsId(String goodsId);
    //动态查询总行数(远程调用)
    public List<GoodsStockEntity> findAllGoodsStockCloud(String goodsName,String goodsType,int currentPage,int rows);
    //动态查询总行数(远程调用)
    public long findAllGoodsStockCountCloud(String goodsName,String goodsType);



}
