package com.lovo.sscafter.orderManagement.dao;

import com.lovo.sscafter.orderManagement.entity.OrderManagementEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IOrderManagementDao extends CrudRepository<OrderManagementEntity,String> {
    @Query("update OrderManagementEntity set orderDelType = 1 " +
            "where orderNum = ?1")
    @Modifying
    public int updateOrderDelType(String orderNum);
}
