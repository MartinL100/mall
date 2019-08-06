package com.lovo.sscafter.upperAndLowerGoods.dao;

import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import org.springframework.data.repository.CrudRepository;


public interface IGoodsDao extends CrudRepository<GoodsEntity,String> {

}
