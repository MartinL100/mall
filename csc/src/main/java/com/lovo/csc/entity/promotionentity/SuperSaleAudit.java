package com.lovo.csc.entity.promotionentity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class SuperSaleAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**商品id，唯一标识*/
    @Column(length = 48)
    private String goodsId;
    /**商品名*/
    @Column(length = 48)
    private String goodsName;
    /**商品价格*/
    @Column(length = 48)
    private String goodsPrice;
    /**商品类型*/
    @Column(length = 48)
    private String goodsType;
    /**商品规格*/
    @Column(length = 48)
    private String goodsNorms;
    /**商品计量单位*/
    @Column(length = 48)
    private String goodsUnit;
    /**商品数量*/
    @Column(length = 48)
    private String goodsNum;
    /**商品进价*/
    @Column(length = 48)
    private String goodsBid;
    /**商品折扣率*/
    @Column(length = 48)
   private String goodsDiscount;
   /**促销申请人 */
   @Column(length = 48)
   private String applyMan;
   /**申请时间*/
   @Column(length = 48)
   private String applyTime;
    /**审核人*/
    @Column(length = 48)
    private String auditMan;
    /**审核时间*/
    @Column(length = 48)
    private String auditTime;
    /**审核结果*/
    @Column(length = 48)
    private String auditResult="1";


    @Override
    public String toString() {
        return "SuperSaleAudit{" +
                "id=" + id +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", goodsNorms='" + goodsNorms + '\'' +
                ", goodsUnit='" + goodsUnit + '\'' +
                ", goodsNum='" + goodsNum + '\'' +
                ", goodsBid='" + goodsBid + '\'' +
                ", goodsDiscount='" + goodsDiscount + '\'' +
                ", applyMan='" + applyMan + '\'' +
                ", applyTime='" + applyTime + '\'' +
                ", auditMan='" + auditMan + '\'' +
                ", auditTime=" + auditTime +
                ", auditResult='" + auditResult + '\'' +
                '}';
    }

    public SuperSaleAudit() {
    }
    public SuperSaleAudit(String goodsId, String goodsName, String goodsPrice, String goodsType, String goodsNorms, String goodsUnit, String goodsNum, String goodsBid, String goodsDiscount, String applyMan, String applyTime) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsType = goodsType;
        this.goodsNorms = goodsNorms;
        this.goodsUnit = goodsUnit;
        this.goodsNum = goodsNum;
        this.goodsBid = goodsBid;
        this.goodsDiscount = goodsDiscount;
        this.applyMan = applyMan;
        this.applyTime = applyTime;
    }


    public String getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(String goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public String getApplyMan() {
        return applyMan;
    }

    public void setApplyMan(String applyMan) {
        this.applyMan = applyMan;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getAuditMan() {
        return auditMan;
    }

    public void setAuditMan(String auditMan) {
        this.auditMan = auditMan;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsNorms() {
        return goodsNorms;
    }

    public void setGoodsNorms(String goodsNorms) {
        this.goodsNorms = goodsNorms;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsBid() {
        return goodsBid;
    }

    public void setGoodsBid(String goodsBid) {
        this.goodsBid = goodsBid;
    }
}
