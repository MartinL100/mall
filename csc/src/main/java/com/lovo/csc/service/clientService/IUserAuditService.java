package com.lovo.csc.service.clientService;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.lovo.csc.entity.SysFrozenOrUnfrozenAccountsEntity;
import com.lovo.csc.entity.SysUserAuditInformationEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

//用户审核业务层

public interface IUserAuditService {
    //根据用户名得到用户审核信息
    public SysUserAuditInformationEntity findSysUserAuditInformationEntityByName(String userName);
     //用户审核页面初始化数据
    public List<SysUserAuditInformationEntity> PageInitList(int page,int rows);
    //用户审核页面初始化数据总数
    public long getPageInitCount();

    //用户状态保存
    public String savaUserAuditMessage
    (SysUserAuditInformationEntity userAuditInformationEntity);

    //用户状态修改
    public String updateUserAuditMessage
    (SysUserAuditInformationEntity userAuditInformationEntity, String auditPerson) throws JsonProcessingException;

    //保存冻结解冻信息
    public String savaFrozenOrUnfrozenAccountsEntity
    (SysFrozenOrUnfrozenAccountsEntity SysFrozenOrUnfrozenAccountsEntity);

    //返回用户折扣等级
    public double getUserDiscount(String userName);

    //根据用户名得到用户注册考勤信息
    public SysUserAuditInformationEntity getSysUserAuditInformationEntity(String userName);

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
    (String auditState, String startTime,String auditType, String endTime, Pageable page);



    /**
     * 动态查询用户冻结与解冻信息总数量
     * @param auditState 审核进行状态 审核中，审核完毕
     * @param startTime  查询开始时间
     * @param endTime    查询结束时间
     * @return
     */
    public long DynamicQuerySysFrozenOrUnfrozenAccountsEntityCount
    (String auditState,String auditType, String startTime, String endTime);

    //根据id查询用户冻结解冻信息
    public SysFrozenOrUnfrozenAccountsEntity findSysFrozenOrUnfrozenAccountsEntityById(String id);




    //循环更新用户状态
    public String CycleUpdateUserAuditInformation(SysFrozenOrUnfrozenAccountsEntity obj,String auditPerson);
}
