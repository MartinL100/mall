package com.lovo.sscafter.goodsStock.dao;

import com.lovo.sscafter.goodsStock.entity.GoodsTypeEntity;
import org.springframework.data.repository.CrudRepository;

public interface IGoodsTypeDao extends CrudRepository<GoodsTypeEntity,String> {

}
