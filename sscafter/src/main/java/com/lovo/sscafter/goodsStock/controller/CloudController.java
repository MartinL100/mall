package com.lovo.sscafter.goodsStock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.goodsStock.entity.GoodsStockEntity;
import com.lovo.sscafter.goodsStock.entity.GoodsTypeEntity;
import com.lovo.sscafter.goodsStock.service.IGoodsStockService;
import com.lovo.sscafter.goodsStock.service.IGoodsTypeService;
import com.lovo.sscafter.goodsStock.service.IOrderGoodsService;
import com.lovo.sscafter.goodsStock.service.IOrderServicr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CloudController {
    @Autowired
    private IGoodsStockService goodsStockService;
    @Autowired
    private IOrderGoodsService orderGoodsService;
    @Autowired
    private IGoodsTypeService goodsTypeService;

@RequestMapping("/findAll/{goodsType}/{goodsName}/{currentPage}/{rows}")
@ResponseBody
    public  String findAll(@PathVariable("goodsType")String goodsType,
   @PathVariable("goodsName")String goodsName ,
    @PathVariable("currentPage")int page,
     @PathVariable("rows")int rows) throws JsonProcessingException {
    if("null".equals(goodsType)){
        goodsType="";
    }if("null".equals(goodsName)){
        goodsName="";
    }
    List<GoodsStockEntity> list= goodsStockService.findAllGoodsStockCloud(goodsName,goodsType,page,rows);
    ObjectMapper mapper = new ObjectMapper();
    String str=mapper.writeValueAsString(list);
        return str;
    }
@RequestMapping("/findAllCount/{goodsType}/{goodsName}")
@ResponseBody
    public long findAllCount(@PathVariable("goodsType")String goodsType,
                             @PathVariable("goodsName")String goodsName ){
    if("null".equals(goodsType)){
        goodsType="";
    }if("null".equals(goodsName)){
        goodsName="";
    }
         long a=goodsStockService.findAllGoodsStockCountCloud(goodsName,goodsType);
    return a;
    }

    @RequestMapping("/findGoodsByGoodsId/{goodsId}")
    @ResponseBody
    public String findGoodsById(@PathVariable("goodsId")String goodsId) throws JsonProcessingException {
        GoodsStockEntity goods=new GoodsStockEntity();
       goods=goodsStockService.findGoodsStockEntityByGoodsId(goodsId);
   ObjectMapper mapper = new ObjectMapper();
     String str=   mapper.writeValueAsString(goods);
        return str;
    }
    @RequestMapping("/upDateTag1ById/{goodsId}")
    @ResponseBody
    public void upDateTag1ById(@PathVariable("goodsId")String goodsId){

        goodsStockService.updateGoodsTag1ById(goodsId);
    }
//    @RequestMapping("/findGoodsBidById/{goodsId}")
//    @ResponseBody
//    public float findGoodsBidById(@PathVariable("goodsId")String goodsId){
//
//         return orderGoodsService.findGoodsBidByGoodsId(goodsId);
//    }

@RequestMapping("/findGoodsNum")
@ResponseBody
    public String findGoodsNum(@RequestBody String goodsMap) throws IOException {
 ObjectMapper mapper = new ObjectMapper();
     Map<String,String> map=(Map<String,String>) mapper.readValue(goodsMap,Object.class);
   List<String> list=new ArrayList<>();
    for (Map.Entry<String,String> entry:map.entrySet()) {
        String key = entry.getKey();
        Long val=Long.parseLong(entry.getValue());
     Long goodsNum=   goodsStockService.findGoodsNumId(key);
     if(goodsNum<val){
         list.add(key);
     }
    }
   String mapStr= mapper.writeValueAsString(list);

    return mapStr;
    }

    @RequestMapping("/findAllgoodsTypeCloud")
    @ResponseBody
    public String goodsTypeFind() throws JsonProcessingException {
        List<GoodsTypeEntity> list=  goodsTypeService.findAll();
    Map<String,String> map  =new HashMap<>();
        for (GoodsTypeEntity g:list
             ) {

            map.put(g.getTypeKey(),g.getTypeName());
        }

        ObjectMapper mapper = new ObjectMapper();
      String str=  mapper.writeValueAsString(map);
        return  str;
    }

}
