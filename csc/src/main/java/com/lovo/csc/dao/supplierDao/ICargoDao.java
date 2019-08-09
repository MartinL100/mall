package com.lovo.csc.dao.supplierDao;

import com.lovo.csc.entity.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICargoDao extends JpaRepository<CargoEntity,String> {

    //添加
    //根据cargoId查询该对象
    //
}
