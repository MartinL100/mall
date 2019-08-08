package com.lovo.sscafter.goodsStock.dao;

import com.lovo.sscafter.goodsStock.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface IOrderDao extends CrudRepository<OrderEntity,String> {
}
