package com.lovo.psc.groupOne.service.impl;

import com.lovo.psc.groupOne.dao1.IIndentDao1;
import com.lovo.psc.entity.IndentEntity;
import com.lovo.psc.groupOne.service.IIndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="indentService")
public class IndentServiceImpl implements IIndentService {
    @Autowired
    private IIndentDao1 indentDao1;

    @Override
    public void saveIndent(IndentEntity indent) {
        indentDao1.save(indent);
    }
}
