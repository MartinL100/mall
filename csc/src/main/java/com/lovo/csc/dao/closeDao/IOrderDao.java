package com.lovo.csc.dao.closeDao;

import com.lovo.csc.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IOrderDao extends CrudRepository<OrderEntity,String> {
    @Query("  from OrderEntity where orderNum=?1")
    public OrderEntity findbyiD(String orderId);

}
