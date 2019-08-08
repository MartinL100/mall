package com.lovo.csc.service.closeService.impl;

import com.lovo.csc.dao.closeDao.IOrderDao;
import com.lovo.csc.dao.closeDao.IOrderQueryDao;
import com.lovo.csc.entity.OrderEntity;
import com.lovo.csc.service.closeService.IOrderService;
import com.lovo.csc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value="orderService")
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IOrderQueryDao orderQueryDao;
    @Override
    public void saveOrder(OrderEntity order) {
        orderDao.save(order);
    }

    @Override
    public OrderEntity findbyId(String orderId) {
        return orderDao.findbyiD(orderId);
    }

    @Override
    public List<OrderEntity> findBySome(int pageNumber, int pageSize,String userName, String orderNum, String startTime, String endTime) {
        String hql="from OrderEntity where 1=1";
        if(!StringUtil.blString(userName)){
            hql+=" and userName like '%"+userName+"%'";
        }
        if(!StringUtil.blString(orderNum)){
            hql+=" and orderNum like '%"+orderNum+"%'";
        }if(!StringUtil.blString(startTime)){
            hql+=" and orderDate>'"+startTime+"'";
        }if(!StringUtil.blString(endTime)){
            hql+=" and orderDate<'"+endTime+"'";
        }
//        System.out.println(hql);

        return orderQueryDao.findBySome(hql,pageNumber,pageSize);
    }

    @Override
    public long count(String userName, String orderNum, String startTime, String endTime) {
        String hql="select count(orderNum) from OrderEntity where 1=1";
        if(!StringUtil.blString(userName)){
            hql+=" and userName like '%"+userName+"%'";
        }
        if(!StringUtil.blString(orderNum)){
            hql+=" and orderNum like '%"+orderNum+"%'";
        }if(!StringUtil.blString(startTime)){
            hql+=" and orderDate>'"+startTime+"'";
        }if(!StringUtil.blString(endTime)){
            hql+=" and orderDate<'"+endTime+"'";
        }
        return orderQueryDao.count(hql);
    }

    @Override
    public List<OrderEntity> findByTag(int pageNumber, int pageSize, int tag) {
           String hql="from OrderEntity where tag="+tag;
         return orderQueryDao.findBySome(hql,pageNumber,pageSize);
    }

    @Override
    public long count(int tag) {
        String hql="select count(orderNum) from OrderEntity where tag="+tag;
        return orderQueryDao.count(hql);
    }
}
