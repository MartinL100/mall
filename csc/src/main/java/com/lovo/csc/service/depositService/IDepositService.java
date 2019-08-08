package com.lovo.csc.service.depositService;

import com.lovo.csc.entity.depositEntity.DepositEntity;

/**
 * 预存款业务层
 */
public interface IDepositService {


    /**
     * 查询预存款
     *
     * @param userName 用户名
     * @return 预存款对象
     */
    public DepositEntity findDepositByUserName(String userName);

    /**
     * 添加预存款，若用户存在，直接修改累计金额、剩余金额和等级；若不存在，重新添加
     * 等级为：1万以下
     * @param userName 用户名
     * @param saveMoney 添加金额
     * @return 预存款对象
     */
    public DepositEntity saveDepositMoney(String userName,Double saveMoney);

    /**
     * 查询选择预付款支付，优惠后的价格
     * 1、先通过用户名查询出该预存款对象
     * 2、判断预存款剩余金额是否大于支付金额，若true，返回折扣后的价格，否则，返回 -1
     *
     * @param userName 用户名
     * @param allPrice 支付金额
     * @return 实际支付金额
     */
    public Double findDepositDiscount(String userName, Double allPrice);


    /**
     * 用户使用预付款结算，修改剩余预存款，并生成预存款明细
     * 1、先通过用户名查询出该预存款对象
     * 2、判断预存款剩余金额减去支付金额是否大于等于0，若true，修改数据库并保存，返回折扣后的价格，
     *    否则，返回-1
     *
     * @param userName 用户名
     * @param allPrice 支付金额
     * @return 实际支付金额
     */
    public Double deductionDeposit(String userName, Double allPrice);

    /**
     * 退货退款
     * @param userName 用户名
     * @param goodsPrice 退款金额
     * @return 返回“退货成功”
     */
    public String salesReturn(String userName,Double  goodsPrice);


}
