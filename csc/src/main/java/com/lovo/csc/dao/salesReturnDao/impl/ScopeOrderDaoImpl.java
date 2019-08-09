package com.lovo.csc.dao.salesReturnDao.impl;

import com.lovo.csc.dao.salesReturnDao.IOrderDao;
import com.lovo.csc.entity.salesReturnEntity.OrderEntity;
import com.lovo.csc.entity.salesReturnEntity.ScopeOrderEntity;
import com.lovo.csc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@Repository(value="orderDao")
public class ScopeOrderDaoImpl implements IOrderDao {
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {

        return entityManagerFactory.getObject().createEntityManager();
    }
    @Override
    public List<ScopeOrderEntity> findByScopeOrder(String orderState, String orderId, String supplierName, int pageNumber, int pageSize) {
        String hql = "   from  ScopeOrderEntity  o where  1=1  ";
        if (!StringUtil.blString(orderState)){
            hql+=" and o.orderState='"+orderState+"' ";
        }
        if (!StringUtil.blString(orderId)){
            hql+=" and o.orderId='"+orderId+"' ";
        }
        if (!StringUtil.blString(supplierName)){
            hql+=" and o.supplierName='"+supplierName+"' ";
        }


        Query query=getEntityManager().createQuery(hql);

        List<ScopeOrderEntity> list=query.setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
        return list;
    }

    @Override
    public long getPageListScopeOrderCount(String orderState, String orderId, String supplierName) {
        String hql = "   from  ScopeOrderEntity o where  1=1  ";
        if (!StringUtil.blString(orderState)){
            hql+=" and o.orderState='"+orderState+"' ";
        }
        if (!StringUtil.blString(orderId)){
            hql+=" and o.orderId='"+orderId+"' ";
        }
        if (!StringUtil.blString(supplierName)){
            hql+=" and o.supplierName='"+supplierName+"' ";
        }
        Query query=getEntityManager().createQuery(hql);
        long a=query.getResultList().size();
        return a;
    }

    @Override
    public List<OrderEntity> findByOrder(String Id, int pageNumber, int pageSize) {
        String hql = "   from  OrderEntity where  1=1  ";
        if (!StringUtil.blString(Id)){
            hql+=" and Id='"+Id+"' ";
        }
        Query query=getEntityManager().createQuery(hql);

        List<OrderEntity> list=query.setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
        return list;
    }

    @Override
    public long getPageListOrderCount(String Id) {
        String hql = "   from  OrderEntity where  1=1  ";
        if (!StringUtil.blString(Id)){
            hql+=" and Id='"+Id+"' ";
        }
        Query query=getEntityManager().createQuery(hql);
        long b=query.getResultList().size();
        return b;
    }
}
