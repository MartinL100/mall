package com.lovo.csc.dao.userDao;

import com.lovo.csc.entity.supplierEntity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao  extends JpaRepository<AuditEntity,String> {

    public AuditEntity findAuditEntityByAuditNameAndAuditPwd(String auditName,String auditPwd);

}
