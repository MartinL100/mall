package com.lovo.csc.service.userService;

import com.lovo.csc.entity.AuditEntity;

public interface IUserService {
    /**
     * 根据用户名和密码查询用户
     * @param auditName 用户名
     * @param auditPwd 密码
     * @return 用户对象
     */
    public AuditEntity findAuditEntityByAuditNameAndAuditPwd(String auditName,String auditPwd);
}
