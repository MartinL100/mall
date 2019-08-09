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
     * @return
     */
    @Query("from  SupplyEntity where indentId.indentId=?1")
    public List<SupplyEntity> findSupply(String indentId);
    public SupplyEntity findBySupplyId(String supplyId);
}
