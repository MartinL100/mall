package com.lovo.csc.dao.depositDao;

import com.lovo.csc.entity.depositEntity.DepositEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IDepositDao extends CrudRepository<DepositEntity,String> {


    @Query("from DepositEntity  where  userName=?1")
    public DepositEntity findDepositByUserName(String userName);


}
