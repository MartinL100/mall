package com.lovo.csc.dao.supplierDao;

import com.lovo.csc.entity.SupplyEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISupplyDao extends CrudRepository<SupplyEntity,String> {
    /**
     * 保存修改
     * @param supply 供货订单中间表
     */
    public SupplyEntity save(SupplyEntity supply);
    /**
     *根据供货订单中间ID供货订单中间表
     * @param supplyId
     * @return
     */
    public SupplyEntity findBySupplyId(String supplyId);
    /**
     * 查询总行数
     * @param indentStatus  供货订单中间表状态（待投标、已投标、待采购、已采购）
     * @return
     */
    @Query("select count(supplyId) from  SupplyEntity where indentStatus=?1")
    public long countAll(String indentStatus);
}
