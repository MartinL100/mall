package com.lovo.sscafter.goodsStock.dao;

import com.lovo.sscafter.goodsStock.entity.IndentEntity;
import org.springframework.data.repository.CrudRepository;

public interface IIndentCrudDao extends CrudRepository<IndentEntity,String> {
}
