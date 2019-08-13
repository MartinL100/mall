package com.lovo.sscbfore.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lovo.common.entity.OrderDTO;
import com.lovo.common.entity.OrderForGoodsDTO;
import com.lovo.sscbfore.entity.OrderReturnMQEntity;
import com.lovo.sscbfore.entity.ReturnEntity;
import com.lovo.sscbfore.entity.ReturnFailedMQ;
import com.lovo.sscbfore.entity.ReturnGoodsEntity;
import com.lovo.sscbfore.entity.ReturnSuccessMQEntity;
import com.lovo.sscbfore.entity.TableDateEntity;
import com.lovo.sscbfore.service.IUserMessageService;
import com.lovo.sscbfore.user.entity2.UserEntity;
import com.lovo.sscbfore.user.entity2.UserInfoEntity;
import org.apache.activemq.command.ActiveMQQueue;
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

    @Autowired
    private IUserMessageService messageService;

    /**
     * 根据订单号，查询订单信息
     *
     * @param userName 用户名
     * @param orderNum 订单号
     * @return 订单Json
     */
    @RequestMapping("returngoods/{userName}/jump/{orderNum}")
    public String jumpGoodsList(@PathVariable("userName") String userName, @PathVariable("orderNum") String orderNum) {
//        根据订单号查询订单
        OrderDTO orderStr = restTemplate.getForEntity("http://sscAfter/findGoodsInfo/" + orderNum + "/", OrderDTO.class).getBody();
        JSONObject jsonObject = new JSONObject(orderStr);
        return jsonObject.toString();
    }

    /**
     * 查询该订单的商品（待修改）
     *
     * @param request 请求
     * @return 订单的商品集合Json
     */
    @RequestMapping("returngoods/req/{orderNum}")
    public String orderGoodsList(@PathVariable String orderNum, HttpServletRequest request) {

        Map<String, String[]> map = request.getParameterMap();
        int page = Integer.parseInt(map.get("page")[0]);
        int limit = Integer.parseInt(map.get("limit")[0]);
//        sscAfter/findGoods/{orderNum}/{currentPage}/{rows}
        final String findOrderGoodsUrl = "http://sscAfter/findGoods/";
//        /sscAfter/findGoodsRows/{orderNum}
        final String findOrderGoodsRowsUrl = "http://sscAfter/findGoodsRows/";

        String url = findOrderGoodsUrl + orderNum + "/" + page + "/" + limit;
        String url1 = findOrderGoodsRowsUrl + orderNum;


        List<OrderForGoodsDTO> goosDto = restTemplate.getForEntity(url, List.class).getBody();
        int rows = restTemplate.getForEntity(url1, int.class).getBody();

        TableDateEntity<OrderForGoodsDTO> tableDate = new TableDateEntity<>();
        tableDate.setCode(0);
        tableDate.setData(goosDto);
        tableDate.setCount(rows);
        tableDate.setMsg("");

        return JSONUtil.toJsonStr(tableDate);
    }

    @RequestMapping("returngoods/creat")
    public String creatOrderGoodsList(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        String dataStr = map.get("data")[0];

//        sendToOrderReturnMQ(dataStr);
        sendToGoodsReturnMQ(dataStr);
        return "{'info': 'Success'}";
    }

    /**
     * 发送消息到OrderReturnMQ
     *
     * @param dataStr 源数据
     */
    private void sendToOrderReturnMQ(String dataStr) {
        JSONObject dataobj = new JSONObject(dataStr);
        String orderNum = dataobj.getStr("orderNum");
        JSONArray returnGoods = dataobj.getJSONArray("data");

        for (int i = 0; i < returnGoods.size(); i++) {
            JSONObject goods = new JSONObject(returnGoods.get(i));
            String goodsId = goods.getStr("goodsId");

            OrderReturnMQEntity orderReturnMQEntity = new OrderReturnMQEntity(orderNum, goodsId, "1");

            ActiveMQQueue queue = new ActiveMQQueue("orderReturnMQ1");
            jmsMessagingTemplate.convertAndSend(queue, JSONUtil.toJsonStr(orderReturnMQEntity));
        }
    }

    /**
     * 将退货单信息发送到GoodsReturnMQ
     *
     * @param dataStr 源数据
     */
    private void sendToGoodsReturnMQ(String dataStr) {
        JSONObject dataobj = new JSONObject(dataStr);
        String orderNum = dataobj.getStr("orderNum");
        String userName = "";
        JSONArray returnGoods = dataobj.getJSONArray("data");


        ReturnEntity re = new ReturnEntity();

        re.setReturnId(IdUtil.simpleUUID() + "");
        re.setOrderNum(orderNum);
        re.setReturnDate(System.currentTimeMillis() + "");

        List<ReturnGoodsEntity> list = new ArrayList<>();

        for (int i = 0; i < returnGoods.size(); i++) {
            JSONObject goodsObj = new JSONObject(returnGoods.get(i));
            ReturnGoodsEntity g = goodsObj.toBean(ReturnGoodsEntity.class);
            if ("".equals(userName)) {
                String orderStr = goodsObj.getStr("orderObj");
                JSONObject orderObj = new JSONObject(orderStr);
                userName = orderObj.getStr("userName");
            }
            list.add(g);
        }
        re.setUserName(userName);
        re.setReturnGoodsList(list);

        ActiveMQQueue queue = new ActiveMQQueue("goodsReturnMQ1");
        jmsMessagingTemplate.convertAndSend(queue, JSONUtil.toJsonStr(re));
    }

    /**
     * 1、生成消息实体类保存到数据库
     * 2、用退货单号远程调用，查询退货单中所有商品信息
     * 3、以Jason格式发送到（orderReturnMQ）
     *
     * @param message 消息本体
     */
    @JmsListener(destination = "returnSuccessMQ")
    public void getReturnSuccessMessage(String message) {
        JSONObject jsonObject = new JSONObject(message);
        ReturnSuccessMQEntity successMQEntity = jsonObject.toBean(ReturnSuccessMQEntity.class);

        String messageInfo = successMQEntity.getReturnId() + "在" + successMQEntity.getMessageTime() + "退货成功";
        UserEntity user = new UserEntity();
        user.setUserName(successMQEntity.getUserName());

        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setMessageInfo(messageInfo);
        userInfo.setMessageDate(successMQEntity.getMessageTime());
        userInfo.setUserInfo(user);

        messageService.addUserMessage(userInfo);


        String returnId = successMQEntity.getReturnId();
        String returnStr = restTemplate.getForEntity("http://sscAfter/findGoodsInfo/" + returnId + "/", String.class).getBody();
        JSONObject returnJsonObject = new JSONObject(returnStr);


    }

    /**
     * 1、生成用户消息保存到用户消息列表
     *
     * @param message
     */
    @JmsListener(destination = "returnFailedMQ")
    public void getReturnFailedMessage(String message) {
        JSONObject jsonObject = new JSONObject(message);
        ReturnFailedMQ failedMQ = jsonObject.toBean(ReturnFailedMQ.class);

        String messageInfo = failedMQ.getReturnId() + "退货单在" + failedMQ.getMessageTime() + "退货失败,失败原因:" + failedMQ.getReturnInfo();
        UserEntity user = new UserEntity();
        user.setUserName(failedMQ.getUserName());

        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setMessageInfo(messageInfo);
        userInfo.setMessageDate(failedMQ.getMessageTime());
        userInfo.setUserInfo(user);

        messageService.addUserMessage(userInfo);

    }
}
