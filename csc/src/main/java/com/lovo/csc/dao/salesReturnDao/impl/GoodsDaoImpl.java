package com.lovo.csc.dao.salesReturnDao.impl;

import com.lovo.csc.dao.salesReturnDao.IGoodsDao;
import com.lovo.csc.entity.salesReturnEntity.GoodsEntity;
import com.lovo.csc.entity.salesReturnEntity.OrderGoodsEntity;

import com.lovo.csc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository(value="goodsDao")
public class GoodsDaoImpl implements IGoodsDao{
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {

        return entityManagerFactory.getObject().createEntityManager();
    }
    @Override
    public List<OrderGoodsEntity> findByOrderId(String goodsState, String orderId, String orderName, int pageNumber, int pageSize) {
        String hql = "   from  OrderGoodsEntity o where  1=1  ";
        if (!StringUtil.blString(goodsState)){
         hql+=" and o.goodsState='"+goodsState+"' ";
        }
        if (!StringUtil.blString(orderId)){
            hql+=" and o.orderId='"+orderId+"' ";
        }
        if (!StringUtil.blString(orderName)){
            hql+=" and o.orderName='"+orderName+"' ";
        }

        Query query=getEntityManager().createQuery(hql);

        List<OrderGoodsEntity>list=query.setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
        return list;

    }

    @Override
    public long getPageListOrderGoodsEntityCount(String goodsState, String orderId, String orderName) {
        String hql = "   from  OrderGoodsEntity o where 1=1  ";
        if (!StringUtil.blString(goodsState)){
            hql+=" and o.goodsState='"+goodsState+"' ";
        }
        if (!StringUtil.blString(orderId)){
            hql+=" and o.orderId='"+orderId+"' ";
        }
        if (!StringUtil.blString(orderName)){
            hql+=" and o.orderName='"+orderName+"' ";
        }
//        hql += "  and o.goodssId='" + goodssId + "' ";
        Query query=getEntityManager().createQuery(hql);
        long b=query.getResultList().size();
        return b;
    }

    public List<GoodsEntity> findByGoods(String goodsId, int pageNumber, int pageSize) {
        String hql = "   from  GoodsEntity  where 1=1  ";
        if (!StringUtil.blString(goodsId)){
            hql+=" and goodsId='"+goodsId+"' ";
        }
        Query query=getEntityManager().createQuery(hql);

        List<GoodsEntity>list=query.setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
        return list;
    }

    @Override
    public long getPageListGoodsCount(String goodsId) {
        String hql = "   from  GoodsEntity  where 1=1  ";
        if (!StringUtil.blString(goodsId)){
            hql+=" and goodsId='"+goodsId+"' ";
        }
        Query query=getEntityManager().createQuery(hql);
        long A=query.getResultList().size();
        return A;
    }
}
