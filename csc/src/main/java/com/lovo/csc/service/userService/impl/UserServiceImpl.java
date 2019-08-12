package com.lovo.csc.service.userService.impl;

import com.lovo.csc.dao.userDao.IUserDao;
import com.lovo.csc.entity.AuditEntity;
import com.lovo.csc.service.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public AuditEntity findAuditEntityByAuditNameAndAuditPwd(String auditName, String auditPwd) {

        return userDao.findAuditEntityByAuditNameAndAuditPwd(auditName, auditPwd);
    }

    @Override
    public AuditEntity addAudit(AuditEntity audit) {

        return userDao.save(audit);
    }
}
