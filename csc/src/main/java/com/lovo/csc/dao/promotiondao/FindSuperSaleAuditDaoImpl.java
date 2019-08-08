package com.lovo.csc.dao.promotiondao;

import com.lovo.csc.entity.promotionentity.SuperSaleAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository("findSuperSaleAuditDao")
//@Repository(value="findSuperSaleAuditDao")
public class FindSuperSaleAuditDaoImpl implements IFindSuperSaleAuditDao{
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;
    public EntityManager getEntityManager() {
        //获得session
        return	 entityManagerFactory.getObject().createEntityManager() ;
    }


    @Override
    public List<SuperSaleAudit> findSuperSale
            (String startTime, String endTime, String auditResult, String goodsName,String firstResult,String maxResults) {
        //拼接hql语句
        String time="applyTime";
        String hql="from SuperSaleAudit where 1=1";
        if(auditResult==null) {
            auditResult="";
        }
            //判断是查询审核通过还是未通过的 ‘1’代表未通过，进而判断是根据申请时间还是审核时间筛选
            if("1".equals(auditResult)){
            hql+="  and  auditResult='1' ";
                if(endTime!=null&&endTime.length()>0) {
                    hql+="  and  applyTime <='"+endTime+"' ";
                }
                if(startTime!=null&&startTime.length()>0) {
                    hql+="  and  applyTime >= '"+startTime+"' ";
                }
            }else {
                time="auditTime";
                hql+="  and  auditResult !='1' and  auditResult like '%"+auditResult+"%'  ";
                if(endTime!=null&&endTime.length()>0) {
                    hql+="  and  auditTime <='"+endTime+"' ";
                }
                if(startTime!=null&&startTime.length()>0) {
                    hql+="  and  auditTime >= '"+startTime+"' ";
                }
            }

        if(goodsName!=null&&goodsName.length()>0) {
            hql+="  and  goodsName like '%"+goodsName+"%' ";
        }
        hql+=" order by "+time+" desc ";
        //建立查询
        Query query=getEntityManager().createQuery(hql);
        if(firstResult.length()>0) {
            query.setMaxResults(Integer.parseInt(maxResults));  //设置获取的数量
            query.setFirstResult(Integer.parseInt(firstResult));//设置从第几个开始获取
        }
        //返回查询结果
        List list= query.getResultList();
        return list;

    }


}
