package com.lovo.csc.dao.depositDao;

import com.lovo.csc.entity.depositEntity.DepositInfoEntity;
import com.lovo.csc.vo.DepositVo;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IDepositInfoDao extends CrudRepository<DepositInfoEntity, String> {

    //查询用户最近6个月的预存款消费金额
    @Query(" select  new  com.lovo.csc.vo.DepositVo(makeTime,sum(makeMoney)) from DepositInfoEntity where deposit.userName=?1" +
            " GROUP BY  makeTime   ORDER BY makeTime DESC")
    public List<DepositVo> getAllByUserName(String userName, Pageable pageable);
}
