package com.lovo.sscbfore.dao;

import com.lovo.sscbfore.user.entity.TypeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author che
 * @title: ITypeDao
 * @projectName mall
 * @description: 下拉框持久接口
 * @date 2019/8/5  15:30
 */
public interface ITypeDao extends CrudRepository<TypeEntity, String> {

    /**
     * 按下拉名称查找所有下拉
     *
     * @param TypeName 下拉名称
     * @return 下拉集合
     */
    List<TypeEntity> findAllByTypeName(String TypeName);
}
