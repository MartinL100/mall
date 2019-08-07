package com.lovo.csc.service.depositService;

import com.lovo.csc.entity.depositEntity.DepositInfoEntity;
import com.lovo.csc.vo.DepositVo;

import java.util.List;

public interface IDepositInfoService {
    /**
     * 添加预存款明细
     * @param depositInfo
     */
    public void addDepositInfo(DepositInfoEntity depositInfo);

    /**
     * 查询最近6个月的消费情况
     * @param userName 用户名
     * @return
     */
    public List<DepositVo> getAllByUserName(String userName);
}
