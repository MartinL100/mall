package com.lovo.sscafter.goodsStock.service.impl;

import com.lovo.sscafter.goodsStock.dao.IReturnGoodsDao;
import com.lovo.sscafter.goodsStock.dto.LookBuyInfoDTO;
import com.lovo.sscafter.goodsStock.dto.ReturnGoodsDto;
import com.lovo.sscafter.goodsStock.service.IReturnGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("returnGoodsService")
public class ReturnGoodsServiceImpl implements IReturnGoodsService {
    @Autowired
   private IReturnGoodsDao  returnGoodsDao;

    @Override
    public List<ReturnGoodsDto> findOrderGoods(String goodsName, String goodsType, String startDate, String endDate, int currentPage, int rows) {
        return returnGoodsDao.findOrderGoods(goodsName,goodsType,startDate,endDate,currentPage,rows);
    }
}
