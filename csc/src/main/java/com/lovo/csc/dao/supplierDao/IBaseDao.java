package com.lovo.csc.dao.supplierDao;

import com.lovo.csc.entity.IndentEntity;
import com.lovo.csc.entity.SupplierEntity;
import com.lovo.csc.entity.SupplyEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBaseDao {
    /**
     * 根据审核未审核分页查询供应商信息
     * @param supplierTag  供应商状态（未审核/已审核）
     * @return
     */
    public List<SupplierEntity> findAllSupplier(int pageNumber, int pageSize, String supplierTag);
    /**
     * 根据分页查询供应订单信息
     * @param indentStatus 供货订单状态（待投标/已投标/待采购/采购）
     * @return
     */
    //    @Modifying
//     @Query("from IndentEntity where supplierTag=?1")
    public List<IndentEntity> findAllIndent(int pageNumber, int pageSize, String indentStatus);
    /**
     * 根据供货订单中间表状态分页查询供货订单中间表信息
     * @param indentStatus  供货订单中间表状态（待投标、已投标、待采购、已采购）
     * @return
     */
    public List<SupplyEntity> findAllSupply(int pageNumber, int pageSize, String indentStatus);
}
