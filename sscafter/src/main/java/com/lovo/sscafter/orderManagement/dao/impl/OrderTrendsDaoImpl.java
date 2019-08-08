package com.lovo.sscafter.orderManagement.dao.impl;

import com.lovo.sscafter.orderManagement.dao.IOrderTrendsDao;
import com.lovo.sscafter.orderManagement.entity.OrderManagementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Query query = utilTrendsHql(hql,orderDate,orderType,userName);
        return query.setFirstResult((currentPage-1)*rows).setMaxResults(rows).getResultList();
    }

    public int findOrderRows(String orderDate,int orderType,String userName){
        String hql = "select count(orderNum) from OrderManagementEntity o where o.orderDelType = 0 ";
        Query query = utilTrendsHql(hql,orderDate,orderType,userName);
        Long num = (Long) query.getSingleResult();
        return num.intValue();
    }

    public Query utilTrendsHql(String hql,String orderDate,int orderType,String userName){
        boolean orderDateFlag =null != orderDate && !"".equals(orderDate) && !"no".equals(orderDate);
        boolean orderTypeFlag = orderType != 3;
        boolean userNameFlag = null != userName && !"".equals(userName) && !"no".equals(userName);
        if(orderDateFlag){
            hql+="and o.orderDate =:orderDate ";
        }
        if(orderTypeFlag){
            hql+="and o.orderType =:orderType ";
        }
        if(userNameFlag){
            hql+="and o.userName like :userName";
        }
        hql+=" order by orderDate";
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
        return query;
    }

    public Map<String,String> findDate(String mouth){
        String sql = "SELECT c.datelist AS mydata , IF(SUM(s.goods_profit)IS NULL ,0,SUM(s.goods_profit)) AS profit FROM calendar c " +
                "LEFT JOIN sys_order_after s ON c.datelist = s.order_date " +
                "WHERE c.datelist LIKE '%"+mouth+"%'GROUP BY c.datelist";

        List rows =  getEntityManager().createNativeQuery(sql).getResultList();
        Map<String,String> map = new HashMap();
        String listMap1 = "";
        String listMap2 = "";
        for (Object obj : rows) {
            Object[] row = (Object[]) obj;
            listMap1+=row[0]+",";
            listMap2+=row[1]+",";
        }
        map.put("listMap",listMap1);
        map.put("listMap2",listMap2);
        return map;
    }
}
