package com.lovo.sscbfore.dao;

import com.lovo.sscbfore.user.entity2.UserInfoEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author che
 * @title: IUserMessageDao
 * @projectName mall
 * @description: 用户消息持久化接口
 * @date 2019/8/5  14:06
 */
public interface IUserMessageDao extends CrudRepository<UserInfoEntity, String> {


}
