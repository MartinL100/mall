package com.lovo.csc.dao.clientdao;


import com.lovo.csc.entity.SysFrozenOrUnfrozenAccountsEntity;
import com.lovo.csc.entity.SysUserAuditInformationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//账号冻结与解冻持久层
public interface IFrozenOrUnfrozenAccountsDao extends CrudRepository<SysFrozenOrUnfrozenAccountsEntity,String> {
    /**
     * 得到页面初始化数据
     * @param page
     * @return
     */
    @Query(value=" from SysFrozenOrUnfrozenAccountsEntity where auditState = '未审核'  ")
    public List<SysFrozenOrUnfrozenAccountsEntity> FrozenOrUnfrozenAccountsPageInitList(Pageable page);

    /**
     * 得到页面初始化数据总数
     * @return
     */
    @Query(value=" select count(FrozenOrUnfrozenAccountsMessageId) from SysFrozenOrUnfrozenAccountsEntity where auditState = '未审核' ")
    public long getFrozenOrUnfrozenAccountsPageInitCount();


}
