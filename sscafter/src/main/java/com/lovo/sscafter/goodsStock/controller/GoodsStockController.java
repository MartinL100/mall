package com.lovo.sscafter.goodsStock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.goodsStock.dto.LookBuyInfoDTO;
import com.lovo.sscafter.goodsStock.dto.OrderGoodsDTO;
import com.lovo.sscafter.goodsStock.dto.ReturnGoodsDto;
import com.lovo.sscafter.goodsStock.entity.*;
import com.lovo.sscafter.goodsStock.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsStockController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private IGoodsStockService goodsStockService;
    @Autowired
    private IOrderGoodsService orderGoodsService;
    @Autowired
    private ISupplyService supplyService;
    @Autowired
    private IReturnGoodsService returnGoodsService;
    @Autowired
    private IGoodsTypeService goodsTypeService;
    @Autowired
    private IIndentService indentService;
    @RequestMapping("/goodsStock")
    @ResponseBody
    public Map<String,Object> goodsStock(int page,int rows,String goodsName,String goodsType){
        if("不限".equals(goodsType)){
            goodsType="";
        }
        List<GoodsStockEntity> list= goodsStockService.findAllGoodsStock(goodsName,goodsType,page,rows);
        Long ro=goodsStockService.findAllGoodsStockCount(goodsName,goodsType);

        Map<String,Object> map = new HashMap<>();
        map.put("rows",list);
        map.put("page",page);
        map.put("total",ro);
        return map;
    }
@RequestMapping("/findAllgoodsType")
@ResponseBody
    public List<GoodsTypeEntity> goodsTypeFind(){
    List<GoodsTypeEntity> list=  goodsTypeService.findAll();

        return  list;
    }

    @RequestMapping("/saveGoods2")
    @ResponseBody
    public String  saveGoods(String goodsName,String goodsType,String goodsNorms
    ,String goodsUnit,Long goodsMinNum){
        GoodsStockEntity goods1=goodsStockService.findByNameTypeAnAndNorms(goodsName,goodsType,goodsNorms);
        if(null!=goods1){
            return "no";
        }
   GoodsStockEntity goods= new GoodsStockEntity();
   goods.setGoodsName(goodsName);
   goods.setGoodsType(goodsType);
   goods.setGoodsMinNum(goodsMinNum);
   goods.setGoodsNorms(goodsNorms);
   goods.setGoodsUnit(goodsUnit);
   goods.setTag("未采购");
        goodsStockService.saveGoods(goods);
return "yes";


    }

     @RequestMapping("/updateGoodsMinNum")
     @ResponseBody
    public void updateGoodsMinNum(String goodsId,Long goodsMinNum){
         goodsStockService.updateGoodsMinNum(goodsMinNum,goodsId);

    }

    @RequestMapping("/buySomthing")
    @ResponseBody
    public String  buySomthing( String  goodsIdJson,Long supplyNum) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nyr = dateFormat.format(new Date());

        IndentEntity ie=new  IndentEntity();
        ie.setIndentDate(nyr);
        indentService.saveIndent(ie);//保存订单

        ObjectMapper mapper = new ObjectMapper();
       List<String> list = (List<String>) mapper.readValue(goodsIdJson, Object.class);
        for ( String goodsId: list ) {
            //更改该商品的状态
            goodsStockService.upDateGoodsTag("正在采购",goodsId);
            GoodsStockEntity goods= new GoodsStockEntity();
            //保存采购-商品中间表
            goods.setGoodsId(goodsId);
            SupplyEntity sup=new SupplyEntity();
            sup.setGoods(goods);
            sup.setIndent(ie);
            sup.setSupplyNum(supplyNum);
            supplyService.saveSupply(sup);
        }
    return "yes";
    }

    @RequestMapping("/lookBuyInfo")
    @ResponseBody
    public Map<String,Object> lookBuyInfo(int page,int rows,String goodsName,String goodsType,String startDate,String endDate){
        if("不限".equals(goodsType)){
            goodsType="";
        }
        List<LookBuyInfoDTO> list = supplyService.findOrderGoods(goodsName,goodsType,startDate,endDate,page,rows);
        Long ro=supplyService.findOrderGoodsCount(goodsName,goodsType,startDate,endDate);

        Map<String,Object> map = new HashMap<>();
        map.put("rows",list);
        map.put("page",page);
      map.put("total",ro);
        return map;
    }

@RequestMapping("/orderGoodsInfo2")
@ResponseBody
    public Map<String,Object> orderGoodsInfo(int page,int rows,String goodsName,String goodsType,String startDate,String endDate){
    if("不限".equals(goodsType)){
        goodsType="";
    }
        Map<String,Object> map=new HashMap<>();
         List<OrderGoodsDTO> list2 = orderGoodsService.findOrderGoods(goodsName,goodsType,startDate,endDate,page,rows);
        long ro=orderGoodsService.findOrderGoodsCount(goodsName,goodsType,startDate,endDate);
    map.put("rows",list2);
    map.put("page",page);
    map.put("total",ro);
        return map;
    }
    @RequestMapping("/goToReturnGoods")
    @ResponseBody
        public String goToReturnGoods(String supplierId,String goodsId,
        String goodsName,String goodsType,String goodsNorms,String goodsUnit,
      String returnGoodsCause,Long returnGoodsNum,float goodsBid,String orderGoodsId){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nyr = dateFormat.format(new Date());
        ReturnGoodsEntity returnGoodsEntity=new ReturnGoodsEntity();
        //保存退货订单
        GoodsStockEntity goods = new GoodsStockEntity();
        goods.setGoodsId(goodsId);
        returnGoodsEntity.setReturnGoodsCause(returnGoodsCause);
        returnGoodsEntity.setReturnGoodsDate(nyr);
        returnGoodsEntity.setSupplierId(supplierId);
        returnGoodsEntity.setGoods(goods);
        returnGoodsEntity.setReturnGoodsNum(returnGoodsNum);
        returnGoodsService.saveReturnGoodsEntity(returnGoodsEntity);
        //保存退货订单结束
        //更改到货单状态
        orderGoodsService.updateIsReturnGoods("已退货",orderGoodsId);

        ReturnGoodsDto reDTO= new ReturnGoodsDto();
        reDTO.setGoodsBid(goodsBid);
        reDTO.setGoodsId(goodsId);
        reDTO.setGoodsName(goodsName);
        reDTO.setGoodsNorms(goodsNorms);
        reDTO.setGoodsType(goodsType);
        reDTO.setGoodsUnit(goodsUnit);
        reDTO.setReturnGoodsCause(returnGoodsCause);
        reDTO.setSupplierId(supplierId);
        reDTO.setReturnGoodsDate(nyr);
        reDTO.setReturnGoodsNum(returnGoodsNum);
        reDTO.setReturnGoodsId(returnGoodsEntity.getReturnGoodsId());

      //减少库存
        goodsStockService.updateGoodsNum("1",returnGoodsNum,goodsId);

        return "yes";
        }
@RequestMapping("/lookReturnGoodsInfo")
@ResponseBody
        public Map<String,Object> lookReturnGoodsInfo(int page,int rows,String goodsName,String goodsType,String startDate,String endDate){
            if("不限".equals(goodsType)){
                goodsType="";
            }
            Map<String,Object> map = new HashMap<>();
         List<ReturnGoodsDto> list=  returnGoodsService.findOrderGoods(goodsName,goodsType,startDate,endDate,page,rows);
       Long ro=returnGoodsService.findOrderGoodsCount(goodsName,goodsType,startDate,endDate);
    map.put("rows",list);
    map.put("page",page);
    map.put("total",ro);

            return map;
        }
        @RequestMapping("/lookAllGoodsType")
        @ResponseBody
        public Map<String,Object> lookAllGoodsType(String typeName,String typeKey,int page,int rows){
            Map<String,Object> map=new HashMap<>();
           List<GoodsTypeEntity> list= goodsTypeService.findGoodsTypeByNmae(typeName,page,rows);
             map.put("rows",list);
             map.put("page",page);
             Long ro = goodsTypeService.findGoodsTypeByNmaeCpunt(typeName);
            map.put("total",ro);

            return map;
        }
    @RequestMapping("/saveGoodsType")
    @ResponseBody
    public void saveGoodsType(String typeName,String typeKey){
      GoodsTypeEntity t= new GoodsTypeEntity();
         t.setTypeKey(typeKey);
         t.setTypeName(typeName);
        goodsTypeService.saveGoodsType(t);
    }
    @RequestMapping("/delGoodsType")
    @ResponseBody
    public void delGoodsType(String id){

        goodsTypeService.delGoodstype(id);
    }
    @RequestMapping("/updateGoodsType")
    @ResponseBody
    public void updateGoodsType(String typeName,String typeId){

        goodsTypeService.updateGoodstype(typeName,typeId);
    }
    @RequestMapping("/fingTypeByKey")
    @ResponseBody
    public String fingTypeByKey(String typeKey){
     GoodsTypeEntity goods=   goodsTypeService.findTypeByKey(typeKey);
     if(null==goods){
         return "yes";
     }else{
         return "no";
     }

    }

}
