package com.lovo.sscbfore.controller;

import cn.hutool.json.JSONUtil;
import com.lovo.sscbfore.service.ITypeService;
import com.lovo.sscbfore.user.entity2.TypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author che
 * @title: TypeController
 * @projectName mall
 * @description: 下拉框内容Controller
 * @date 2019/8/6  10:01
 */
@RestController
public class TypeController {

    @Autowired
    ITypeService typeService;

    @RequestMapping("req/type/{typename}")
    public String getTypeEntity(@PathVariable("typename") String typeName) {
        List<TypeEntity> typeEntityList = typeService.findTypesByTypeName(typeName);
        return JSONUtil.parse(typeEntityList).toString();
    }
}
