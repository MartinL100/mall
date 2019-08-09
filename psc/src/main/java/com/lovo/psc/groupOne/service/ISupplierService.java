package com.lovo.psc.groupOne.service;

import com.lovo.psc.entity.SupplierEntity;

public interface ISupplierService {
    /**
     * 商户登录
     * @param supplierName 商户名称
     * @param supplierPwd 商户密码
     * @return 商户对象
     */
    public SupplierEntity login(String supplierName, String supplierPwd);

    /**
     * 按商户编号查找商户对象
     * @param supplierId 商户编号
     * @return 商户对象
     */
    public SupplierEntity findBySupplierId(String supplierId);
}
