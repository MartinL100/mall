package com.lovo.csc.service.supplierService.impl;

import com.lovo.csc.dao.supplierDao.ICargoDao;
import com.lovo.csc.dao.supplierDao.ISupplierDao;
import com.lovo.csc.dao.supplierDao.ISupplyDao;
import com.lovo.csc.entity.CargoEntity;
import com.lovo.csc.entity.SupplierEntity;
import com.lovo.csc.entity.SupplyEntity;
import com.lovo.csc.service.supplierService.ICargoService;
import com.lovo.csc.util.promotionutil.DateFormat;
import com.lovo.csc.vo.suppliervo.SupplyVO;
import com.lovo.csc.vo.suppliervo.TenderVO;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service(value = "cargoService")
public class CargoServiceImpl implements ICargoService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private ActiveMQQueue FBMQ;
    @Autowired
    private ActiveMQQueue priceMQ;
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
    public void AJAXSupply(String supplierArray, String supplyId) {
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
            CargoEntity cargo=cargoDao.save(cargo1);
            SupplyVO vo=new SupplyVO(cargo.getSupplyId().getIndentId().getIndentId(),cargo.getSupplyId().getIndentId().getIndentDate(),
                    cargo.getCargoId(),cargo.getSupplyId().getGoodsName(),cargo.getSupplyId().getGoodsNorms(),
                    cargo.getSupplyId().getGoodsType(),cargo.getSupplyId().getGoodsUnit(),cargo.getSupplyId().getSupplyNum(),
                    str);
            list.add(vo);
        }
        jmsMessagingTemplate.convertAndSend(FBMQ,list);
    }
    public void AJAXCargo(String cargoId,int supplyNum){
        CargoEntity cargo=cargoDao.findByCargoId(cargoId);
        cargo.getSupplyId().setSupplyNum(supplyNum);
        cargo.getSupplyId().setIndentStatus("已采购");
        cargoDao.save(cargo);
        TenderVO vo=new TenderVO(cargo.getSupplyId().getIndentId().getIndentId(),cargoId,supplyNum);
        jmsMessagingTemplate.convertAndSend(FBMQ,vo);
    }
}
