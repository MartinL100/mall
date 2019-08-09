package com.lovo.sscafter.customerRetention.dao;


import com.lovo.sscafter.customerRetention.Entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<UserEntity, Integer> {

    @Query(" from UserEntity where userName1=?1 and password1=?2")
    public UserEntity findByUserName1AAndPassword1(String userName1,String password1);

    public UserEntity findByUserName1(String userName);
}
