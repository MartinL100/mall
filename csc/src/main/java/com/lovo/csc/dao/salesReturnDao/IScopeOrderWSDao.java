package com.lovo.csc.dao.salesReturnDao;


import com.lovo.csc.entity.salesReturnEntity.OrderEntity;
import com.lovo.csc.entity.salesReturnEntity.ScopeOrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IScopeOrderWSDao extends CrudRepository<ScopeOrderEntity,String> {

////    @Modifying
////    @Query("from OrderEntity o where o.scopeOrder.orderId=?1")
//    public List<OrderEntity> findByScopeOrder(String orderId);
}
