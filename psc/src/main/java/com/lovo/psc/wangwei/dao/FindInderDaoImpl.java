package com.lovo.psc.wangwei.dao;

import com.lovo.psc.entity.IndentEntity;
import com.lovo.psc.wangwei.dto.InderDTO;
import com.lovo.psc.wangwei.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository(value = "findinderDao")
public class FindInderDaoImpl implements FindinderDao {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }


    @Override
    public long CountInerList(String goodsName, String indentId, String zbTag, String ghTag, String supplierName, String startDate, String endDate) {

        String hql=" select new com.lovo.psc.wangwei.dto.InderDTO(s.goodsName,i.indentId," +
                "s.zbTag,s.ghTag,i.indentDate,i.orderMoney,s.goodsNorms,s.goodsType,s.goodsUnit,s.supplyNum)  " +
                "from  SupplyCenterEntity as s   join s.indentEntity as i  join s.supplier as su where 1=1 ";

        if (!StringUtil.blString(goodsName)) {
            hql += "  and s.goodsName='" + goodsName + "' ";
        }
        if (!StringUtil.blString(indentId)) {
            hql += "  and i.indentId='" + indentId + "' ";
        }
        if (!StringUtil.blString(zbTag)) {
            hql += "  and s.zbTag='" + zbTag + "' ";
        }

        if (!StringUtil.blString(ghTag)) {
            hql += "  and s.ghTag='" + ghTag + "' ";
        }
        if (!StringUtil.blString(startDate)) {
            hql += "  and i.indentDate>'" + startDate + "' ";
        }
        if (!StringUtil.blString(endDate)) {
            hql += "  and i.indentDate<'" + endDate + "' ";
        }

        hql += "  and su.supplierName='" + supplierName + "' ";

        List<InderDTO> list= getEntityManager().createQuery(hql).getResultList();


        return list.size();

    }

    @Override
    public List<InderDTO> findInerList(String goodsName, String indentId, String zbTag, String ghTag, String supplierName,
                                       String startDate,String endDate, int pageNumber, int pageSize) {

        String hql=" select new com.lovo.psc.wangwei.dto.InderDTO(s.goodsName,i.indentId,s.zbTag,s.ghTag,i.indentDate,i.orderMoney,s.goodsNorms,s.goodsType,s.goodsUnit,s.supplyNum)  " +
                "from  SupplyCenterEntity as s   join s.indentEntity as i  join s.supplier as su where 1=1 ";


        if (!StringUtil.blString(goodsName)) {
            hql += "  and s.goodsName='" + goodsName + "' ";
        }
        if (!StringUtil.blString(indentId)) {
            hql += "  and i.indentId='" + indentId + "' ";
        }
        if (!StringUtil.blString(zbTag)) {
            hql += "  and s.zbTag='" + zbTag + "' ";
        }

        if (!StringUtil.blString(ghTag)) {
            hql += "  and s.ghTag='" + ghTag + "' ";
        }
        if (!StringUtil.blString(startDate)) {
            hql += "  and i.indentDate>'" + startDate + "' ";
        }
        if (!StringUtil.blString(endDate)) {
            hql += "  and i.indentDate<'" + endDate + "' ";
        }

        hql += "  and su.supplierName='" + supplierName + "' ";

       List<InderDTO> list= getEntityManager().createQuery(hql).getResultList();


        return list;
    }

    @Override
    public List<IndentEntity> findIner(String supplierName,String indentId ,String indentStatus, String startDate,String endDate, int pageNumber, int pageSize) {

        String hql=" select distinct   s.indentEntity  " +
                "from  SupplyCenterEntity as s   join s.indentEntity as i  join s.supplier as su where 1=1 ";



        if (!StringUtil.blString(indentId)) {
            hql += "  and i.indentId='" + indentId + "' ";
        }


        if (!StringUtil.blString(indentStatus)) {
            hql += "  and i.ghTag='" + indentStatus + "' ";
        }
        if (!StringUtil.blString(startDate)) {
            hql += "  and i.indentDate>'" + startDate + "' ";
        }
        if (!StringUtil.blString(endDate)) {
            hql += "  and i.indentDate<'" + endDate + "' ";
        }


        hql += "  and su.supplierName='" + supplierName + "'  ";
        List<IndentEntity> list= getEntityManager().createQuery(hql).getResultList();

        return list;
    }
    @Override
    public long CountIner(String supplierName,String indentId ,String indentStatus, String startDate,String endDate) {

        String hql=" select  distinct  s.indentEntity  " +
                "from  SupplyCenterEntity as s   join s.indentEntity as i  join s.supplier as su where su.supplierId='\" + supplyId + \"' ";


        if (!StringUtil.blString(indentId)) {
            hql += "  and i.indentId='" + indentId + "' ";
        }


        if (!StringUtil.blString(indentStatus)) {
            hql += "  and i.indentStatus='" + indentStatus + "' ";
        }
        if (!StringUtil.blString(startDate)) {
            hql += "  and i.indentDate>'" + startDate + "' ";
        }
        if (!StringUtil.blString(endDate)) {
            hql += "  and i.indentDate<'" + endDate + "' ";
        }


        hql += "  and su.supplierName='" + supplierName + "' ";
        List<IndentEntity> list= getEntityManager().createQuery(hql).getResultList();

        return list.size();
    }
}
