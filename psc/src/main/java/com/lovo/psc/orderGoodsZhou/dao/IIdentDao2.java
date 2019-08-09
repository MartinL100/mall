package com.lovo.psc.orderGoodsZhou.dao;

import com.lovo.psc.orderGoodsZhou.dto.SendGoodsDto;
import com.lovo.psc.entity.IndentEntity;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IIdentDao2 {

    /**
     * 通过供应商编号动态查询所有已支付订单集合并分页
     * @param supplierId 供应商id
     * @param indentStatus 订单状态
     * @return 订单集合
     */
    public List<IndentEntity> getYesPayList(String supplierId, String indentStatus, String startDate, String endDate, Pageable pageable);

    /**
     * 根据订单号查询供货商品集合和供应商id
     * @param indentId 订单id
     */
    public List<SendGoodsDto> sendGoodsMQ(String indentId);
}
