package com.lovo.csc.dao.clientdao;



import com.lovo.csc.entity.SysUserAuditInformationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//用户注册审核持久层
public interface IUserRegistraTionAuditDao extends CrudRepository<SysUserAuditInformationEntity,String> {


    //根据用户名查找用户
    /**
     * 根据用户名查找用户
     * @param userName 用户名
     * @return
     */
    public SysUserAuditInformationEntity findByUserName(String userName);

    //页面初始化查询所有审核未通过的
    /**
     * 页面初始化查询所有非审核通过的数据
     * @param page 分页条件
     * @return
     */
    @Query(value=" from SysUserAuditInformationEntity where userState <> '审核通过' and  userState <> '已冻结' ")
    public List<SysUserAuditInformationEntity> PageInitList(Pageable page);


    //得到初始化数据总数
    /**
     * 得到初始化数据总数
     * @return 初始化数据总数
     */ @Query(value=" select count(userAuditInformationId) from SysUserAuditInformationEntity where userState <> '审核通过' and  userState <> '已冻结' ")
    public long getPageInitCount();


}
