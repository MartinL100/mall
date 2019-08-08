package com.lovo.sscafter.goodsStock.dao.impl;

import com.lovo.sscafter.goodsStock.dao.IGoodsStockDao;
import com.lovo.sscafter.goodsStock.entity.GoodsStockEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.List;
@Component

public class GoodsStockDaoImpl implements IGoodsStockDao {
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }



    @Override
    public List<GoodsStockEntity> findAllGoodsStock(String goodsName,
         String goodsType,int currentPage,int rows) {
     String   hql="from GoodsStockEntity where 1=1 ";
     if(null!=goodsName&&goodsName.length()!=0){
         hql+=" and goodsName like '%"+goodsName+"%'";

     }
     if(null!=goodsType&&goodsType.length()!=0){
         hql+=" and goodsType=:goodsType";
     }

      Query query = getEntityManager().createQuery(hql);

        if(null!=goodsType&&goodsType.length()!=0){
            query.setParameter("goodsType",goodsType);
        }
       query.setFirstResult((currentPage-1)*rows);
        query.setMaxResults(rows);


        return query.getResultList();
    }

    @Override
    public long findAllGoodsStockCount(String goodsName, String goodsType) {
        String   hql="select count(*) from GoodsStockEntity where 1=1 ";
        if(null!=goodsName&&goodsName.length()!=0){
            hql+=" and goodsName like '%"+goodsName+"%'";
        }
        if(null!=goodsType&&goodsType.length()!=0){
            hql+=" and goodsType=:goodsType";
        }
        Query query = getEntityManager().createQuery(hql);
        if(null!=goodsType&&goodsType.length()!=0){
            query.setParameter("goodsType",goodsType);
        }
        return (long)query.getSingleResult();
    }

    @Override
    public String findSupplyIdByGoodsId(String goodsId) {
     String hql="select og.supplierId from GoodsStockEntity g left join OrderGoodsEntity og on g.goodsId=og.goods.goodsId where g.goodsId=:goodsId";

        Query query = getEntityManager().createQuery(hql);
        query.setParameter("goodsId",goodsId);
        return(String) query.getSingleResult();
    }

    @Override
    public List<GoodsStockEntity> findAllGoodsStockCloud(String goodsName, String goodsType, int currentPage, int rows) {
        String   hql="from GoodsStockEntity where tag1='未添加' ";
        if(null!=goodsName&&goodsName.length()!=0){
            hql+=" and goodsName like '%"+goodsName+"%'";

        }
        if(null!=goodsType&&goodsType.length()!=0){
            hql+=" and goodsType=:goodsType";
        }

        Query query = getEntityManager().createQuery(hql);

        if(null!=goodsType&&goodsType.length()!=0){
            query.setParameter("goodsType",goodsType);
        }
        query.setFirstResult((currentPage-1)*rows);
        query.setMaxResults(rows);


        return query.getResultList();
    }

    @Override
    public long findAllGoodsStockCountCloud(String goodsName, String goodsType) {
        String   hql="select count(*) from GoodsStockEntity where tag1='未添加' ";
        if(null!=goodsName&&goodsName.length()!=0){
            hql+=" and goodsName like '%"+goodsName+"%'";
        }
        if(null!=goodsType&&goodsType.length()!=0){
            hql+=" and goodsType=:goodsType";
        }
        Query query = getEntityManager().createQuery(hql);
        if(null!=goodsType&&goodsType.length()!=0){
            query.setParameter("goodsType",goodsType);
        }
        return (long)query.getSingleResult();
    }

}
