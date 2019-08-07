package com.lovo.sscafter.orderManagement.service.impl;

import com.lovo.common.entity.GoodsDTO;
import com.lovo.common.entity.OrderDTO;
import com.lovo.sscafter.orderManagement.dao.IGoodsManagementDao;
import com.lovo.sscafter.orderManagement.dao.IOrderManagementDao;
import com.lovo.sscafter.orderManagement.dao.IOrderTrendsDao;
import com.lovo.sscafter.orderManagement.entity.OrderForGoodsEntity;
import com.lovo.sscafter.orderManagement.entity.OrderManagementEntity;
import com.lovo.sscafter.orderManagement.service.IOrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderManagementServiceImpl implements IOrderManagementService {
    @Autowired
    IOrderManagementDao orderManagementDao;


    @Autowired
    IGoodsManagementDao goodsManagementDao;
    @Autowired
    IOrderTrendsDao orderTrendsDao;
    public boolean updateOrderDelType(String orderNum) {
        int temp = orderManagementDao.updateOrderDelType(orderNum);
        if(temp == 0){
            return false;
        }
        return true;
    }

    @Override
    public List<OrderManagementEntity> findTrendsOrderInfo(String orderDate, int orderType, int currentPage, int rows, String userName) {
        List<OrderManagementEntity> list = orderTrendsDao.findTrendsOrderInfo(orderDate, orderType, currentPage, rows, userName);
        return list;
    }

    @Override
    public int findOrderRows(String orderDate, int orderType, String userName) {
        return orderTrendsDao.findOrderRows(orderDate,orderType,userName);
    }

    @Override
    public List<OrderForGoodsEntity> receiveOrder(OrderDTO orderDTO) {
        float tempOrderProfit=0;
        OrderManagementEntity orderEntity = new OrderManagementEntity();
        orderEntity.setOrderNum(orderDTO.getOrderNum());

        orderEntity.setOrderType("1");
        orderEntity.setOrderDate(orderDTO.getOrderDate());
        orderEntity.setOrderMoney(orderDTO.getOrderMoney());
        orderEntity.setPayMoney(Float.parseFloat(orderDTO.getPayMoney()));
        orderEntity.setPayMethod(orderDTO.getPayMethod());
        orderEntity.setOrderAddress(orderDTO.getAddressId());
        orderEntity.setOrderDelType(0);
        orderEntity.setUserName(orderDTO.getUserName());
        orderEntity.setGoodsSize(orderDTO.getGoodsDTOList().size());

        List<OrderForGoodsEntity> goodslist =new ArrayList<OrderForGoodsEntity>();
        //准备放商品
        for (GoodsDTO goodsDTO:orderDTO.getGoodsDTOList()) {
            OrderForGoodsEntity ofge = new OrderForGoodsEntity();
            ofge.setGoodsName(goodsDTO.getGoodsName());
            ofge.setGoodsNorms(goodsDTO.getGoodsNorms());
            ofge.setGoodsType(goodsDTO.getGoodsType());
            ofge.setGoodsNum(goodsDTO.getGoodsNum().intValue());
            ofge.setGoodsPrice(goodsDTO.getGoodsPrice());
            ofge.setGoodsUnit(goodsDTO.getGoodsUnit());
            ofge.setGoodsStatus(1);
            ofge.setOrderObj(orderEntity);
            ofge.setStockGoodsId(goodsDTO.getGoodsId());
            //假设得到了进价为20,设置利润
            float profit = (goodsDTO.getGoodsPrice()-20)*goodsDTO.getGoodsNum();
            ofge.setOrderProfit(profit);
            tempOrderProfit+=profit;

            goodslist.add(ofge);
        }
        //利润
        orderEntity.setGoodsProfit(tempOrderProfit);
        //保存
        orderManagementDao.save(orderEntity);
        return goodslist;
    }
    public void receiveOrder2(List<OrderForGoodsEntity> goodslist){
        for (OrderForGoodsEntity goods:goodslist) {
            goodsManagementDao.save(goods);
        }
    }
    @Override
    public void updateOrderType(String orderId) {
        orderManagementDao.updateOrderType(orderId,0+"");
    }


    public Map<String,String> findDate(String mouth){
        Map<String,String> list =  orderTrendsDao.findDate(mouth);

        return list;
    }
}
