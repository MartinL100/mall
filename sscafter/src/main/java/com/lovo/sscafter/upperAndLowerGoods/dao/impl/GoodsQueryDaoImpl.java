package com.lovo.sscafter.upperAndLowerGoods.dao.impl;

import com.lovo.sscafter.upperAndLowerGoods.dao.IGoodsQueryDao;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import com.lovo.sscafter.upperAndLowerGoods.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@Repository(value="goodsQueryDao")
public class GoodsQueryDaoImpl implements IGoodsQueryDao {
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;
    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }

    public List<GoodsEntity> getGoodsList(int page,int rows,String goodsState, String goodsType, String goodsBooking, String goodsName) {
        String hql="from GoodsEntity where goodsState=:goodsState";
        if(!"不限".equals(goodsType)){
            hql+="  and goodsType=:goodsType";
        }
        if(!"不限".equals(goodsBooking)){
            hql+="  and goodsBooking=:goodsBooking";
        }
         if(!StringUtil.blString(goodsName)){
            hql+="  and goodsName like '%"+goodsName+"%'";
        }
        Query query=	getEntityManager().createQuery(hql);
        query.setParameter("goodsState",goodsState);
        if(!"不限".equals(goodsType)){
            query.setParameter("goodsType",goodsType);
        }
         if(!"不限".equals(goodsBooking)){
            query.setParameter("goodsBooking",goodsBooking);
        }
//         if(!StringUtil.blString(goodsName)){
//            query.setParameter("goodsName",goodsName);
//        }
        return query.setFirstResult(page).setMaxResults(rows).getResultList();
    }

    @Override
    public long getGoodsCount(String goodsState, String goodsType, String goodsBooking, String goodsName) {
        String hql="select count(*) from GoodsEntity where goodsState=:goodsState";
        if(!"不限".equals(goodsType)){
            hql+="  and goodsType=:goodsType";
        }
        if(!"不限".equals(goodsBooking)){
            hql+="  and goodsBooking=:goodsBooking";
        }
        if(!StringUtil.blString(goodsName)){
            hql+="  and goodsName like '%"+goodsName+"%'";
        }
        Query query=	getEntityManager().createQuery(hql);
        query.setParameter("goodsState",goodsState);
        if(!"不限".equals(goodsType)){
            query.setParameter("goodsType",goodsType);
        }
        if(!"不限".equals(goodsBooking)){
            query.setParameter("goodsBooking",goodsBooking);
        }
        return (long) query.getSingleResult();
    }


}
