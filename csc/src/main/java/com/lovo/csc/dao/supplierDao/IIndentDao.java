package com.lovo.csc.dao.supplierDao;

import com.lovo.csc.entity.supplierEntity.IndentEntity;
import org.springframework.data.repository.CrudRepository;

public interface IIndentDao extends CrudRepository<IndentEntity,String> {
    /**
     * 添加修改供货订单
     * @param indent 供货订单
     */
    public IndentEntity save(IndentEntity indent);
    /**
     * 根据供货订单ID查询订单
     * @param indentId 供货订单
     * @return
     */
    public IndentEntity findByIndentId(String indentId);
}
