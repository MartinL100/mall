package com.lovo.psc.groupOne.controller;

import com.lovo.psc.entity.SupplierEntity;
import com.lovo.psc.entity.SupplyCenterEntity;
import com.lovo.psc.groupOne.service.ISupplyCenterService;
import com.lovo.psc.groupOne.vo.InfoVo;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SupplyCenterController {
    @Autowired
    private ISupplyCenterService supplyCenterService;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    @RequestMapping("findCenter.psc")
    @ResponseBody
    public Map<String,Object> find(int page, int rows, HttpServletRequest request){
          String supplierId =((SupplierEntity)request.getSession().getAttribute("supplier")).getSupplierId();
        List<SupplyCenterEntity> list =  supplyCenterService.findBySupplierIdAnAndBjTag(supplierId,"未报价");
//        for(SupplyCenterEntity s :list){
//            s.getSupplier().setSupplyCenterList(null);
//            s.getIndentEntity().setSupplyCenterList(null);
//
//        }
        Map<String,Object> map=new HashMap<>();
        map.put("rows",list);
        map.put("page",page);
        long total=list.size();
        map.put("total",total);
        return map;
    }

    @RequestMapping("updatePrice.psc")
    @ResponseBody
    public String updatePrice(SupplyCenterEntity supplyCenter){
        String supplyId =  supplyCenter.getSupplyId();
        Float price = supplyCenter.getSupplyPrice();
        SupplyCenterEntity sce = supplyCenterService.findBySupplyId(supplyId);
        sce.setSupplyPrice(price);
        sce.setBjTag("已报价");
        supplyCenterService.updatePrice(sce);

        ActiveMQQueue queue = new ActiveMQQueue("orderMQ");

        InfoVo infoVo = new InfoVo();
        infoVo.setCargoId(sce.getCargoId());
        infoVo.setSupplyPrice(sce.getSupplyPrice());
        jmsMessagingTemplate.convertAndSend(queue,infoVo);

        return  "{'info':'报价成功'}";
    }
}
