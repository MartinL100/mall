package com.lovo.psc.orderGoodsZhou.dao;

import com.lovo.psc.entity.IndentEntity;
import com.lovo.psc.entity.SupplyCenterEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface IIdentDao extends CrudRepository<IndentEntity,String> {

    /**
     * 根据大订单编号修改状态和结算时间
     * @param indentId 订单id
     * @param indentStatus 订单状态
     * @param indentDate 订单结算时间
     */
    @Transactional
    @Modifying
    @Query("update IndentEntity set indentStatus=?2,indentDate=?3 where indentId=?1")
    public void updateByindentId2(String indentId,String indentStatus,String indentDate);

    /**
     * 根据订单号修改订单状态（未支付）
     * @param indentId
     * @param indentStatus
     */
    @Transactional
    @Modifying
    @Query("update IndentEntity set indentStatus=?2 where  indentId=?1")
    public void updateByIndentId(String indentId,String indentStatus);
    /**
     * 通过供应商编号查询所有未支付订单集合并分页
     * @param supplierId 供应商id
     * @param indentStatus 订单状态
     * @return 订单集合
     */
    @Query("select i from IndentEntity i left join SupplierEntity s on i.supplier.supplierId=s.supplierId where s.supplierId=?1 and i.indentStatus=?2")
    public List<IndentEntity> getNoPayList(String supplierId, String indentStatus, Pageable pageable);

    /**
     * 根据订单id查询所属订单的所有商品
     * @param indentId 订单id
     * @return 商品集合
     */
    @Query("select su from SupplyCenterEntity su left join IndentEntity i on su.indentEntity.indentId=i.indentId left join SupplierGoodsEntity sg on su.supplierGoodsEntity.codeId=sg.codeId where i.indentId=?1")
    public List<SupplyCenterEntity> getGoodsList(String indentId,Pageable pageable);
    /**
     * 根据订单id查询所属订单的所有商品用于计算总金额
     * @param indentId 订单id
     * @return 商品集合
     */
    @Query("select su from SupplyCenterEntity su left join IndentEntity i on su.indentEntity.indentId=i.indentId left join SupplierGoodsEntity sg on su.supplierGoodsEntity.codeId=sg.codeId where i.indentId=?1")
    public List<SupplyCenterEntity> getGoodsList2(String indentId);

    /**
     * 通过商品id修改库存
     * @param codeId
     * @param supplyNum
     */
    @Transactional
    @Modifying
    @Query("update SupplierGoodsEntity set goodsNum=goodsNum-?2 where codeId=?1")
    public void updateByCodeId(String codeId,int supplyNum);
}
