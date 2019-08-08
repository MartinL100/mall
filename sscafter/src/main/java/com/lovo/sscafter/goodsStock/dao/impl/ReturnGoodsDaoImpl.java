package com.lovo.sscafter.goodsStock.dao.impl;

import com.lovo.sscafter.goodsStock.dao.IReturnGoodsDao;
import com.lovo.sscafter.goodsStock.dto.ReturnGoodsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@Component
public class ReturnGoodsDaoImpl implements IReturnGoodsDao {
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }



    @Override
    public List<ReturnGoodsDto> findOrderGoods(String goodsName, String goodsType, String startDate, String endDate, int currentPage, int rows) {
String hql="select new com.lovo.sscafter.goodsStock.dto.ReturnGoodsDto(re.returnGoodsId as returnGoodsId," +
        "re.returnGoodsDate as returnGoodsDate,oe.supplierId as supplierId,ge.goodsId as goodsId,ge.goodsName as goodsName," +
        " ge.goodsType as goodsType,ge.goodsNorms as goodsNorms," +
        "  ge.goodsUnit as goodsUnit,re.returnGoodsCause as returnGoodsCause,re.returnGoodsNum as returnGoodsNum,oe.goodsBid as goodsBid) from GoodsStockEntity ge left " +
        " join ReturnGoodsEntity re on ge.goodsId=re.goods.goodsId left join OrderGoodsEntity oe " +
        " on oe.goods.goodsId=ge.goodsId where re.returnGoodsCause is not null ";
        if(null!=goodsName&&goodsName.length()!=0){
            hql+=" and ge.goodsName like '%"+goodsName+"%' ";
        }
        if(null!=goodsType&&goodsType.length()!=0){
            hql+=" and ge.goodsType=: goodsType1 ";
        }if(null!=startDate&&startDate.length()!=0){
            hql +=" and re.returnGoodsDate>=:startDate";
        }if(null!=endDate&&endDate.length()!=0){
            hql+=" and  re.returnGoodsDate<=:endDate";
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
        String hql="select count(*) from GoodsStockEntity ge left " +
                " join ReturnGoodsEntity re on ge.goodsId=re.goods.goodsId left join OrderGoodsEntity oe " +
                " on oe.goods.goodsId=ge.goodsId where re.returnGoodsCause is not null ";
        if(null!=goodsName&&goodsName.length()!=0){
            hql+=" and ge.goodsName like '%"+goodsName+"%' ";
        }
        if(null!=goodsType&&goodsType.length()!=0){
            hql+=" and ge.goodsType=: goodsType1 ";
        }if(null!=startDate&&startDate.length()!=0){
            hql +=" and re.returnGoodsDate>=:startDate";
        }if(null!=endDate&&endDate.length()!=0){
            hql+=" and  re.returnGoodsDate<=:endDate";
        }
        Query query = getEntityManager().createQuery(hql);

        if(null!=goodsType&&goodsType.length()!=0){
            query.setParameter("goodsType1",goodsType);
        }if(null!=startDate&&startDate.length()!=0){
            query.setParameter("startDate",startDate);
        }if(null!=endDate&&endDate.length()!=0){
            query.setParameter("endDate",endDate);
        }

        return(Long) query.getSingleResult();
    }
}
