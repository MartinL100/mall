package com.lovo.csc.service.supplierService;

import com.lovo.csc.entity.supplierEntity.IndentEntity;

import java.util.List;

public interface IIndentService {
    /**
     * 添加修改供货订单
     * @param indent 供货订单
     */
    public void save(IndentEntity indent);
    /**
     * 根据供货订单ID查询订单
     * @param indentId 供货订单
     * @return
     */
    public IndentEntity findByIndentId(String indentId);
    /**
     * 根据分页查询供应订单信息
     * @param indentStatus 供货订单状态（待投标/已投标/待采购/采购）
     * @return
     */
    public List<IndentEntity> findIndent(int page, int rows, String indentId, String startDate, String endDate, String indentStatus);
    /**
     * 查询总行数
     * @param indentStatus 供货订单状态（待投标/已投标/待采购/采购）
     * @return
     */
    public long countAll(String indentId,String startDate,String endDate, String indentStatus);
}
