package com.lovo.csc.controller.supplierController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.entity.*;
import com.lovo.csc.entity.supplierEntity.CargoEntity;
import com.lovo.csc.entity.supplierEntity.IndentEntity;
import com.lovo.csc.entity.supplierEntity.SupplierGoodsEntity;
import com.lovo.csc.entity.supplierEntity.SupplyEntity;
import com.lovo.csc.service.supplierService.*;
import com.lovo.csc.vo.suppliervo.SupplierGoodsDTO;
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
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private IIndentService indentService;

    @RequestMapping("AJAXSupplier")
    public String AJAXSupplier(SupplierVO vo,HttpServletRequest request){
        supplierService.AJAXSupplier(vo,request);
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
    public String AJAXSupply(String supplierArray, String supplyId, HttpServletRequest request){

        cargoService.AJAXSupply(supplierArray,supplyId,request);
        return "{'errorMsg':fasle}";
    }

    @RequestMapping("AJAXCargo")
    public String AJAXCargo(String cargoId,int supplyNum, HttpServletRequest request){
        cargoService.AJAXCargo(cargoId,supplyNum,request);
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
                supplier.setSupplierTag("供应商未审核");
                supplierService.save(supplier);
            }
        }
    }
    @JmsListener(destination = "ZCMQ1")
//    @RequestMapping("saveSupplierMQ")
    public void saveSupplier(String message) {
        if (null==message||"".equals(message)){
//            return "{'errorMsg':false}";
            return;
        }
        SupplierEntity supplier=null;
        try {
            supplier=new ObjectMapper().readValue(message,SupplierEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != supplier) {
            supplier.setSupplierTag("供应商未审核");
            supplierService.save(supplier);
        }
//        return "{'errorMsg':true}";
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
                supplierGoods.getSupplierId().setSupplierTag("供应商品未审核");
                supplierService.save(supplierGoods.getSupplierId());
                if("下架".equals(supplierGoods.getSupplierType()))
                    supplierGoods.setSupplierStatus("取消供应");
                supplierGoodsService.save(supplierGoods);
            }
        }
    }
    @JmsListener(destination = "AddSupplierGoodsAudit1")
//    @RequestMapping("supplierGoodsAudit")
    public void supplierGoodsAudit(String message) {
        if (null==message||"".equals(message)){
            return;
        }
        SupplierGoodsDTO vo=null;
        try {
            vo=new ObjectMapper().readValue(message,SupplierGoodsDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != vo) {
            if("下架".equals(vo.getSupplierType()))
                vo.setSupplierStatus("取消供应");
            SupplierGoodsEntity s=new SupplierGoodsEntity();
            s.setCodeId(vo.getCodeId());
            s.setSupplierStatus(vo.getSupplierStatus());
            s.setGoodsName(vo.getGoodsName());
            s.setGoodsNorms(vo.getGoodsNorms());
            s.setGoodsType(vo.getGoodsType());
            s.setGoodsUnit(vo.getGoodsUnit());
            s.setSupplierType(vo.getSupplierType());
            SupplierEntity supplier=supplierService.findBySupplierId(vo.getSupplierId());
            supplier.setSupplierTag("供应商品未审核");
            supplierService.save(supplier);
            s.setSupplierId(supplier);
            supplierGoodsService.save(s);
        }
//        return "{'errorMsg':true}";
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
    @JmsListener(destination = "buyMQ1")
//    @RequestMapping("buyMQ")
    public void buyMQ(String message) {
        if (null==message||"".equals(message)){
            return;
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
//        return "{'errorMsg':true}";
    }
    public void saveSupply(List<SupplyVO> list){
        for (SupplyVO vo:list) {
            SupplyEntity supply=new SupplyEntity();
            IndentEntity indent=new IndentEntity();
            indent.setIndentId(vo.getIndentId());
            indent.setIndentDate(vo.getIndentDate());
            indent.setIndentStatus("未结算");
            supply.setIndentId(indent);
            supply.setIndentStatus("未投标");
            supply.setSupplyNum(vo.getSupplyNum());
            supply.setGoodsName(vo.getGoodsName());
            supply.setGoodsNorms(vo.getGoodsNorms());
            supply.setGoodsType(vo.getGoodsType());
            supply.setGoodsUnit(vo.getGoodsUnit());
            indentService.save(indent);
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
                cargo.setSupplyPrice(vo.getSupplyPrice());
                cargo.getSupplyId().setIndentStatus("已采购");
                cargoService.save(cargo);
            }
        }
    }
    @JmsListener(destination = "orderMQ1")
//    @RequestMapping("orderMQ")
    public void orderMQ(String message) {
        if (null==message||"".equals(message)){
            return ;
        }
        SupplyTenderVo vo=null;
        try {
            vo=new ObjectMapper().readValue(message,SupplyTenderVo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != vo) {
            CargoEntity cargo=cargoService.findByCargoId(vo.getCargoId());
            cargo.setSupplyPrice(vo.getSupplyPrice());
            cargo.getSupplyId().setIndentStatus("未采购");
            supplyService.save(cargo.getSupplyId());
            cargo.setCargoStatus("未采购");
            cargoService.save(cargo);
        }
//        return "{'errorMsg':true}";
    }
}
