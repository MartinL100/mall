package com.lovo.csc.dao.clientdao.impl;


import com.lovo.csc.dao.clientdao.IUserAuditNormalDao;
import com.lovo.csc.entity.SysFrozenOrUnfrozenAccountsEntity;
import com.lovo.csc.entity.SysUserAuditInformationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository(value="userAuditNormalDao")
public class UserAuditNormalDaoImpl implements IUserAuditNormalDao {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }

    //动态查询页面显示用户注册数据
    @Override
    public List<SysUserAuditInformationEntity> DynamicQueryAuditInformation
    (String userState, String startTime, String endTime, Pageable page) {

        return getEntityManager(). createQuery(GetHql1(userState, startTime, endTime)).
                setFirstResult(page.getPageNumber()).setMaxResults(page.getPageSize())
                .getResultList();
    }

    //动态查询页面显示用户注册数据总数
    @Override
    public long DynamicQueryAuditInformationCount(String userState, String startTime, String endTime) {

        return getEntityManager().createQuery(GetHql1(userState, startTime, endTime))
                .getResultList().size();
    }


    //动态查询用户冻结与解冻信息
    @Override
    public List<SysFrozenOrUnfrozenAccountsEntity> DynamicQuerySysFrozenOrUnfrozenAccountsEntity(String auditState,String auditType, String startTime, String endTime, Pageable page) {
        return getEntityManager(). createQuery(GetHql2(auditState,auditType,startTime, endTime)).
                setFirstResult(page.getPageNumber()).setMaxResults(page.getPageSize())
                .getResultList();
    }

    //动态查询用户冻结与解冻信息数据总数
    @Override
    public long DynamicQuerySysFrozenOrUnfrozenAccountsEntityCount(String auditState,String auditType, String startTime, String endTime) {
        return getEntityManager(). createQuery(GetHql2(auditState,auditType,startTime,endTime)).getResultList().size();
    }


    //得到查询注册信息hql
    public String GetHql1(String userState, String startTime, String endTime){
        String hql=" from SysUserAuditInformationEntity where 1=1 ";
        if (null!=userState&&!"".equals(userState)){
            hql+=" and userState = '" +  userState+"'";
        }
        if (null!=startTime&&!"".equals(startTime)){
            hql+=" and  auditTime >= '" + startTime+"'";
        }
        if (null!=endTime&&!"".equals(endTime)){
            hql+=" and auditTime <= '" + endTime+"'";
        }
        return  hql;
    }


    //得到查询冻结，解冻信息hql
    public String GetHql2(String auditState,String auditType, String startTime, String endTime){
        String hql=" from SysFrozenOrUnfrozenAccountsEntity where 1=1 ";
        if (null!=auditState&&!"".equals(auditState)){
            hql+=" and auditState = '" +  auditState+"'";
        }
        if (null!=auditType&&!"".equals(auditType)){
            hql+=" and auditType = '" +auditType+"'";
        }
        if (null!=startTime&&!"".equals(startTime)){
            hql+=" and auditTime >= '" + startTime+"'";
        }
        if (null!=endTime&&!"".equals(endTime)){
            hql+=" and auditTime <= '" + endTime+"'";
        }
        return hql;
    }






}
