package com.lovo.csc.dao.closeDao;

import com.lovo.csc.entity.supplierEntity.IndentEntity;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * 供货结算订单分页查询
 */
public interface IIndentCloseDao1 {

    /**
     * 按订单状态（已结算、未结算）、结算开始时间、结算结束时间 分页查询 供货订单集合
     * @param indentStatus 订单状态（已结算、未结算）
     * @param start 结算开始时间
     * @param end 结算结束时间
     * @return
     */
    public List<IndentEntity> findIndentList(int pageNumber, int pageSize, String start, String end, String indentStatus);

    /**
     * 计算总行数
     * @param indentStatus 订单状态（已结算、未结算）
     * @param start 订单结算开始时间
     * @param end 订单结算结束时间
     * @return 总行数
     */
    public long countIndentList(String start, String end, String indentStatus);

}
