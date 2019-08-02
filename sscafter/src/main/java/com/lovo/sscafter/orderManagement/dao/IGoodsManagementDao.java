package com.lovo.sscafter.orderManagement.dao;

import com.lovo.sscafter.orderManagement.entity.OrderForGoodsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface IGoodsManagementDao extends CrudRepository<OrderForGoodsEntity,String> {
    @Query("from OrderForGoodsEntity g left join g.orderObj o where o.orderNum = ?1")
    public List<OrderForGoodsEntity> findGoodsAndOrder(String orderNum, Pageable page);
}
