package com.lovo.csc.dao.supplierDao;

import com.lovo.csc.entity.AuditEntity;
import org.springframework.data.repository.CrudRepository;

public interface IAuditDao extends CrudRepository<AuditEntity,String> {
    /**
     * 审核系统登录
     * @param auditName 审核系统账号
     * @param auditPwd 审核系统密码
     * @return
     */
    public AuditEntity findByAuditNameAndAuditPwd(String auditName, String auditPwd);
    /**
     * 根据审核系统账号ID查询账号
     * @param auditId 审核系统账号ID
     * @return
     */
    public AuditEntity findByAuditId(String auditId);
}
