package com.lovo.csc.dao.promotiondao;

import com.lovo.csc.entity.promotionentity.SuperSaleAudit;

import java.util.List;

public interface IFindSuperSaleAuditDao {

public List<SuperSaleAudit> findSuperSale(String startTime,String endTime,String auditResult,String goodsName,String firstResult,String maxResults );


}
