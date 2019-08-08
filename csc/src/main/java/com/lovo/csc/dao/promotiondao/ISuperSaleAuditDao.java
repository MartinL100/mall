package com.lovo.csc.dao.promotiondao;

import com.lovo.csc.entity.promotionentity.SuperSaleAudit;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ISuperSaleAuditDao extends CrudRepository<SuperSaleAudit,String> {


    @Query(" from SuperSaleAudit where auditResult=?1")
    public List<SuperSaleAudit> findAllByAuditResult(String  AuditResult);
    @Modifying@Transactional
    @Query(" update SuperSaleAudit set auditMan=?1 ,auditTime=?2,auditResult=?3 where id=?4")
    public void updateSuperSaleAudit(String  auditMan,String  auditTime,String auditResult,int id);

    public SuperSaleAudit findSuperSaleAuditById(int id);
}
