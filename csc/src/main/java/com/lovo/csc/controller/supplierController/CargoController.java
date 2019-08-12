package com.lovo.csc.controller.supplierController;

import com.lovo.csc.entity.supplierEntity.CargoEntity;
import com.lovo.csc.service.supplierService.ICargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CargoController {
    @Autowired
    private ICargoService cargoService;

    @RequestMapping("findCargo.lovo")
//    @ResponseBody
    public  List<CargoEntity> findCargo(String supplyId){
        List<CargoEntity> list=cargoService.findBySupplyId(supplyId);
        return  list;
    }
}
