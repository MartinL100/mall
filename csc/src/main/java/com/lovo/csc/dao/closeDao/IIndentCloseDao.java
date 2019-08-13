package com.lovo.csc.dao.closeDao;

import com.lovo.csc.entity.supplierEntity.IndentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 供货结算订单保存、查询总行数
 */
public interface IIndentCloseDao extends CrudRepository<IndentEntity,String> {

    /**
     * 查询总行数
     * @param indentStatus 供货订单状态
     * @return
     */
    @Query("select count(indentId) from  IndentEntity where indentStatus=?1")
    public long countAll(String indentStatus);

    public IndentEntity findByIndentId(String indentId);

}
