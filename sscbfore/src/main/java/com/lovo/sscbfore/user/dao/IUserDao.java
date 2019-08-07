package com.lovo.sscbfore.user.dao;

import com.lovo.sscbfore.user.entity2.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface IUserDao extends CrudRepository<UserEntity,String> {

    @Query(" from UserEntity where userName=?1 and password=?2 ")
    public UserEntity userLogin(String userName,String password);

    @Query("from UserEntity where userName=?1 ")
    public UserEntity findUserByName(String userName);

    @Modifying
    @Transactional
    @Query("update UserEntity set userState=?2 where userName=?1 ")
    public void updateUserState(String userName,String userState);

}
