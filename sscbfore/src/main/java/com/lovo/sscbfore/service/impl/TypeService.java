package com.lovo.sscbfore.service.impl;

import com.lovo.sscbfore.dao.ITypeDao;
import com.lovo.sscbfore.service.ITypeService;
import com.lovo.sscbfore.user.entity2.TypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author che
 * @title: TypeService
 * @projectName mall
 * @description: 下拉框业务接口实现类
 * @date 2019/8/6  9:58
 */
@Service
public class TypeService implements ITypeService {

    @Autowired
    ITypeDao typeDao;

    @Override
    public List<TypeEntity> findTypesByTypeName(String typeName) {
        return typeDao.findAllByTypeName(typeName);
    }

    @Override
    public void addType(TypeEntity type) {
        typeDao.save(type);
    }
}
