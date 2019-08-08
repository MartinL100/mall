package com.lovo.sscafter.goodsStock.dao;

import com.lovo.sscafter.goodsStock.entity.ReturnGoodsEntity;
import org.springframework.data.repository.CrudRepository;

public interface IRuturnGoodsCrudDao extends CrudRepository<ReturnGoodsEntity,String> {
}
