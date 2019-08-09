package com.lovo.csc.controller.salesReturnController;


import com.lovo.csc.entity.salesReturnEntity.GoodsEntity;
import com.lovo.csc.entity.salesReturnEntity.OrderEntity;
import com.lovo.csc.entity.salesReturnEntity.OrderGoodsEntity;
import com.lovo.csc.entity.salesReturnEntity.ScopeOrderEntity;
import com.lovo.csc.service.salesReturnService.IOrderGoodsService;
import com.lovo.csc.service.salesReturnService.IScopeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PageController {
    @Autowired
   private IOrderGoodsService orderGoodsService;
    @Autowired
    private IScopeOrderService scopeOrderService;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    /**
     *
     * @param page 当前页
     * @param rows 每页显示的行数
     * @param orderId 条件
     * @return
     */
    @RequestMapping("page1.lovo")
    public Map<String,Object> page(int page,int rows,String goodsState, String orderId, String orderName){
        Map<String, Object> map = new HashMap<>();
       List<OrderGoodsEntity>list=orderGoodsService.findByOrderId(goodsState,orderId,orderName,page,rows);
        for(int i=0;i<list.size();i++){
            list.get(i);
        }

       map.put("rows",list);
       map.put("page",page);
       long total=orderGoodsService.getPageListOrderGoodsEntityCount(orderId,orderName,goodsState);
       map.put("total",total);

        return  map;
    }

    /**
     *
     * @param page 当前页
     * @param rows 每页显示的行数
     * @param orderId 条件
     * @return
     */
    @RequestMapping("pageOrder.lovo")
    public Map<String,Object> pageOrder(int page,int rows,String orderState, String orderId, String supplierName){
        Map<String,Object> map=new HashMap<>();
        List<ScopeOrderEntity> lists=scopeOrderService.findByScopeOrder(orderState,orderId,supplierName,page,rows);
        for(int i=0;i<lists.size();i++){
            lists.get(i);
        }
//        List<OrderEntity>list=scopeOrderService.findByOrder()
        map.put("rows",lists);
        map.put("page",page);
        long total=scopeOrderService.getPageListScopeOrderCount(orderState,orderId,supplierName);
        map.put("total",total);

        return  map;
    }



    @RequestMapping("pageScopeOrder.lovo")
    public Map<String,Object> pageOrder(int page,int rows,String Id){
        Map<String,Object> map=new HashMap<>();
        List<OrderEntity> lists=scopeOrderService.findByOrder(Id,page,rows);
        for(int i=0;i<lists.size();i++){
            lists.get(i);
        }
        map.put("rows",lists);
        map.put("page",page);
        long total=scopeOrderService.getPageListOrderCount(Id);
        map.put("total",total);

        return  map;
    }



    @RequestMapping("pageGoods.lovo")
    public Map<String,Object> pageGoods(int page,int rows,String goodsId){
        Map<String,Object> map=new HashMap<>();
        List<GoodsEntity> lists=orderGoodsService.findByGoods(goodsId,page,rows);
        for(int i=0;i<lists.size();i++){
            lists.get(i);
        }
        map.put("rows",lists);
        map.put("page",page);
        long total=orderGoodsService.getPageListGoodsCount(goodsId);
        map.put("total",total);

        return  map;
    }
}
