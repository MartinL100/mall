package com.lovo.csc.controller.salesReturnController;

import com.lovo.csc.entity.salesReturnEntity.OrderEntity;
import com.lovo.csc.entity.salesReturnEntity.ScopeOrderEntity;
import com.lovo.csc.service.salesReturnService.IScopeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ScopeOrderController {
    @Autowired
    private IScopeOrderService scopeOrderService;

    //修改保存供货商审核信息
    @RequestMapping("updateScopeOrder.lovo")
    public String updateScopeOrder(ScopeOrderEntity scopeOrderEntity, HttpServletRequest requst){
       String orderState="";
        String id=null;
        id=scopeOrderService.updateScopeOrder(scopeOrderEntity,orderState);
        if (null!=id&&!"".equals(id)){
            return "{'successMsg':'操作成功'}";
        }
        return "{'errorMsg':'操作失败'}";
    }

    //查询
    @RequestMapping("findByScopeOrder.lovo")
    public List<OrderEntity> findByScopeOrder(String Id){
        List<OrderEntity> list=scopeOrderService.findByScopeOrder(Id);

        return list;
    }

}
