package com.lovo.csc.dao.closeDao.impl;

import com.lovo.csc.dao.closeDao.IOrderDao;
import com.lovo.csc.dao.closeDao.IOrderQueryDao;
import com.lovo.csc.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.awt.print.Pageable;
import java.util.List;

@Repository("orderQueryDao")
public class OrderQueryDaoImpl implements IOrderQueryDao {
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }
    @Override
    public List<OrderEntity> findBySome(String hql,int pageNumber, int pageSize  ) {
        return getEntityManager().createQuery(hql).setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
    }

    @Override
    public long count(String hql) {
        return (long) getEntityManager().createQuery(hql).getSingleResult();

    }


}
