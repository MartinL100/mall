//package com.lovo.csc.entity.salesReturnEntity;
//
//import com.lovo.csc.entity.SupplierEntity;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.util.List;
//
///**
// * 供应商商品实体类
// */
//@Entity
//@Table(name = "sys_supplier_goods")
//public class SupplierGoodsEntity {
//    @Id
//    @GenericGenerator(name = "cuuid",strategy = "uuid")
//    @GeneratedValue(generator = "cuuid")
//    @Column(length = 32)
//    /**商品编号 */
//    private  String codeId;
//    @Column(length = 48)
//    /**商品名称 */
//    private String goodsName;
//    @Column(length = 48)
//    /**商品规格 */
//    private  String goodsNoms;
//    @Column(length = 48)
//    /**商品类型 */
//    private String goodsType;
//    @Column(length = 48)
//    /**商品单位 */
//    private String goodsUnit;
//    /**商品库存 */
//    private int goodsNum;
//    /**商品状态*/
//    private String supplierStatus;
//    /**商品操作*/
//    private String supplierType;
//    /**供应商对象 */
//    @ManyToOne
//    @JoinColumn(name="supplierId")
//    private SupplierEntity supplier;
//
//    public String getCodeId() {
//        return codeId;
//    }
//
//    public void setCodeId(String codeId) {
//        this.codeId = codeId;
//    }
//
//    public String getGoodsName() {
//        return goodsName;
//    }
//
//    public void setGoodsName(String goodsName) {
//        this.goodsName = goodsName;
//    }
//
//    public String getGoodsNoms() {
//        return goodsNoms;
//    }
//
//    public void setGoodsNoms(String goodsNoms) {
//        this.goodsNoms = goodsNoms;
//    }
//
//    public String getGoodsType() {
//        return goodsType;
//    }
//
//    public void setGoodsType(String goodsType) {
//        this.goodsType = goodsType;
//    }
//
//    public String getGoodsUnit() {
//        return goodsUnit;
//    }
//
//    public void setGoodsUnit(String goodsUnit) {
//        this.goodsUnit = goodsUnit;
//    }
//
//    public int getGoodsNum() {
//        return goodsNum;
//    }
//
//    public void setGoodsNum(int goodsNum) {
//        this.goodsNum = goodsNum;
//    }
//
//    public SupplierEntity getSupplier() {
//        return supplier;
//    }
//
//    public void setSupplier(SupplierEntity supplier) {
//        this.supplier = supplier;
//    }
//
//    public String getSupplierStatus() {
//        return supplierStatus;
//    }
//
//    public void setSupplierStatus(String supplierStatus) {
//        this.supplierStatus = supplierStatus;
//    }
//
//    public String getSupplierType() {
//        return supplierType;
//    }
//
//    public void setSupplierType(String supplierType) {
//        this.supplierType = supplierType;
//    }
//
//    public SupplierGoodsEntity(String goodsName, String goodsNoms, String goodsType, String goodsUnit, int goodsNum, SupplierEntity supplier) {
//        this.goodsName = goodsName;
//        this.goodsNoms = goodsNoms;
//        this.goodsType = goodsType;
//        this.goodsUnit = goodsUnit;
//        this.goodsNum = goodsNum;
//        this.supplier = supplier;
//    }
//
//    public SupplierGoodsEntity() {
//    }
//}
