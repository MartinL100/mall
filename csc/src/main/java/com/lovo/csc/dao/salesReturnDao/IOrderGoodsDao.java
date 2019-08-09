package com.lovo.csc.dao.salesReturnDao;



import com.lovo.csc.entity.salesReturnEntity.GoodsEntity;
import com.lovo.csc.entity.salesReturnEntity.OrderGoodsEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IOrderGoodsDao extends CrudRepository<OrderGoodsEntity,String> {
//    @Query("from GoodsEntity g where g.orderGoods.goodsState=?1")
//    public List<GoodsEntity> findByOrderId(String goodsState,String startTime,String endTime);
//
//    @Query("from OrderGoodsEntity where orderId>?1")
//    public List<OrderGoodsEntity>getPageListOrderGoodsEntity(String id, Pageable pageable);
//
//    @Query("select  count(orderId) from OrderGoodsEntity where orderId>?1 ")
//    public long getPageListOrderGoodsEntityCount(String id);
//    @Modifying
//    @Query("update OrderGoodsEntity set goodsAuditState=?1 where orderId=?2")
//    public int updateOrderGoods(String goodsAuditState,String orderId);
//
//    @Query("from GoodsEntity g where g.orderGoods.orderId=?1")
//    public List<GoodsEntity> findByOrderGoods(String orderId);

}
