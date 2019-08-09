package com.lovo.psc.orderGoodsZhou.service;

import com.lovo.psc.orderGoodsZhou.dto.SendGoodsDto;
import com.lovo.psc.entity.IndentEntity;
import com.lovo.psc.entity.SupplyCenterEntity;

import java.util.List;

public interface IIdentService {

    /**
     * 根据大订单编号修改状态和结算时间
     * @param indentId 订单id
     * @param indentStatus 订单状态
     * @param indentDate 订单结算时间
     */
    public void updateByindentId2(String indentId,String indentStatus,String indentDate);
    /**
     * 根据订单号修改订单状态（未支付）
     * @param indentId
     * @param indentStatus
     */
    public void updateByIndentId(String indentId,String indentStatus);
    /**
     * 通过供应商编号查询所有未支付订单集合并分页
     * @param supplierId 供应商id
     * @param indentStatus 订单状态
     * @return 订单集合
     */
    public List<IndentEntity> getNoPayList(String supplierId, String indentStatus, int pageNumber, int pageSize);

    /**
     * 根据订单id查询所属订单的所有商品
     * @param indentId 订单id
     * @return 商品集合
     */
    public List<SupplyCenterEntity> getGoodsList(String indentId,int pageNumber, int pageSize);
    /**
     * 根据订单id查询所属订单的所有商品用于计算总金额
     * @param indentId 订单id
     * @return 商品集合
     */
    public List<SupplyCenterEntity> getGoodsList2(String indentId);
    /**
     * 通过供应商编号动态查询所有已支付订单集合并分页
     * @param supplierId 供应商id
     * @param indentStatus 订单状态
     * @return 订单集合
     */
    public List<IndentEntity> getYesPayList(String supplierId, String indentStatus,String startDate,String endDate,int pageNumber, int pageSize);
    /**
     * 根据订单号查询供货商品集合和供应商id
     * @param indentId 订单id
     */
    public List<SendGoodsDto> sendGoodsMQ(String indentId);
    /**
     * 通过商品id修改库存
     * @param codeId 商品id
     * @param supplyNum 供货数量
     */
    public void updateByCodeId(String codeId,int supplyNum);
}
