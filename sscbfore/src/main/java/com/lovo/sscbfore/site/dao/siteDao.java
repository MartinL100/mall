package com.lovo.sscbfore.site.dao;

import com.lovo.sscbfore.user.entity2.SiteEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface siteDao extends CrudRepository<SiteEntity, String> {
    /**
     * 根据地址id查询地址信息
     *
     * @param siteId 地址id
     * @return 地址对象
     */
    @Query("from SiteEntity where siteId=?1")
    public SiteEntity findAllBySiteId(String siteId);

    /**
     * 根据地址id修改地址信息
     *
     * @param siteEntity 新对象
     */
    @Query("update SiteEntity set siteArea=siteArea,siteDetail=siteDetail,siteMan=siteMan,siteDefault=siteDefault,sitePhone=sitePhone,sitePostcode=sitePostcode,userSite=userSite where siteId=siteId")
    int updateSite(SiteEntity siteEntity);
    /**
     * 根据用户名查询所有地址
     *
     * @param userSite 用户名
     * @return 地址对象
     */
    @Query("from SiteEntity where userSite.userName=?1")
    public List<SiteEntity> findAllByUserSite(String userSite);

    @Modifying
    @Query("update SiteEntity set siteDefault=0  where siteDefault=1")
    public void updateSiteDefault();
    @Modifying
    @Query("update SiteEntity set siteDefault=?2  where siteId=?1")
    public void updateSiteDefaultById(String siteId,int siteDefault);
}
