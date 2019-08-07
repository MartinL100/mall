package com.lovo.sscbfore;

import com.lovo.sscbfore.user.entity2.UserEntity;
import com.lovo.sscbfore.user.service.IUserService;
import com.lovo.sscbfore.user.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserTest {

    @Test
    public void aaa(){
        IUserService service=new UserServiceImpl();
       //UserEntity u= service.findUserByName("zy");
       // System.out.printf(u.getTrueName());
    }
}
