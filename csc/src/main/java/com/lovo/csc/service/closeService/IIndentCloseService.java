package com.lovo.csc.service.closeService;

import com.lovo.csc.entity.supplierEntity.IndentEntity;
import com.lovo.csc.vo.IndentSupplyDto;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface IIndentCloseService {
    /**
     * 修改供货订单（保存）
     */
    public void save(IndentEntity indentEntity);

    /**
     * 按条件查找供货订单集合(分页)
     * @param indentStatus 订单状态（已结算、未结算）
     * @param startDate 订单结算开始时间
     * @param endDate 订单结算结束时间
     * @return 订单集合（已结算或未结算）
     */
    public List<IndentEntity> findIndentList(int pageNumber, int pageSize, String startDate, String endDate, String indentStatus);

    /**
     * 计算总行数
     * @param indentStatus 订单状态（已结算、未结算）
     * @param startDate 订单结算开始时间
     * @param endDate 订单结算结束时间
     * @return 总行数
     */
    public long countIndentList(String startDate, String endDate, String indentStatus);

    /**
     * 根据供货订单ID查询该订单的供货商品以及报价
     * @param indentId 供货结算订单ID
     * @return 该订单的供货商品（包含报价）的集合
     */
    public List<IndentSupplyDto>findSupplyListByIndentId(int pageNumber, int pageSize,String indentId);


    public long countIndentSupplyListByIndentId(String indentId);


    public IndentEntity findByIndentId(String indentId);

}
