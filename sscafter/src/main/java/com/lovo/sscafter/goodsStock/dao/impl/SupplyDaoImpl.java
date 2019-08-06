package com.lovo.sscafter.goodsStock.dao.impl;

import com.lovo.sscafter.goodsStock.dao.ISupplyDao;
import com.lovo.sscafter.goodsStock.dto.LookBuyInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@Component
public class SupplyDaoImpl implements ISupplyDao {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }

    @Override
    public List<LookBuyInfoDTO> findOrderGoods(String goodsName, String goodsType,
           String startDate, String endDate, int currentPage, int rows) {
   String hql="select new com.lovo.sscafter.goodsStock.dto.LookBuyInfoDTO(ie.indentId as indentId,ie.indentDate as indentDate,se.supplyNum as supplyNum" +
           ",se.supplierId as supplierId,ge.goodsName as goodsName,ge.goodsType as goodsType,ge.goodsNorms as goodsNorms,ge.goodsUnit as goodsUnit) from SupplyEntity se left join IndentEntity ie on ie.indentId=se.indent.indentId" +
           " left join GoodsStockEntity ge on ge.goodsId=se.goods.goodsId where 1=1 ";
        if(null!=goodsName&&goodsName.length()!=0){
            hql+=" and ge.goodsName like '%"+goodsName+"%' ";
        }
        if(null!=goodsType&&goodsType.length()!=0){
            hql+=" and ge.goodsType=: goodsType1 ";
        }if(null!=startDate&&startDate.length()!=0){
            hql +=" and ie.indentDate>=:startDate";
        }if(null!=endDate&&endDate.length()!=0){
            hql+=" and  ie.indentDate<=:endDate";
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
}
