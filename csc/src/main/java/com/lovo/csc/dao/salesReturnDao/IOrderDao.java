package com.lovo.csc.dao.salesReturnDao;

import com.lovo.csc.entity.salesReturnEntity.OrderEntity;
import com.lovo.csc.entity.salesReturnEntity.ScopeOrderEntity;

import java.util.List;

public interface IOrderDao {
    /**
     * 根据订单状态查询退货订单信息
     * @param orderState 订单状态
     * @param orderId 订单编号号
     * @param supplierName 供应商名称
     * @return
     */
    public List<ScopeOrderEntity> findByScopeOrder(String orderState, String orderId, String supplierName,  int pageNumber, int pageSize);

    /**
     * 查询总行数
     * @param orderState
     * @param orderId
     * @param supplierName
     * @return
     */
    public long getPageListScopeOrderCount(String orderState, String orderId, String supplierName);

    /**
     * 根据id查询商品信息
     * @param Id
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<OrderEntity> findByOrder(String Id,int pageNumber, int pageSize);

    /**
     * 查询总行数
     * @param Id
     * @return
     */
    public long getPageListOrderCount(String Id);
}
