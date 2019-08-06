package com.lovo.sscbfore.service;

import com.lovo.sscbfore.user.entity.TypeEntity;

import java.util.List;

/**
 * @author che
 * @title: ITypeService
 * @projectName mall
 * @description: 下拉框持久接口
 * @date 2019/8/5  15:32
 */
public interface ITypeService {

    /**
     * 按下拉名称查找所有下拉
     *
     * @param typeName 下拉名称
     * @return 下拉集合
     */
    List<TypeEntity> findTypesByTypeName(String typeName);

}
