package com.lovo.sscafter.goodsStock.service.impl;

import com.lovo.sscafter.goodsStock.dao.ISupplyDao;
import com.lovo.sscafter.goodsStock.dto.LookBuyInfoDTO;
import com.lovo.sscafter.goodsStock.service.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("supplyService")
public class SupplyServiceImpl implements ISupplyService {
    @Autowired
    private ISupplyDao supplyDao;

    @Override
    public List<LookBuyInfoDTO> findOrderGoods(String goodsName, String goodsType,
  String startDate, String endDate, int currentPage, int rows) {

        return supplyDao.findOrderGoods(goodsName,goodsType,startDate,endDate,currentPage,rows);
    }
}
