package com.lovo.sscafter.orderManagement.service.impl;

import com.lovo.sscafter.orderManagement.dao.IGoodsManagementDao;
import com.lovo.sscafter.orderManagement.dao.IOrderManagementDao;
import com.lovo.sscafter.orderManagement.entity.DTO.ReturnGoodsDTO;
import com.lovo.sscafter.orderManagement.entity.OrderForGoodsEntity;
import com.lovo.sscafter.orderManagement.service.IGoodsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class GoodsManagmentServiceImpl implements IGoodsManagementService {
    @Autowired
    IGoodsManagementDao goodsManagementDao;


    @Autowired
    IOrderManagementDao orderManagementDao;
    @Override
    public List<OrderForGoodsEntity> findGoods(String orderNum, int currentPage, int rows) {
        Pageable page = new PageRequest(currentPage-1,rows);
        return goodsManagementDao.findGoods(orderNum,page);
    }

    @Override
    public int findGoodsRows(String orderNum) {
        return goodsManagementDao.findGoodsRows(orderNum);
    }

    @Override
    public void updateOrderReturn(ReturnGoodsDTO goodsDTO) {
        if(goodsDTO.getGoodsStatus()==1){
            goodsManagementDao.updateOrderReturn(goodsDTO.getOrderNum(),goodsDTO.getGoodsId(),goodsDTO.getGoodsStatus());
            orderManagementDao.updateOrderType(goodsDTO.getOrderNum(),2+"");
        }else{
            //修改商品退货状态
            goodsManagementDao.updateOrderReturn(goodsDTO.getOrderNum(),goodsDTO.getGoodsId(),goodsDTO.getGoodsStatus());
            //得到商品利润
            float orderProfit = goodsManagementDao.findOrderProfit(goodsDTO.getGoodsId());
            //根据订单号修改利润
            orderManagementDao.updateOrderProfit(goodsDTO.getOrderNum(),orderProfit);
        }
    }
}

