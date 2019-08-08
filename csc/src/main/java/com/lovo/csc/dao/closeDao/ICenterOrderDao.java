package com.lovo.csc.dao.closeDao;

import com.lovo.csc.entity.CenterOrderGoods;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICenterOrderDao extends CrudRepository<CenterOrderGoods,String> {
    @Query(value="from center_order_goods c LEFT JOIN sys_goods g on g.goods_id=c.goods_id " +
            "WHERE c.order_num=?1 ",nativeQuery = true)
    public List<CenterOrderGoods> findbyOrderId(String orderId);
}
