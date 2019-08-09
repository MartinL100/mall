package com.lovo.psc.orderGoodsZhou.dao.impl;

import com.lovo.psc.orderGoodsZhou.dao.IIdentDao2;
import com.lovo.psc.orderGoodsZhou.dto.SendGoodsDto;
import com.lovo.psc.entity.IndentEntity;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;
@Repository(value = "identDao2")
public class IdentDao2Impl implements IIdentDao2 {
    @Autowired
    private LocalContainerEntityManagerFactoryBean managerFactoryBean;
    public EntityManager getEntityManager(){
        return managerFactoryBean.getObject().createEntityManager();
    }
    @Override
    public List<IndentEntity> getYesPayList(String supplierId, String indentStatus, String startDate, String endDate, Pageable pageable) {
        String hql="";
        List<IndentEntity> list=null;
        //两个都不为空
        if(startDate!=null&&startDate!=""&&endDate!=null&&endDate!=""){
            hql+="select i from IndentEntity i join SupplierEntity s on i.supplier.supplierId=s.supplierId " +
                    " where s.supplierId=?1 and i.indentStatus=?2 and i.indentDate >= ?3 and i.indentDate <= ?4";
            list=getEntityManager().createQuery(hql)
                    .setParameter(1,supplierId)
                    .setParameter(2,indentStatus)
                    .setParameter(3,startDate)
                    .setParameter(4,endDate)
                    .getResultList();
        }
        //开始日期不为空，结束日期为空
        else if(startDate!=null&&startDate!=""&&endDate==null&&endDate==""){
            hql+="select i from IndentEntity i join SupplierEntity s on i.supplier.supplierId=s.supplierId " +
                    " where s.supplierId=?1 and i.indentStatus=?2 and i.indentDate >= ?3";
            list=getEntityManager().createQuery(hql)
                    .setParameter(1,supplierId)
                    .setParameter(2,indentStatus)
                    .setParameter(3,startDate)
                    .getResultList();
        }
        //开始日期为空，结束日期不为空
        else if(startDate==null&&startDate==""&&endDate!=null&&endDate!=""){
            hql+="select i from IndentEntity i join SupplierEntity s on i.supplier.supplierId=s.supplierId " +
                    " where s.supplierId=?1 and i.indentStatus=?2 and i.indentDate <= ?3";
            list=getEntityManager().createQuery(hql)
                    .setParameter(1,supplierId)
                    .setParameter(2,indentStatus)
                    .setParameter(3,endDate)
                    .getResultList();
        }
        //两个都为空
        else {
            hql+="select i from IndentEntity i join SupplierEntity s on i.supplier.supplierId=s.supplierId " +
                    " where s.supplierId=?1 and i.indentStatus=?2 ";
            list=getEntityManager().createQuery(hql)
                .setParameter(1,supplierId)
                .setParameter(2,indentStatus)
                .getResultList();
        }
        return list;
    }

    @Override
    public List<SendGoodsDto> sendGoodsMQ(String indentId) {
        List<SendGoodsDto> list=null;
        String hql="select " +
                "new com.lovo.psc.orderGoodsZhou.dto.SendGoodsDto" +
                "(su.indentEntity.indentId as indentId,su.supplierGoodsEntity.codeId as codeId," +
                "sg.supplier.supplierId as supplierId,su.goodsName as goodsName," +
                "su.goodsNorms as goodsNoms,su.goodsType as goodsType,su.supplyNum as supplyNum," +
                "su.goodsUnit as goodsUnit,su.supplyPrice as supplyPrice) " +
                "from SupplyCenterEntity su left join IndentEntity i " +
                "on su.indentEntity.indentId=i.indentId left join SupplierGoodsEntity sg " +
                "on su.supplierGoodsEntity.codeId=sg.codeId where i.indentId=?1";

        list=getEntityManager().createQuery(hql).setParameter(1,indentId).getResultList();
        return list;
    }
}
