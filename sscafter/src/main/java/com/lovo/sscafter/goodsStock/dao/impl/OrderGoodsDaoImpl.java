package com.lovo.sscafter.goodsStock.dao.impl;

import com.lovo.sscafter.goodsStock.dao.IOrderGoodsDao;
import com.lovo.sscafter.goodsStock.dto.OrderGoodsDTO;
import com.lovo.sscafter.goodsStock.entity.OrderGoodsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@Component
public class OrderGoodsDaoImpl implements IOrderGoodsDao {
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }

//    oe.orderId as orderId ,og.goodsOrderDate as goodsOrderDate," +
//            "og.supplierId as supplierId,g.goodsName as goodsName,g.goodsType as goodsType,g.goodsNorms as goodsNorms,g.goodsUnit as goodsUnit," +
//            "g.goodsNum as goodsNum, og.goodsBid as goodsBid,og.isReturnGoods as isReturnGoods
    @Override
    public List<OrderGoodsDTO> findOrderGoods(String goodsName, String goodsType,
      String startDate, String endDate, int currentPage, int rows) {
     String hql="select new com.lovo.sscafter.goodsStock.dto.OrderGoodsDTO(og.id as orderGoodsId,g.goodsId as goodsId,oe.orderId as orderId ,og.goodsOrderDate as goodsOrderDate," +
             " og.supplierId as supplierId,g.goodsName as goodsName,g.goodsType as goodsType,g.goodsNorms as goodsNorms,g.goodsUnit as goodsUnit," +
             " og.goodsNum as goodsNum, og.goodsBid as goodsBid,og.isReturnGoods as isReturnGoods) from " +
             "OrderGoodsEntity og left join GoodsStockEntity g on g.goodsId=og.goods.goodsId left join OrderEntity oe on oe.id2=og.order.id2 where 1=1 ";
       if(null!=goodsName&&goodsName.length()!=0){
           hql+=" and g.goodsName like '%"+goodsName+"%' ";
       }

      if(null!=goodsType&&goodsType.length()!=0){
          hql+=" and g.goodsType=: goodsType1 ";
      }if(null!=startDate&&startDate.length()!=0){
          hql +=" and og.goodsOrderDate>=:startDate";
        }if(null!=endDate&&endDate.length()!=0){
          hql+=" and  og.goodsOrderDate<=:endDate";
        }

        Query query = getEntityManager().createQuery(hql);
        if(null!=goodsType&&goodsType.length()!=0){
            query.setParameter("goodsType1",goodsType);
        }if(null!=startDate&&startDate.length()!=0){
            query.setParameter("startDate",startDate);
        }if(null!=endDate&&endDate.length()!=0){
            query.setParameter("endDate",endDate);
        }



     query.setFirstResult((currentPage-1)*rows);
     query.setMaxResults(rows);

        return query.getResultList();
    }

    @Override
    public long findOrderGoodsCount(String goodsName, String goodsType, String startDate, String endDate) {

        String hql="select count (*)from " +
                "OrderGoodsEntity og left join GoodsStockEntity g on g.goodsId=og.goods.goodsId left join OrderEntity oe on oe.id2=og.order.id2 where 1=1 ";
        if(null!=goodsName&&goodsName.length()!=0){
            hql+=" and g.goodsName like '%"+goodsName+"%' ";
        }

        if(null!=goodsType&&goodsType.length()!=0){
            hql+=" and g.goodsType=: goodsType1 ";
        }if(null!=startDate&&startDate.length()!=0){
            hql +=" and og.goodsOrderDate>=:startDate";
        }if(null!=endDate&&endDate.length()!=0){
            hql+=" and  og.goodsOrderDate<=:endDate";
        }

        Query query = getEntityManager().createQuery(hql);
        if(null!=goodsType&&goodsType.length()!=0){
            query.setParameter("goodsType1",goodsType);
        }if(null!=startDate&&startDate.length()!=0){
            query.setParameter("startDate",startDate);
        }if(null!=endDate&&endDate.length()!=0){
            query.setParameter("endDate",endDate);
        }

        return (Long) query.getSingleResult();
    }

    @Override
    public float findGoodsBidByGoodsId(String goodsId) {
        String hql="select og.goodsBid from " +
                "OrderGoodsEntity og left join GoodsStockEntity g on g.goodsId=og.goods.goodsId where g.goodsId=: goodsId";
        Query query = getEntityManager().createQuery(hql);
        query.setParameter("goodsId",goodsId);

        return(float) query.getSingleResult();
    }
}
