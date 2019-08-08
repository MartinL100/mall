package com.lovo.sscbfore.dao;

import com.lovo.sscbfore.user.entity2.UserEntity;
import com.lovo.sscbfore.user.entity2.UserInfoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author che
 * @title: IUserMessageDao
 * @projectName mall
 * @description: 用户消息持久化接口
 * @date 2019/8/5  14:06
 */
public interface IUserMessageDao extends PagingAndSortingRepository<UserInfoEntity, String> {


    /**
     * 查询用户的所有消息
     *
     * @param userName 用户名称
     * @return 用户消息集合（按消息时间倒序排列）
     */
    List<UserInfoEntity> findAllByUserInfoIsOrderByMessageDateDesc(UserEntity userName);


    @Query(value = "from UserInfoEntity  as i where i.userInfo = ?1 order by i.messageDate desc")
    List<UserInfoEntity> findByUserNameAndPage(UserEntity userName, @PageableDefault Pageable pageable);

    /**
     * 查询用户所有消息数量
     *
     * @param userName 用户名
     * @return 用户消息数量
     */
    int countByUserInfo(UserEntity userName);

    /**
     * 按消息状态查询用户消息数量
     *
     * @param user          用户消息
     * @param messageStatus 消息状态
     * @return 对应消息状态的数量
     */
    int countByUserInfoAndAndMessageStatus(UserEntity user, int messageStatus);


    /**
     * 按用户名修改消息状态
     *
     * @param user 用户名
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "update  sys_info  set sys_info.message_status = 1 where  sys_info.user_name = ?1", nativeQuery = true)
    void updateMessageStatus(String user);
}
