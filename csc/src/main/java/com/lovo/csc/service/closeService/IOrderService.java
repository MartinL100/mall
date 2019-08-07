package com.lovo.csc.service.closeService;

import com.lovo.csc.entity.GoodsEntity;
import com.lovo.csc.entity.OrderEntity;
import org.hibernate.criterion.Order;

import java.util.List;

public interface IOrderService {
    /**
     * 保存商品信息
     * @param order
     */
    public void saveOrder(OrderEntity order);

    /**
     * 根据订单id查找订单记录
     * @param orderId
     * @return
     */
    public OrderEntity findbyId(String orderId);

    /**
     * 动态条件查询
     * @param userName
     * @param orderNum
     * @param startTime
     * @param endTime
     * @return
     */
    public List<OrderEntity> findBySome(int pageNumber, int pageSize, String userName,
                                        String orderNum, String startTime, String endTime);

    /**
     * 统计条件查询的总行数
     * @param userName
     * @param orderNum
     * @param startTime
     * @param endTime
     * @return
     */
    public long count(String userName, String orderNum, String startTime, String endTime);
    public List<OrderEntity> findByTag(int pageNumber, int pageSize, int tag);
    public long count(int tag);
}
