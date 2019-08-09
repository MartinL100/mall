package com.lovo.psc.groupOne.service.impl;

import com.lovo.psc.groupOne.dao.IIndentDao;
import com.lovo.psc.entity.IndentEntity;
import com.lovo.psc.groupOne.service.IIndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="indentService")
public class IndentServiceImpl implements IIndentService {
    @Autowired
    private IIndentDao indentDao;

    @Override
    public void saveIndent(IndentEntity indent) {
        indentDao.save(indent);
    }
}
