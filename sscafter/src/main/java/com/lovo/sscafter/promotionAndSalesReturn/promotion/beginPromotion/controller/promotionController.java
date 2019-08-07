package com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.service.IPromotionGoodsService;
import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
import com.lovo.sscafter.util.ListFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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



    //根据页面传入的id集合，返回到设置促销页面
    @RequestMapping("/showGoods")
    @ResponseBody
    public String findRestList(String list, HttpServletRequest request,HttpServletResponse response) {
        List<String> listString=null;
        ObjectMapper mapper=new ObjectMapper();
        try {
            //将json字符串转换为list集合
          listString=  (List<String>)mapper.readValue(list,Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String info="";
        if ( !"".equals(listString) && null!=listString){
        //查询数据为空则返回no
            info="yes";
        }else {
            //不为空则返回yes
            info="no";
        }
        //将id集合放入到session中,传入到跳转的controller
      //  request.setAttribute("listString",listString);
   //     request.getSession().setAttribute("listString",listString);
            //请求转发
//           request.getRequestDispatcher("promotion/promotionAll").forward(request,response);
        ListFinal.list=listString;
        return info;
    }



    //接收请求转发传入的数据
    @RequestMapping("/promotionAll")
    @ResponseBody
    public Map<String,Object> promotionAll(int page, int rows){
        System.out.printf("航空的后果发挥卡的活动和公平");
    Map<String,Object> map=new HashMap<>();

        //得到请求转发的数据(id的集合)
//    List<String> goodsIdList=(List<String>) request.getSession().getAttribute("listString");

        //根据id集合在从数据库查询
    List<GoodsEntity> goodsList=service.findByGoodsId(ListFinal.list);
    int nowPage=(page-1)*rows;
    map.put("rows",goodsList);
    map.put("page",nowPage);
    map.put("total",goodsList.size());
    return map;
    }

}
