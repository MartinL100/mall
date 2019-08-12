package com.lovo.psc.groupOne.dao1;

import com.lovo.psc.entity.SupplierEntity;
import org.springframework.data.repository.CrudRepository;

public interface ISupplierDao1 extends CrudRepository<SupplierEntity,String> {
    /**
     * 按照商户名和密码查找商户
     * @param supplierName 商户名称
     * @param supplierPwd 商户密码
     * @return 商户对象
     */
    public SupplierEntity findBySupplierNameAndSupplierPwd(String supplierName, String supplierPwd);

    /**
     * 按照商户编号查询商户
     * @param SupplierId 商户编号
     * @return 商户对象
     */
    public SupplierEntity findBySupplierId(String SupplierId);
}
