package com.lovo.sscbfore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
        return "{\n" +
                "    \"code\": 0,\n" +
                "    \"msg\": \"\",\n" +
                "    \"count\": 16,\n" +
                "    \"data\": [\n" +
                "    {\n" +
                "        \"goodsId\": 1,\n" +
                "        \"goodsName\": \"自行车\",\n" +
                "        \"goodsNum\": 12,\n" +
                "        \"goodsPrice\": 123\n" +
                "    },\n" +
                "    {\n" +
                "        \"goodsId\": 2,\n" +
                "        \"goodsName\": \"遥控车\",\n" +
                "        \"goodsNum\": 11,\n" +
                "        \"goodsPrice\": 12.3\n" +
                "    }\n" +
                "]\n" +
                "}";
    }
}
