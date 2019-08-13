package com.lovo.csc.dao.closeDao.impl;


import com.lovo.csc.dao.closeDao.IIndentCloseDao1;
import com.lovo.csc.entity.supplierEntity.IndentEntity;
import com.lovo.csc.util.Verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository(value = "indentCloseDao1")
public class IndentCloseDaoImpl1 implements IIndentCloseDao1 {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }

    @Override
    public List<IndentEntity> findIndentList(int pageNumber, int pageSize, String start, String end, String indentStatus) {
        String hql=" from IndentEntity where 1=1 ";
        if(Verify.verifyString(start)){
            hql+=" and indentDate>='"+start+"'";
        }
        if(Verify.verifyString(end)){
            hql+=" and indentDate<='"+end+"'";
        }
        hql+=" and indentStatus='"+indentStatus+"'";
        Query query=getEntityManager().createQuery(hql);
        return query.setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
    }


    public long countIndentList( String start, String end, String indentStatus){
        String hql="from IndentEntity where 1=1 ";
        if(Verify.verifyString(start)){
            hql+=" and indentDate>='"+start+"'";
        }
        if(Verify.verifyString(end)){
            hql+=" and indentDate<='"+end+"'";
        }
        hql+=" and indentStatus='"+indentStatus+"'";
        Query query=getEntityManager().createQuery(hql);
        return query.getResultList().size();

    }


}
