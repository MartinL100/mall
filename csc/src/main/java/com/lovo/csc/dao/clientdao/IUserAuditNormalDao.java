package com.lovo.csc.dao.clientdao;


import com.lovo.csc.entity.SysFrozenOrUnfrozenAccountsEntity;
import com.lovo.csc.entity.SysUserAuditInformationEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IUserAuditNormalDao {


    /**
     * 动态查询页面显示用户注册数据
     * @param userState 用户账号状态
     * @param startTime 查询开始时间
     * @param endTime   查询结束时间
     * @param page      分页条件
     * @return  用户注册页面显示List
     */
    public List<SysUserAuditInformationEntity>
    DynamicQueryAuditInformation(String userState, String startTime, String endTime, Pageable page);


    /**
     * 动态查询页面显示用户注册数据总数
     * @param userState 用户账号状态
     * @param startTime 查询开始时间
     * @param endTime   查询结束时间
     * @return 注册数据总数
     */
    public long
    DynamicQueryAuditInformationCount(String userState, String startTime, String endTime);


    //动态查询用户冻结与解冻信息
    /**
     * 动态查询用户冻结与解冻信息
     * @param auditState 审核进行状态 审核中，审核完毕
     * @param startTime  查询开始时间
     * @param endTime    查询结束时间
     * @param page       分页条件
     * @return
     */
    public List<SysFrozenOrUnfrozenAccountsEntity> DynamicQuerySysFrozenOrUnfrozenAccountsEntity
    (String auditState,String auditType, String startTime, String endTime, Pageable page);


    /**
     * 动态查询用户冻结与解冻信息总数量
     * @param auditState 审核进行状态 审核中，审核完毕
     * @param startTime  查询开始时间
     * @param endTime    查询结束时间
     * @return
     */
    public long DynamicQuerySysFrozenOrUnfrozenAccountsEntityCount
    (String auditState,String auditType, String startTime, String endTime);



}
