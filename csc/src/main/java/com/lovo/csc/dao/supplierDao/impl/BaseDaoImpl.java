package com.lovo.csc.dao.supplierDao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.lovo.csc.dao.supplierDao.IBaseDao;
import com.lovo.csc.entity.IndentEntity;
import com.lovo.csc.entity.SupplierEntity;
import com.lovo.csc.entity.SupplyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

@Repository(value="baseDao")
public class BaseDaoImpl implements IBaseDao{
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }

    @Override
    public List<SupplierEntity> findAllSupplier(int pageNumber, int pageSize, String supplierTag) {
        String hql="from SupplierEntity";
        if(null==supplierTag||"".equals(supplierTag)){
            Query query=getEntityManager().createQuery(hql);
        }
        else{
            hql+=" where supplierTag=?1";
            Query query=getEntityManager().createQuery(hql).setParameter(1,supplierTag);
        }
        Query query=getEntityManager().createQuery(hql);
        return query.setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
    }
    @Override
    public List<IndentEntity> findAllIndent(int pageNumber, int pageSize, String indentStatus) {
        String hql="from IndentEntity";
        if(null==indentStatus||"".equals(indentStatus)){
            Query query=getEntityManager().createQuery(hql);
        }
        else{
            hql+=" where indentStatus=?1";
            Query query=getEntityManager().createQuery(hql).setParameter(1,indentStatus);
        }
        Query query=getEntityManager().createQuery(hql);
        return query.setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
    }

    @Override
    public List<SupplyEntity> findAllSupply(int pageNumber, int pageSize, String indentStatus) {
        String hql="from IndentEntity";
        if(null==indentStatus||"".equals(indentStatus)){
            Query query=getEntityManager().createQuery(hql);
        }
        else{
            hql+=" where indentStatus=?1";
            Query query=getEntityManager().createQuery(hql).setParameter(1,indentStatus);
        }
        Query query=getEntityManager().createQuery(hql);
        return query.setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
    }
}
