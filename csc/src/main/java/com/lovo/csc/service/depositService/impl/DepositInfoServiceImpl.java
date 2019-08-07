package com.lovo.csc.service.depositService.impl;

import com.lovo.csc.dao.depositDao.IDepositInfoDao;
import com.lovo.csc.entity.depositEntity.DepositInfoEntity;
import com.lovo.csc.service.depositService.IDepositInfoService;
import com.lovo.csc.vo.DepositVo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "depositInfoService")
public class DepositInfoServiceImpl implements IDepositInfoService {
    @Autowired
    private IDepositInfoDao depositInfoDao;
    @Override
    public void addDepositInfo(DepositInfoEntity depositInfo) {
        depositInfoDao.save(depositInfo);
    }

    @Override
    public List<DepositVo> getAllByUserName(String userName) {
        Pageable pageable=new PageRequest(0,7);
        List<DepositVo> list= depositInfoDao.getAllByUserName(userName,pageable);
        list.remove(0);
        return list;
    }
}
