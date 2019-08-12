package com.lovo.sscbfore.service.impl;

import cn.hutool.json.JSONUtil;
import com.lovo.sscbfore.entity.GoodsEntity;
import com.lovo.sscbfore.service.IAddGoodsService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author che
 * @title: AddGoodsServiceImpl
 * @projectName mall
 * @description: 商品上架接口实现类
 * @date 2019/8/8  18:00
 */
@Service
public class AddGoodsServiceImpl implements IAddGoodsService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public int addGoods(String goodsId, String goodsPrice, MultipartFile file) throws IOException {

        String goodsPicPath = fileTrans(file);
        GoodsEntity goods = getGoodsEntity(goodsId);

        goods.setGoodsPrice(Float.parseFloat(goodsPrice));
        goods.setGoodsPath(goodsPicPath);

        sendToAddGoodsMq(goods);

        updateGoodsState(goodsId);

        return 0;
    }

    /**
     * 远程修改商品状态
     *
     * @param goodsId 商品id
     */
    private void updateGoodsState(String goodsId) {
        final String updateGoodsStatusUrl = "http://sscAfter/upDateTag1ById/";
        restTemplate.getForEntity(updateGoodsStatusUrl + goodsId, String.class);
    }


    /**
     * 发送消息到商品上架mq
     *
     * @param goods 更改后商品对象
     */
    private void sendToAddGoodsMq(GoodsEntity goods) {
        ActiveMQQueue queue = new ActiveMQQueue("addGoodsMQ");
        jmsMessagingTemplate.convertAndSend(queue, JSONUtil.toJsonStr(goods));
    }

    /**
     * 远程查询商品信息，产生商品对象
     *
     * @param goodsId 商品id
     * @return 商品实体类
     */
    private GoodsEntity getGoodsEntity(String goodsId) {
        final String findGoodsUrl = "http://sscAfter/findGoodsByGoodsId/";
        GoodsEntity goodsEntity = restTemplate.getForEntity(findGoodsUrl + goodsId, GoodsEntity.class).getBody();
        return goodsEntity;
    }

    /**
     * 将文件保存到目标文件夹
     *
     * @param file 源文件
     * @return 保存后的路径
     * @throws IOException IO异常
     */
    private String fileTrans(MultipartFile file) throws IOException {
        final String picPath = "D:/virtualPath/goodsPic/";

        File tarFile = new File(picPath + System.currentTimeMillis() + file.getOriginalFilename());
        file.transferTo(tarFile);
        return tarFile.getPath();
    }


}
