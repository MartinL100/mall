package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnGoodsEntity;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.service.IReturnGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * 退货编号
 */
@RestController//只返回数据
@RequestMapping("findPutaway")
public class salesRerurnController {
    @Autowired
    private IReturnGoodsService goodsService;

    /**
     * 根据退货单单号查询商品
     * @param oderNum 退货单单号
     * @return 商品集合json
     */
    @RequestMapping("findGoodsByreturnOderNum/{returnOderNum}")
    public List<ReturnGoodsEntity> findGoodsByreturnOderNum(@PathVariable("returnOderNum")String oderNum){
    List<ReturnGoodsEntity> list=goodsService.findByReturnOderId(oderNum);
        return list;
}
}
