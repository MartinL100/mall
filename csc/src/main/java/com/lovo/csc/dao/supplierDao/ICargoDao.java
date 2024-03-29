package com.lovo.csc.dao.supplierDao;

import com.lovo.csc.entity.supplierEntity.CargoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICargoDao extends CrudRepository<CargoEntity,String> {
    /**
     * 保存修改
     * @param cargo 供货订单中间表
     */
    public CargoEntity save(CargoEntity cargo);

    @Query("from  CargoEntity where supplyId.supplyId=?1")
    public List<CargoEntity> findBySupplyId(String supplyId);
    public CargoEntity findByCargoId(String cargoId);
}
