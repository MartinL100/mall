package com.lovo.sscbfore.user.dao;

import com.lovo.sscbfore.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserEntity,String> {

    @Query(" from UserEntity where userName=?1 and password=?2 ")
    public UserEntity userLogin(String userName,String password);

    @Query("from UserEntity where userName=?1 ")
    public UserEntity findUserByName(String userName);

    @Modifying
    @Query("update UserEntity set userState=?1 where userName=?2 ")
    public void updateUserState(String userName,String userState);

}
