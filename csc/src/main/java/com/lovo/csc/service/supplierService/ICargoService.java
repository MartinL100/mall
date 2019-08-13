package com.lovo.csc.service.supplierService;

import com.lovo.csc.entity.supplierEntity.CargoEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICargoService {
    public CargoEntity save(CargoEntity cargo);
    public List<CargoEntity> findBySupplyId(String supplyId);
    public CargoEntity findByCargoId(String cargoId);
    public void AJAXSupply(String supplierArray, String supplyId, HttpServletRequest request);
    public void AJAXCargo(String cargoId,int supplyNum, HttpServletRequest request);
}
