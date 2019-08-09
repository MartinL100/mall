package com.lovo.csc.dao.salesReturnDao;


import com.lovo.csc.entity.salesReturnEntity.OrderEntity;
import com.lovo.csc.entity.salesReturnEntity.OrderGoodsEntity;
import com.lovo.csc.entity.salesReturnEntity.ScopeOrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IScopeOrderDao extends CrudRepository<OrderEntity,String> {

//    @Modifying
//    @Query("from OrderEntity o where o.scopeOrder.orderId=?1")
    public List<OrderEntity> findByScopeOrder(String Id);
}
