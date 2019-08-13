package com.lovo.csc.service.supplierService.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.csc.dao.supplierDao.ICargoDao;
import com.lovo.csc.dao.supplierDao.ISupplierDao;
import com.lovo.csc.dao.supplierDao.ISupplyDao;
import com.lovo.csc.entity.supplierEntity.AuditEntity;
import com.lovo.csc.entity.supplierEntity.CargoEntity;
import com.lovo.csc.entity.SupplierEntity;
import com.lovo.csc.entity.supplierEntity.SupplyEntity;
import com.lovo.csc.service.supplierService.ICargoService;
import com.lovo.csc.util.promotionutil.DateFormat;
import com.lovo.csc.vo.suppliervo.SupplyVO;
import com.lovo.csc.vo.suppliervo.TenderVO;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@Service(value = "cargoService")
public class CargoServiceImpl implements ICargoService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
//    @Autowired
//    private ActiveMQQueue FBMQ;
//    @Autowired
//    private ActiveMQQueue priceMQ;
    @Autowired
    private ICargoDao cargoDao;
    @Autowired
    private ISupplierDao supplierDao;
    @Autowired
    private ISupplyDao supplyDao;
    @Override
    public CargoEntity save(CargoEntity cargo) {
        return cargoDao.save(cargo);
    }

    @Override
    public List<CargoEntity> findBySupplyId(String supplyId) {
        return cargoDao.findBySupplyId(supplyId);
    }

    @Override
    public CargoEntity findByCargoId(String cargoId) {
        return cargoDao.findByCargoId(cargoId);
    }

    @Override
    public void AJAXSupply(String supplierArray, String supplyId, HttpServletRequest request) {
        String[] array=supplierArray.split(",");
        List<SupplyVO> list=new ArrayList<>();
        for (String str:array) {
            SupplierEntity supplier=supplierDao.findBySupplierId(str);
            CargoEntity cargo1=new CargoEntity();
            cargo1.setSupplierId(supplier);
            SupplyEntity supply=supplyDao.findBySupplyId(supplyId);
            supply.setIndentStatus("已投标");
            cargo1.setSupplyId(supply);
            cargo1.setTenderDate(new DateFormat().getNow());
            AuditEntity audit=(AuditEntity) request.getSession().getAttribute("auditObj");
            cargo1.setTenderPeople(audit.getAuditPeople());
            cargo1.setCargoStatus("未报价");
            CargoEntity cargo=cargoDao.save(cargo1);
            SupplyVO vo=new SupplyVO(cargo.getSupplyId().getIndentId().getIndentId(),cargo.getSupplyId().getIndentId().getIndentDate(),
                    cargo.getCargoId(),cargo.getSupplyId().getGoodsName(),cargo.getSupplyId().getGoodsNorms(),
                    cargo.getSupplyId().getGoodsType(),cargo.getSupplyId().getGoodsUnit(),cargo.getSupplyId().getSupplyNum(),
                    str);
            list.add(vo);
        }
        ActiveMQQueue queue=new ActiveMQQueue("FBMQ1");
        ObjectMapper mapper=new ObjectMapper();
        try {
            String v=mapper.writeValueAsString(list);
            jmsMessagingTemplate.convertAndSend(queue,v);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public void AJAXCargo(String cargoId,int supplyNum, HttpServletRequest request){
        CargoEntity cargo=cargoDao.findByCargoId(cargoId);
        cargo.getSupplyId().setSupplyNum(supplyNum);
        cargo.getSupplyId().setIndentStatus("已采购");
        cargo.setPurchaseDate(new DateFormat().getNow());
        AuditEntity audit=(AuditEntity) request.getSession().getAttribute("auditObj");
        cargo.setPurchasePeople(audit.getAuditPeople());
        cargo.setCargoStatus("已采购");
        cargoDao.save(cargo);
        TenderVO vo=new TenderVO(cargo.getSupplyId().getIndentId().getIndentId(),cargoId,supplyNum);
        ActiveMQQueue queue=new ActiveMQQueue("priceMQ1");
        ObjectMapper mapper=new ObjectMapper();
        try {
            String v=mapper.writeValueAsString(vo);
            jmsMessagingTemplate.convertAndSend(queue,v);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
