package com.lovo.sscbfore.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lovo.common.entity.GoodsDTO;
import com.lovo.common.entity.OrderDTO;
import com.lovo.sscbfore.entity.OrderReturnMQEntity;
import com.lovo.sscbfore.entity.ReturnEntity;
import com.lovo.sscbfore.entity.ReturnFailedMQ;
import com.lovo.sscbfore.entity.ReturnGoodsEntity;
import com.lovo.sscbfore.entity.ReturnGoodsVo;
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
     *
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
        String dataStr = map.get("data")[0];

        sendToOrderReturnMQ(dataStr);
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

            ActiveMQQueue queue = new ActiveMQQueue("orderReturnMQ");
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
        JSONArray returnGoods = dataobj.getJSONArray("data");

        ReturnEntity re = new ReturnEntity();
        re.setReturnId(IdUtil.simpleUUID() + "");
        re.setOrderNum(orderNum);
        re.setReturnDate(System.currentTimeMillis() + "");

        List<ReturnGoodsEntity> list = new ArrayList<>();

        for (int i = 0; i < returnGoods.size(); i++) {
            JSONObject goodsObj = new JSONObject(returnGoods.get(i));
            ReturnGoodsEntity g = goodsObj.toBean(ReturnGoodsEntity.class);
            list.add(g);
        }
        re.setReturnGoodsList(list);

        ActiveMQQueue queue = new ActiveMQQueue("goodsReturnMQ");
        jmsMessagingTemplate.convertAndSend(queue, JSONUtil.toJsonStr(re));
    }

    /**
     * 1、生成消息实体类保存到数据库
     * 2、用订单号远程调用，查询退货单中所有商品信息
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
