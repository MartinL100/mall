package com.lovo.sscbfore.user.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="sys_site")
public class SiteEntity {
    @Id
    @GenericGenerator(name = "siteUuid", strategy = "uuid")
    @GeneratedValue(generator = "siteUuid")
    @Column(name="site_id",length = 32)
    /**地址ID*/
    private String siteId;
    @Column(name="site_man",length =48)
    /**收货人*/
    private String siteMan;
    @Column(name="site_area",length =48)
    /**所在地区*/
    private String siteArea;
    @Column(name="site_detail",length =48)
    /**详细地址*/
    private String siteDetail;
    @Column(name="site_postcode",length =48)
    /**邮编*/
    private String sitePostcode;
    @Column(name="site_phone",length =48)
    /**收货电话*/
    private String sitePhone;
    @Column(name="site_default")
    /**默认地址*/
    private int siteDefault;
    @ManyToOne
    @JoinColumn(name="user_name")
    /**用户地址对象*/
    private UserEntity userSite;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteMan() {
        return siteMan;
    }

    public void setSiteMan(String siteMan) {
        this.siteMan = siteMan;
    }

    public String getSiteArea() {
        return siteArea;
    }

    public void setSiteArea(String siteArea) {
        this.siteArea = siteArea;
    }

    public String getSiteDetail() {
        return siteDetail;
    }

    public void setSiteDetail(String siteDetail) {
        this.siteDetail = siteDetail;
    }

    public String getSitePostcode() {
        return sitePostcode;
    }

    public void setSitePostcode(String sitePostcode) {
        this.sitePostcode = sitePostcode;
    }

    public String getSitePhone() {
        return sitePhone;
    }

    public void setSitePhone(String sitePhone) {
        this.sitePhone = sitePhone;
    }

    public int getSiteDefault() {
        return siteDefault;
    }

    public void setSiteDefault(int siteDefault) {
        this.siteDefault = siteDefault;
    }

    public UserEntity getUserSite() {
        return userSite;
    }

    public void setUserSite(UserEntity userSite) {
        this.userSite = userSite;
    }
}
