package com.lovo.sscbfore.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author che
 * @title: IAddGoodsService
 * @projectName mall
 * @description: 商品上架业务接口
 * @date 2019/8/8  17:55
 */
public interface IAddGoodsService {

    /**
     * 商品上架
     * 1、将文件保存到XXX
     * 2、根据Id 查询商品，生成商品对象
     * 3、修改商品价格，地址路径
     * 4、发送MQ到addGoodsMQ
     * 5、远程调用更改商品状态
     *
     * @param goodsId    商品id
     * @param goodsPrice 商品价格
     * @param file       商品文件图片
     * @return 是否成功： 成功返回0 不成功返回1
     */
    int addGoods(String goodsId, String goodsPrice, MultipartFile file) throws IOException;
}
