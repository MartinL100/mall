package com.lovo.csc.service.promotionService;

import com.lovo.csc.entity.promotionentity.SuperSaleAudit;

import java.util.List;

public interface ISuperSaleAuditService {


    /**
     * 添加促销审核信息
     * @param superSaleAudit 促销审核对象
     */
    public void saveSuperSaleAudit(SuperSaleAudit superSaleAudit);
    /**
     * 模糊查询促销审核记录集合
     * @param startTime
     * @param endTime
     * @param auditResult
     * @param goodsName
     * @return
     */
    public List<SuperSaleAudit> findSuperSaleAudits
            (String startTime, String endTime, String auditResult, String goodsName,String firstResult,String maxResults);

    /**
     * 审核后修改对应数据库信息
     * @param auditMan
     * @param auditTime
     * @param auditResult
     * @param id
     */
    public void updateSuperSaleAudit
            (String  auditMan,String  auditTime,String auditResult,int id);

    /**
     * 根据id查找对应的
     * @param id
     * @return
     */
    public SuperSaleAudit findSuperSaleAuditById(int id);
}
