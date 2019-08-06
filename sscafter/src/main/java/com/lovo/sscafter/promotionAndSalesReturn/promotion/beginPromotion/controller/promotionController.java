package com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.service.IPromotionGoodsService;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import com.lovo.sscafter.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 促销controller
 */
@Controller
@RequestMapping("promotion")
public class promotionController {

    @Autowired
    private IPromotionGoodsService service;

    @RequestMapping("/findAll")
    @ResponseBody//只返回数据
    public Map<String,Object> findAll(String goodsName, String goodsType, int page, int rows){
        //动态查询总行数
      int pageCount= (int)service.findCount(goodsName,goodsType);

      //计算起始位置
      int nowPage=(page-1)*rows;

       List<GoodsEntity> list= service.findBygoodsNameAndgoodsState(goodsName,goodsType,nowPage,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("rows",list);
        map.put("page",nowPage);
        map.put("total",pageCount);
        return map;
    }

    //根据页面传入的id集合查询商品，返回到设置促销页面
    @RequestMapping("/showGoods")
    @ResponseBody
    public Map<String,Object> findRestList(String list, HttpServletRequest request, HttpServletResponse response){
        List<String> listString=null;
        ObjectMapper mapper=new ObjectMapper();
        try {
            //将json字符串转换为list集合
          listString=  (List<String>)mapper.readValue(list,Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

      List<GoodsEntity> goodsList=  service.findByGoodsId(listString);
        if ( !"".equals(goodsList) && null!=goodsList){

        }
        request.setAttribute("goodsList",goodsList);
        try {
            //请求转发
            request.getRequestDispatcher("").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
