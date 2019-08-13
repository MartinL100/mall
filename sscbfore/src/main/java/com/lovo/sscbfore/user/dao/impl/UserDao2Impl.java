package com.lovo.sscbfore.user.dao.impl;

import com.lovo.sscbfore.user.dao.IUserDao2;
import com.lovo.sscbfore.user.entity2.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository(value = "userDao2")
public class UserDao2Impl implements IUserDao2 {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;
    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }
    @Override
    public List<UserEntity> userList(String userName,String userState,int currentPage,int rows) {
        String hql="from UserEntity where userState=1 ";
        if(!userName.equals("no")){
            hql+=" and userName like :userName";
        }
       if(userState.equals("2")){
            hql+=" and userState=3 ";
        }
        Query query=getEntityManager().createQuery(hql);
        if(!userName.equals("no")){
            query.setParameter("userName","%"+userName+"%");
        }
//        if(currentPage>1){
//         List<UserEntity> list=   query.setFirstResult((currentPage-1)*rows).setMaxResults(rows).getResultList();
//        }
        return query.setFirstResult((currentPage-1)*rows).setMaxResults(rows).getResultList();
    }

    @Override
    public int userRows(String userName, String userState) {
        String hql="from UserEntity where userState=1 ";
        if(!userName.equals("no")){
            hql+=" and userName like :userName ";
        }
        if(userState.equals("2")){
            hql+=" and userState=3 ";
        }

        Query query=getEntityManager().createQuery(hql);
        if(!userName.equals("no")){
            query.setParameter("userName","%"+userName+"%");
        }
        int i= query.getResultList().size();
        return i;
    }
}
