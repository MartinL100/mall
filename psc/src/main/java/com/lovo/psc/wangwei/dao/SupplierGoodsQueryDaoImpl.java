package com.lovo.psc.wangwei.dao;

import com.lovo.psc.entity.SupplierGoodsEntity;
import com.lovo.psc.wangwei.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository(value = "supplierGoodsQueryDao")
public class SupplierGoodsQueryDaoImpl implements ISupplierGoodsQueryDao {
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }

    @Override
    public List<SupplierGoodsEntity> pageList(String goodsName, String supplierName, String goodsType, String codeId, int pageNumber, int pageSize) {
         String hql = "   from  SupplierGoodsEntity  as s  where 1=1  ";

        if (!StringUtil.blString(goodsName)) {
            hql += "  and s.goodsName='" + goodsName + "' ";
        }
        if (!StringUtil.blString(goodsType)) {
            hql += "  and s.goodsType='" + goodsType + "' ";
        }
        if (!StringUtil.blString(codeId)) {
            hql += "  and s.codeId='" + codeId + "' ";
        }
        hql += "  and s.supplier.supplierName='" + supplierName + "' ";
        System.out.println(hql);
        List<SupplierGoodsEntity> list    = getEntityManager().createQuery(hql).getResultList();


        return list;

    }

    @Override
    public long countPage(String goodsName, String supplierName, String goodsType, String codeId) {
        String hql = "   from  SupplierGoodsEntity  as s  where 1=1   ";

        if (!StringUtil.blString(goodsName)) {
            hql += "  and s.goodsName='" + goodsName + "' ";
        }
        if (!StringUtil.blString(goodsType)) {
            hql += "  and s.goodsType='" + goodsType + "' ";
        }
        if (!StringUtil.blString(codeId)) {
            hql += "  and s.codeId='" + codeId + "' ";
        }
        hql += "   and s.supplier.supplierName='" + supplierName + "' ";
        System.out.println(hql);
        Query query = getEntityManager().createQuery(hql);
        long a = query.getResultList().size();


        return a;
    }
}
