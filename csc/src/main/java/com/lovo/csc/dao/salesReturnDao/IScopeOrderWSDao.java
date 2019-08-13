package com.lovo.csc.dao.salesReturnDao;


import com.lovo.csc.entity.salesReturnEntity.OrderEntity;
import com.lovo.csc.entity.salesReturnEntity.ScopeOrderEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IScopeOrderWSDao extends CrudRepository<ScopeOrderEntity,String> {

////    @Modifying
////    @Query("from OrderEntity o where o.scopeOrder.orderId=?1")
//    public List<OrderEntity> findByScopeOrder(String orderId);
//    @Modifying
//  @Query("update ScopeOrderEntity set orderState=?1 where orderId=?2")
//  public int updateScopeOrder(String orderState,String orderId);
}
