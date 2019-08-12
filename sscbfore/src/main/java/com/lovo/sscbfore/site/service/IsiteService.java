package com.lovo.sscbfore.site.service;

import com.lovo.sscbfore.user.entity2.SiteEntity;

import java.util.List;


public interface IsiteService {
    //保存
    public void saveSite(SiteEntity site);
   //按用户名查找所以地址
    public List<SiteEntity> findAllByUserSite(String userSite);
    //
   public SiteEntity findSiteBySid(String sid);
       //修改
       public  int updateSite(SiteEntity siteEntity);
     //删除
     public int deleteSite(String sid);
//默认地址
    public void updateSiteDefaultById(String siteid);


}
