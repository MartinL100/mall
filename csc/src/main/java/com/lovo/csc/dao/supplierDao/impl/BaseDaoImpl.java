package com.lovo.csc.dao.supplierDao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.lovo.csc.dao.supplierDao.IBaseDao;
import com.lovo.csc.entity.supplierEntity.IndentEntity;
import com.lovo.csc.entity.SupplierEntity;
import com.lovo.csc.entity.supplierEntity.SupplyEntity;
import com.lovo.csc.util.Verify;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<SupplierEntity> findAllSupplier(int pageNumber, int pageSize,String supplierName,String supplierType,String supplierLevel, String supplierTag) {
        String hql="from SupplierEntity where 1=1 ";
        if(Verify.verifyString(supplierName)){
            hql+=" and supplierName like '%"+supplierName+"%'";
        }
        if(Verify.verifyString(supplierType)){
            hql+=" and supplierType='"+supplierType+"'";
        }
        if(Verify.verifyString(supplierLevel)&&!"不限".equals(supplierLevel)){
            hql+=" and supplierLevel='"+supplierLevel+"'";
        }
        if(Verify.verifyString(supplierTag)&&!"不限".equals(supplierTag)){
            hql+=" and supplierTag='"+supplierTag+"'";
        }
        Query query=getEntityManager().createQuery(hql);
        return query.setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
    }
    @Override
    public long countSupplier(String supplierName,String supplierType,String supplierLevel,String supplierTag){
        String hql="from SupplierEntity where 1=1 ";
        if(Verify.verifyString(supplierName)){
            hql+=" and supplierName like '%"+supplierName+"%'";
        }
        if(Verify.verifyString(supplierType)){
            hql+=" and supplierType='"+supplierType+"'";
        }
        if(!"不限".equals(supplierLevel)){
            hql+=" and supplierLevel='"+supplierLevel+"'";
        }
        if(!"不限".equals(supplierTag)){
            hql+=" and supplierTag='"+supplierTag+"'";
        }
        Query query=getEntityManager().createQuery(hql);
        return query.getResultList().size();
    }
    @Override
    public List<IndentEntity> findAllIndent(int pageNumber, int pageSize, String indentId, String startDate, String endDate, String indentStatus) {
        String hql="from IndentEntity where 1=1 ";
        if(Verify.verifyString(indentId)){
            hql+=" and indentId='"+indentId+"'";
        }
        if(Verify.verifyString(startDate)){
            hql+=" and indentDate>='"+startDate+"'";
        }
        if(Verify.verifyString(endDate)){
            hql+=" and indentDate<='"+endDate+"'";
        }
        if(Verify.verifyString(indentStatus)&&!"不限".equals(indentStatus)){
            hql+=" and indentStatus='"+indentStatus+"'";
        }
        Query query=getEntityManager().createQuery(hql);
        return query.setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
    }
        @Override
    public long countIndent(String indentId,String startDate,String endDate, String indentStatus){
            String hql="from IndentEntity where 1=1 ";
            if(Verify.verifyString(indentId)){
                hql+=" and indentId='"+indentId+"'";
            }
            if(Verify.verifyString(startDate)){
                hql+=" and indentDate>='"+startDate+"'";
            }
            if(Verify.verifyString(endDate)){
                hql+=" and indentDate<='"+endDate+"'";
            }
            if(Verify.verifyString(indentStatus)&&!"不限".equals(indentStatus)){
                hql+=" and indentStatus='"+indentStatus+"'";
            }
            Query query=getEntityManager().createQuery(hql);
        return query.getResultList().size();
    }

    @Override
    public List<SupplyEntity> findAllSupply(int pageNumber, int pageSize, String goodsName, String indentStatus) {
        String hql="from SupplyEntity where 1=1 ";
        if(Verify.verifyString(goodsName)){
            hql+=" and goodsName like '%"+goodsName+"%'";
        }
        if(Verify.verifyString(indentStatus)&&!"不限".equals(indentStatus)){
            hql+=" and indentStatus='"+indentStatus+"'";
        }
        Query query=getEntityManager().createQuery(hql);
        return query.setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
    }

    @Override
    public long countSupply(String goodsName, String indentStatus) {
        String hql="from SupplyEntity where 1=1 ";
        if(Verify.verifyString(goodsName)){
            hql+=" and goodsName like '%"+goodsName+"%'";
        }
        if(Verify.verifyString(indentStatus)&&!"不限".equals(indentStatus)){
            hql+=" and indentStatus='"+indentStatus+"'";
        }
        Query query=getEntityManager().createQuery(hql);
        return query.getResultList().size();
    }
}
