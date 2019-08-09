package com.lovo.sscbfore.site.service.impl;

import com.lovo.sscbfore.site.dao.siteDao;
import com.lovo.sscbfore.site.service.IsiteService;
import com.lovo.sscbfore.user.entity2.SiteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "siteService")
public class siteServiceImpl implements IsiteService {

    @Autowired
    private siteDao siteDao;
    public void saveSite(SiteEntity site) {
        siteDao.save(site);
    }

    @Override
    public List<SiteEntity> findAllByUserSite(String userSite) {

        return siteDao.findAllByUserSite(userSite);
    }

    @Override
    public SiteEntity findSiteBySid(String sid) {
        return siteDao.findAllBySiteId(sid);
    }

    @Override
    public int updateSite(SiteEntity siteEntity) {
        siteDao.save(siteEntity);
        return 1;
    }

    @Override
    public int deleteSite(String sid) {
        siteDao.deleteById(sid);
        return 1;
    }

    @Override
    public SiteEntity findSiteISSiteDefault() {
        return siteDao.findSiteISSiteDefault();
    }
}
