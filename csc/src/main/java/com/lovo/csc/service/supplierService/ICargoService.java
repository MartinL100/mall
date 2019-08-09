package com.lovo.csc.service.supplierService;

import com.lovo.csc.entity.CargoEntity;

import java.util.List;

public interface ICargoService {
    public CargoEntity save(CargoEntity cargo);
    public List<CargoEntity> findBySupplyId(String supplyId);
    public CargoEntity findByCargoId(String cargoId);
    public void AJAXSupply(String supplierArray, String supplyId);
    public void AJAXCargo(String cargoId,int supplyNum);
}
