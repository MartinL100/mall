package com.lovo.sscbfore.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.lovo.common.entity.GoodsDTO;
import com.lovo.common.entity.OrderDTO;
import com.lovo.sscbfore.entity.ReturnGoodsVo;
import com.lovo.sscbfore.entity.TableDateEntity;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 根据订单号，查询订单信息
     *
     * @param userName 用户名
     * @param orderNum 订单号
     * @return 订单Json
     */
    @RequestMapping("returngoods/{userName}/jump/{orderNum}")
    public String jumpGoodsList(@PathVariable("userName") String userName, @PathVariable("orderNum") String orderNum) {

        //根据订单号查询订单
//        String orderStr = restTemplate.getForEntity("http://sscAfter/findGoodsInfo/" + orderNum + "/", String.class).getBody();
//        JSONObject jsonObject = new JSONObject(orderStr);
//        return jsonObject.toString();

        List<GoodsDTO> goodsDTOList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            GoodsDTO goods = new GoodsDTO();
            goods.setGoodsId(i + "");
            goods.setGoodsName("水果" + i);
            goods.setGoodsNorms("个");
            goods.setGoodsPrice(123123f);
            goods.setGoodsNum(Long.parseLong(i + ""));
            goods.setGoodsType("123");
            goods.setGoodsUnit("123123123");
            goodsDTOList.add(goods);
        }

        OrderDTO order = new OrderDTO();
        order.setAddressId("123123123");
        order.setOrderNum("1ahsdkj123123");
        order.setOrderDate(System.currentTimeMillis() + "");
        order.setUserName("che");
        order.setOrderMoney(123123f);
        order.setPayMoney(123123 + "");
        order.setPayMethod("allipay");
        order.setGoodsDTOList(goodsDTOList);

        JSON json = JSONUtil.parse(order);
        return json.toString();
    }

    /**
     * 查询该订单的商品（待修改）
     * @param request 请求
     * @return 订单的商品集合Json
     */
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

    @RequestMapping("returngoods/creat")
    public String creatOrderGoodsList(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        System.out.println(map);
        return "123123";
    }



    @JmsListener(destination = "returnSuccessMQ")
    public void getReturnSuccessMessage(String message) {
        System.out.println(message);
    }

    @JmsListener(destination = "returnFailedMQ")
    public void getReturnFailedMessage(String message) {

    }
}
