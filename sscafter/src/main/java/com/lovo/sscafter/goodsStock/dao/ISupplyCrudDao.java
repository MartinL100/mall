package com.lovo.sscafter.goodsStock.dao;

import com.lovo.sscafter.goodsStock.entity.SupplyEntity;
import org.springframework.data.repository.CrudRepository;

public interface ISupplyCrudDao extends CrudRepository<SupplyEntity,String> {
}
