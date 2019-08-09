package com.lovo.csc.dao.clientdao;


import com.lovo.csc.entity.SysFrozenOrUnfrozenAccountsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//账号冻结与解冻持久层
public interface IFrozenOrUnfrozenAccountsDao extends CrudRepository<SysFrozenOrUnfrozenAccountsEntity,String> {

    @Query(value = "  from SysFrozenOrUnfrozenAccountsEntity  where frozenOrUnfrozenAccountsMessageId=?1 ")
    public SysFrozenOrUnfrozenAccountsEntity getFrozenOrUnfrozenAccountsMessageById(String id);


}
