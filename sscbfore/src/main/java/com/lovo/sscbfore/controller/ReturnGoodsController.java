package com.lovo.sscbfore.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.lovo.sscbfore.entity.ReturnGoodsVo;
import com.lovo.sscbfore.entity.TableDateEntity;
import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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


        List<ReturnGoodsVo> goodsVoList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            ReturnGoodsVo goodsVo = new ReturnGoodsVo();
            goodsVo.setGoodsId(i);
            goodsVo.setGoodsName("水果" + i);
            goodsVo.setGoodsNum(i);
            goodsVo.setGoodsPrice("1");
            goodsVoList.add(goodsVo);
        }

        TableDateEntity tableDate = new TableDateEntity<ReturnGoodsVo>();
        tableDate.setCode(0);
        tableDate.setDate(goodsVoList);
        tableDate.setCount(goodsVoList.size());
        tableDate.setMsg("");


        JSON json = JSONUtil.parse(tableDate);

        JSON json2 = JSONUtil.parse("{\n" +
                "  \"msg\": \"\",\n" +
                "  \"date\": [\n" +
                "    {\n" +
                "      \"goodsId\": 0,\n" +
                "      \"goodsPrice\": \"1\",\n" +
                "      \"goodsName\": \"水果0\",\n" +
                "      \"goodsNum\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"goodsId\": 1,\n" +
                "      \"goodsPrice\": \"1\",\n" +
                "      \"goodsName\": \"水果1\",\n" +
                "      \"goodsNum\": 1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"code\": 0,\n" +
                "  \"count\": 2\n" +
                "}");

        JSON json1 = JSONUtil.parse("{\n" +
                "    \"msg\": \"\",\n" +
                "    \"data\": [\n" +
                "    {\n" +
                "        \"goodsId\": 1,\n" +
                "        \"goodsName\": \"自行车\",\n" +
                "        \"goodsNum\": 12,\n" +
                "        \"goodsPrice\": 123\n" +
                "    },\n" +
                "    {\n" +
                "        \"goodsId\": 2,\n" +
                "        \"goodsNum\": 11,\n" +
                "        \"goodsName\": \"遥控车\",\n" +
                "        \"goodsPrice\": 12.3\n" +
                "    }\n" +
                "],\n" +
                "    \"code\": 0,\n" +
                "    \"count\": 2\n" +
                "}");

        System.out.println(json.toString());
        Map<String, String[]> map = request.getParameterMap();
//
//        return json.toString();
        return json1.toString();
//        return json2.toString();
    }
}
