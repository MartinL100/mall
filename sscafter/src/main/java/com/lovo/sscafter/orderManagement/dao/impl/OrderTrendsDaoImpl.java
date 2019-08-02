package com.lovo.sscafter.orderManagement.dao.impl;

import com.lovo.sscafter.orderManagement.dao.IOrderTrendsDao;
import com.lovo.sscafter.orderManagement.entity.OrderManagementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@Repository(value = "orderTrendsDao")
public class OrderTrendsDaoImpl implements IOrderTrendsDao {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }

    @Override
    public List<OrderManagementEntity> findTrendsOrderInfo(String orderDate, int orderType, int currentPage, int rows, String userName) {
        String hql = "from OrderManagementEntity o where o.orderDelType = 0 ";
        boolean orderDateFlag =null != orderDate && !"".equals(orderDate) && !"no".equals(orderDate);
        boolean orderTypeFlag = orderType != 3;
        boolean userNameFlag = null != userName && !"".equals(userName);
        if(orderDateFlag){
            hql+="and o.orderDate =:orderDate ";
        }
        if(orderTypeFlag){
            hql+="and o.orderType =:orderType ";
        }
        if(userNameFlag){
            hql+="and o.userName like :userName";
        }
        Query query=getEntityManager().createQuery(hql);
        if(orderDateFlag){
            query.setParameter("orderDate",orderDate);
        }
        if(orderTypeFlag){
            query.setParameter("orderType",orderType+"");
        }
        if(userNameFlag){
            query.setParameter("userName","%"+userName+"%");
        }
        return query.setFirstResult((currentPage-1)*rows).setMaxResults(rows).getResultList();
    }
}
