package com.lovo.sscafter.goodsStock.dao.impl;

import com.lovo.sscafter.goodsStock.dao.IGoodsTypeDao;
import com.lovo.sscafter.goodsStock.entity.GoodsTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@Component
public class GoodsTypeDaoImpl implements IGoodsTypeDao {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }


    @Override
    public List<GoodsTypeEntity> findGoodsTypeByNmae(String typeName,int page,int rows) {
       String hql="from GoodsTypeEntity where 1=1 ";
    if(null!=typeName&&typeName.length()!=0){
        hql+=" and typeName=:typeName";
    }

        Query query = getEntityManager().createQuery(hql);

        if(null!=typeName&&typeName.length()!=0){
            query.setParameter("typeName",typeName);
        }
        query.setFirstResult((page-1)*rows);
        query.setMaxResults(rows);


        return query.getResultList();
    }

    @Override
    public long findGoodsTypeByNmaeCpunt(String typeName) {
        String hql="select count (*)from GoodsTypeEntity where 1=1 ";
        if(null!=typeName&&typeName.length()!=0){
            hql+=" and typeName=:typeName";
        }
        Query query = getEntityManager().createQuery(hql);
        if(null!=typeName&&typeName.length()!=0){
            query.setParameter("typeName",typeName);
        }

        return (Long) query.getSingleResult();
    }
}
