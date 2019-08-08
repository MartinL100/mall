package com.lovo.sscafter.goodsStock.service.impl;

import com.lovo.sscafter.goodsStock.dao.IIndentCrudDao;
import com.lovo.sscafter.goodsStock.entity.IndentEntity;
import com.lovo.sscafter.goodsStock.service.IIndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("indentService")
public class IndentServiceimpl implements IIndentService {
    @Autowired
    private IIndentCrudDao indentCrudDao;
    @Override
    public void saveIndent(IndentEntity ie) {
        indentCrudDao.save(ie);
    }
}
