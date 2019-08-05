package com.lovo.csc.service.supplierService;

import com.lovo.csc.entity.SupplyEntity;

import java.util.List;

public interface ISupplyService {
    /**
     * 保存修改
     * @param supply 供货订单中间表
     */
    public void save(SupplyEntity supply);
    /**
     *根据供货订单中间ID供货订单中间表
     * @param supplyId
     * @return
     */
    public SupplyEntity findBySupplyId(String supplyId);
    /**
     * 根据供货订单中间表状态分页查询供货订单中间表信息
     * @param pageNumber 当前页
     * @param pageSize 每页显示的行数
     * @param indentStatus  供货订单中间表状态（待投标、已投标、待采购、已采购）
     * @return
     */
    public List<SupplyEntity> findAll(int pageNumber, int pageSize, String indentStatus);
    /**
     * 查询总行数
     * @param indentStatus  供货订单中间表状态（待投标、已投标、待采购、已采购）
     * @return
     */
    public long countAll(String indentStatus);
}
