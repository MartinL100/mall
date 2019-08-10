package com.lovo.csc.service.salesReturnService;

import com.lovo.csc.entity.salesReturnEntity.OrderEntity;
import com.lovo.csc.entity.salesReturnEntity.ScopeOrderEntity;



import java.util.List;

public interface IScopeOrderService {
    /**
     * 根据订单状态查询退货订单信息
     * @param orderState 订单状态
     * @param orderId 订单编号号
     * @param supplierName 供应商名称
     * @return
     */
    public List<ScopeOrderEntity> findByScopeOrder(String orderState, String orderId, String supplierName,int pageNumber, int pageSize);

    /**
     * 查询总行数
     * @param orderState
     * @param orderId
     * @param supplierName
     * @return
     */
    public long getPageListScopeOrderCount(String orderState, String orderId, String supplierName);

    /**
     * 保存
     * @param scopeOrderEntity
     * @return
     */
    public ScopeOrderEntity sava(ScopeOrderEntity scopeOrderEntity);

    /**
     * 查询
     *
     * @param Id
     * @return
     */
    public List<OrderEntity> findByScopeOrder(String Id);

    /**
     * 修改状态
     * @param scopeOrderEntity
     * @param orderState 订单状态
     * @return
     */
    public String updateScopeOrder(ScopeOrderEntity scopeOrderEntity,String orderState);

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
