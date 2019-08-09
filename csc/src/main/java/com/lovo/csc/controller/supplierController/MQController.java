package com.lovo.csc.controller.supplierController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.entity.*;
import com.lovo.csc.service.supplierService.ICargoService;
import com.lovo.csc.service.supplierService.ISupplierGoodsService;
import com.lovo.csc.service.supplierService.ISupplierService;
import com.lovo.csc.service.supplierService.ISupplyService;
import com.lovo.csc.vo.suppliervo.SupplierVO;
import com.lovo.csc.vo.suppliervo.SupplyTenderVo;
import com.lovo.csc.vo.suppliervo.SupplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.List;


@RestController
public class MQController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private ISupplyService supplyService;
    @Autowired
    private ISupplierGoodsService supplierGoodsService;
    @Autowired
    private ICargoService cargoService;

    @RequestMapping("AJAXSupplier")
    public String AJAXSupplier(SupplierVO vo){
        supplierService.AJAXSupplier(vo);
        return "{'errorMsg':fasle}";
    }

    @RequestMapping("AJAXSupplierGoods")
    @ResponseBody
    public String AJAXSupplierGoods(String codeArray, String supplierStatusArray){
        supplierGoodsService.AJAXSupplierGoods(codeArray,supplierStatusArray);
        return "{'errorMsg':fasle}";
    }

    @RequestMapping("AJAXSupply")
    @ResponseBody
    public String AJAXSupply(String supplierArray, String supplyId){
        cargoService.AJAXSupply(supplierArray,supplyId);
        return "{'errorMsg':fasle}";
    }

    @RequestMapping("AJAXCargo")
    public String AJAXCargo(String cargoId,int supplyNum){
        cargoService.AJAXCargo(cargoId,supplyNum);
        return "{'errorMsg':fasle}";
    }
    //服务器重启 从MQ中取出数据并保存到数据库
    @RequestMapping("getZCMQ.lovo")
    public void getZCMQ(){
        String MQJson="";
        SupplierEntity supplier=null;
        boolean lock=true;
        while (lock){
            TextMessage textMessage= (TextMessage)(jmsMessagingTemplate.getJmsTemplate()).
                    receive("ZCMQ");
            try {
                MQJson=textMessage.getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            //取完
            if (null==MQJson||"".equals(MQJson)){
                lock=false;
                return;
            }
            else{
                try {
                    supplier=  new ObjectMapper().readValue(MQJson,SupplierEntity.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                supplier.setSupplierTag("未审核");
                supplierService.save(supplier);
            }
        }
    }
    @JmsListener(destination = "ZCMQ")
    @RequestMapping("saveSupplierMQ")
    public String saveSupplier(String message) {
        if (null==message||"".equals(message)){
            return "{'errorMsg':false}";
        }
        SupplierEntity supplier=null;
        try {
            supplier=new ObjectMapper().readValue(message,SupplierEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != supplier) {
            supplier.setSupplierTag("未审核");
            supplierService.save(supplier);
        }
        return "{'errorMsg':true}";
    }
    //服务器重启 从MQ中取出数据并保存到数据库
    @RequestMapping("getSupplierGoodsAudit.lovo")
    public void getSupplierGoodsAudit(){
        String MQJson="";
        SupplierGoodsEntity supplierGoods=null;
        boolean lock=true;
        while (lock){
            TextMessage textMessage= (TextMessage)(jmsMessagingTemplate.getJmsTemplate()).
                    receive("supplierGoodsAudit");
            try {
                MQJson=textMessage.getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            //取完
            if (null==MQJson||"".equals(MQJson)){
                lock=false;
                return;
            }
            else{
                try {
                    supplierGoods=  new ObjectMapper().readValue(MQJson,SupplierGoodsEntity.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if("下架".equals(supplierGoods.getSupplierType()))
                    supplierGoods.setSupplierStatus("取消供应");
                supplierGoodsService.save(supplierGoods);
            }
        }
    }
    @JmsListener(destination = "supplierGoodsAudit")
    @RequestMapping("supplierGoodsAudit")
    public String supplierGoodsAudit(String message) {
        if (null==message||"".equals(message)){
            return "{'errorMsg':false}";
        }
        SupplierGoodsEntity supplierGoods=null;
        try {
            supplierGoods=new ObjectMapper().readValue(message,SupplierGoodsEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != supplierGoods) {
            if("下架".equals(supplierGoods.getSupplierType()))
            supplierGoods.setSupplierStatus("取消供应");
            supplierGoodsService.save(supplierGoods);
        }
        return "{'errorMsg':true}";
    }

    //服务器重启 从MQ中取出数据并保存到数据库
    @RequestMapping("getBuyMQ.lovo")
    public void getBuyMQ(){
        String MQJson="";
        List<SupplyVO> list=null;
        boolean lock=true;
        while (lock){
            TextMessage textMessage= (TextMessage)(jmsMessagingTemplate.getJmsTemplate()).
                    receive("buyMQ");
            try {
                MQJson=textMessage.getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            //取完
            if (null==MQJson||"".equals(MQJson)){
                lock=false;
                return;
            }
            else{
                try {
                    list=new ObjectMapper().readValue(MQJson,new TypeReference<List<SupplyVO>>(){});
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveSupply(list);
            }
        }
    }
    @JmsListener(destination = "buyMQ")
    @RequestMapping("buyMQ")
    public String buyMQ(String message) {
        if (null==message||"".equals(message)){
            return "{'errorMsg':false}";
        }
        List<SupplyVO> list=null;
        try {
            list=new ObjectMapper().readValue(message,new TypeReference<List<SupplyVO>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != list) {
            saveSupply(list);
        }
        return "{'errorMsg':true}";
    }
    public void saveSupply(List<SupplyVO> list){
        for (SupplyVO vo:list) {
            SupplyEntity supply=new SupplyEntity();
            IndentEntity indent=new IndentEntity();
            indent.setIndentId(vo.getIndentId());
            indent.setIndentDate(vo.getIndentDate());
            indent.setIndentStatus("未完成");
            supply.setIndentId(indent);
            supply.setIndentStatus("未投标");
            supply.setSupplyNum(vo.getSupplyNum());
            supply.setGoodsName(vo.getGoodsName());
            supply.setGoodsNorms(vo.getGoodsNorms());
            supply.setGoodsType(vo.getGoodsType());
            supply.setGoodsUnit(vo.getGoodsUnit());
            SupplyEntity s=supplyService.save(supply);
        }
    }
    //服务器重启 从MQ中取出数据并保存到数据库
    @RequestMapping("getOrderMQ.lovo")
    public void getOrderMQ(){
        String MQJson="";
        SupplyTenderVo vo=null;
        boolean lock=true;
        while (lock){
            TextMessage textMessage= (TextMessage)(jmsMessagingTemplate.getJmsTemplate()).
                    receive("orderMQ");
            try {
                MQJson=textMessage.getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            //取完
            if (null==MQJson||"".equals(MQJson)){
                lock=false;
                return;
            }
            else{
                try {
                    vo=  new ObjectMapper().readValue(MQJson,SupplyTenderVo.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CargoEntity cargo=cargoService.findByCargoId(vo.getCargoId());
                cargo.setSupplyPrice(vo.getPrice());
                cargo.getSupplyId().setIndentStatus("已采购");
                cargoService.save(cargo);
            }
        }
    }
    @JmsListener(destination = "orderMQ")
    @RequestMapping("orderMQ")
    public String orderMQ(String message) {
        if (null==message||"".equals(message)){
            return "{'errorMsg':false}";
        }
        SupplyTenderVo vo=null;
        try {
            vo=new ObjectMapper().readValue(message,SupplyTenderVo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != vo) {
            CargoEntity cargo=cargoService.findByCargoId(vo.getCargoId());
            cargo.setSupplyPrice(vo.getPrice());
            cargo.getSupplyId().setIndentStatus("已采购");
            cargoService.save(cargo);
        }
        return "{'errorMsg':true}";
    }
}
