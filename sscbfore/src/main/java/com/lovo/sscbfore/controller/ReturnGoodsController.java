package com.lovo.sscbfore.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.lovo.sscbfore.entity.ReturnGoodsVo;
import com.lovo.sscbfore.entity.TableDateEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author che
 * @title: ReturnGoodsController
 * @projectName mall
 * @description: 用户退货controller
 * @date 2019/8/3  14:08
 */
@RestController
public class ReturnGoodsController {

    @RequestMapping("returngoods/req")
    public String orderGoodsList(HttpServletRequest request) {

        Map<String, String[]> map = request.getParameterMap();
        int page = Integer.parseInt(map.get("page")[0]);
        int limit = Integer.parseInt(map.get("limit")[0]);

        List<ReturnGoodsVo> goodsVoList = new ArrayList<>();
        for (int i = (page - 1) * limit; i < page * limit; i++) {
            ReturnGoodsVo goodsVo = new ReturnGoodsVo();
            goodsVo.setGoodsId(i);
            goodsVo.setGoodsName("水果" + i);
            goodsVo.setGoodsNum(100);
            goodsVo.setGoodsPrice("1");
            goodsVoList.add(goodsVo);
        }

        TableDateEntity tableDate = new TableDateEntity<ReturnGoodsVo>();
        tableDate.setCode(0);
        tableDate.setData(goodsVoList);
        tableDate.setCount(20);
        tableDate.setMsg("");

        JSON json = JSONUtil.parse(tableDate);
        System.out.println(json.toString());
        return json.toString();

    }
}
