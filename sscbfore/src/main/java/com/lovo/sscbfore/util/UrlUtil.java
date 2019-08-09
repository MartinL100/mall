package com.lovo.sscbfore.util;

/**
 * url工具类
 */
public class UrlUtil {
    /** 验证库存 goodsMap存放： goodsId：goodNum ,   restfull*/
    public static final String IS_ENOUGH_URL="http://sscAfter/findGoodsNum/";
    /**保存订单，订单信息以json方式 变量名为orderInfo，post提交*/
    public static final String SAVE_ORDER_URL="http://sscAfter/receiveOrder/";
    /**审核订单，订单信息以json方式 变量名为orderInfo，post提交*/
    public static final String CHECK_ORDER_URL="http://csc/checkOrder/";
    /**减少库存 goodsMap存放： goodsId：goodNum ,   restfull*/
    public static final String UPDATE_GOODS_NUM_URL="http://sscAfter/updateGoodsNum/1/";
    /**修改订单状态，orderNum 订单编号，restfull*/
    public static final String UPDATE_ORDER_STATUE_URL="http://sscAfter/updateOrderType/";
    /**存入预存款 {userName} 用户名/{saveMoney存入金额  r estfull*/
    public static final String SAVE_DEPOSIT_MONEY_URL="http://csc/getDepositObj/";
    /**{userName}用户名， restfull*/
    public static final String FIND_DEPOSIT_INFO="http://csc/findDeposit/";
    /**验证用户存款是否充足  {userName}/{allPrice}/{ payMethod}   用户名/总价/支付方式，restfull*/
    public static final String MONEY_IS_ENOUGH = "http://csc/countMoney/";
    /**初始化下拉框*/
    public static final String INIT_SELECT="";
    /**动态查询预售商品 商品类型、商品名、当前页、每页显示行数*/
    public static final String FIND_GOODS_PRESELL="Http://sscAfter/dynamicPresell/";

}
