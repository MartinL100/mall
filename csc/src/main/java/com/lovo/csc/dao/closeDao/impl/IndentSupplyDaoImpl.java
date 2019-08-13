package com.lovo.csc.dao.closeDao.impl;

import com.lovo.csc.dao.closeDao.IIndentSupplyDao;
import com.lovo.csc.vo.IndentSupplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository(value = "indentSupplyDao")
public class IndentSupplyDaoImpl implements IIndentSupplyDao {
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }
    @Override
    public List<IndentSupplyDto> findIndentSupplyListByIndentId(int pageNumber, int pageSize,String indentId) {

        String hql="select" +
                " new com.lovo.csc.vo.IndentSupplyDto" +
                "(" +
                "s.supplyId as supplyId," +
                "s.goodsName as goodsName," +
                "s.goodsNorms as goodsNorms," +
                "s.goodsType as goodsType," +
                "s.goodsUnit as goodsUnit," +
                "s.supplyNum as supplyNum," +
                "s.indentId.indentId as indentId," +
                "c.supplyPrice as supplyPrice" +
                ")" +
                " from SupplyEntity s" +
                " left join CargoEntity c on s.supplyId=c.supplyId.supplyId" +
                " left join IndentEntity i on s.indentId.indentId=i.indentId" +
                " where i.indentId=?1";

        return getEntityManager().createQuery(hql).setParameter(1,indentId).setFirstResult(pageNumber).setMaxResults(pageSize).getResultList();
    }


    public long countIndentSupplyListByIndentId(String indentId){
        String hql="select" +
                " new com.lovo.csc.vo.IndentSupplyDto" +
                "(" +
                "s.supplyId as supplyId," +
                "s.goodsName as goodsName," +
                "s.goodsNorms as goodsNorms," +
                "s.goodsType as goodsType," +
                "s.goodsUnit as goodsUnit," +
                "s.supplyNum as supplyNum," +
                "s.indentId.indentId as indentId," +
                "c.supplyPrice as supplyPrice" +
                ")" +
                " from SupplyEntity s" +
                " left join CargoEntity c on s.supplyId=c.supplyId.supplyId" +
                " left join IndentEntity i on s.indentId.indentId=i.indentId" +
                " where i.indentId=?1";

        return getEntityManager().createQuery(hql).setParameter(1,indentId).getResultList().size();

    }


}
