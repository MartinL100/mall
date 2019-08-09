package com.lovo.sscbfore.site.service;

import com.lovo.sscbfore.user.entity2.SiteEntity;

import java.util.List;


public interface IsiteService {
    //保存
    public void saveSite(SiteEntity site);
   //按用户名查找所以地址
    public List<SiteEntity> findAllByUserSite(String userSite);
    //
    SiteEntity findSiteBySid(String sid);

    int updateSite(SiteEntity siteEntity);
     //删除
    int deleteSite(String sid);

    SiteEntity findSiteISSiteDefault();


}
