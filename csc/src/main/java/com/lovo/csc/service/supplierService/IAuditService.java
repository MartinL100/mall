package com.lovo.csc.service.supplierService;

import com.lovo.csc.entity.supplierEntity.AuditEntity;

public interface IAuditService {
    /**
     * 审核系统登录
     * @param auditName 审核系统账号
     * @param auditPwd 审核系统密码
     * @return
     */
    public AuditEntity findByAuditNameAndAudintPwd(String auditName,String auditPwd);
    /**
     * 根据审核系统账号ID查询账号
     * @param auditId 审核系统账号ID
     * @return
     */
    public AuditEntity findByAuditId(String auditId);
}
