package com.lovo.sscbfore.controller;

import com.lovo.common.entity.GoodsDTO;
import com.lovo.common.entity.GoodssDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ShoppingController {
    //加入购物车的MAP
   public static Map<String ,Map<String,GoodsDTO>> map = new HashMap<String, Map<String,GoodsDTO>>();
   public static Map<String ,Map<String,GoodssDTO>>map2 = new HashMap<String, Map<String,GoodssDTO>>();

    @RequestMapping("shoppingAll")
    public String shopping(){

        String info2="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[";
        for (Map<String,GoodssDTO> m:map2.values()){
            for (GoodssDTO g:m.values()) {
                info2+="{\"goodsName\":\""+g.getGoodsName()+"\",\"goodsNum\":\""+g.getGoodsNum()+"\",\"goodsPrice\":\""+g.getGoodsPrice()+"\",\"goodsDiscount\":\""+g.getGoodsType()+"\"}" ;
            }
        }
        info2.substring(info2.length()-1);
        info2+="]}";

        System.out.printf(info2);
        String info="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":" +
                "[{\"goodsName\":\"Ak47-A\",\"goodsNum\":\"100支\",\"goodsPrice\":\"1000元/支\",\"goodsDiscount\":\"已退货\"}," +
                "{\"goodsName\":\"Ak47\",\"goodsNum\":" + "\"100支\",\"goodsPrice\":\"1000元/支\",\"goodsDiscount\":\"正常\"}," +
                "{\"goodsName\":\"火麒麟\",\"goodsNum\":\"100支\",\"goodsPrice\":" + "\"1000元/支\",\"goodsDiscount\":\"正常\"}," +
                "{\"goodsName\":\"激光\",\"goodsNum\":" + "\"100支\",\"goodsPrice\":\"8888元/支\",\"goodsDiscount\":\"退货中\"}," +
                "{\"goodsName\":\"毁灭\",\"goodsNum\":\"100支\",\"goodsPrice\":\"8880元/支\",\"goodsDiscount\":\"正常\"}]}";
        return info2;
    }
    //加入Map集合
    @RequestMapping("shoppingJion")
    public void joinMap(GoodssDTO dto){
        System.out.printf(dto.getGoodsName());
        Map<String,GoodssDTO> mapMin = new HashMap<String,GoodssDTO>();
        mapMin.put(dto.getGoodsName(),dto);
        System.out.printf(mapMin.size()+"");
        map2.put("集合1",mapMin);
        System.out.printf(""+map2.get("集合1").size());
        //集合1应该是Seeion里的用户名
//        spring mvc中session获取
//        HttpSession session= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
//        String openId = (String) session.getAttribute("openId");


    }

//修改数量
    @RequestMapping("setingMap")
    public void setingMap(String goodsName,String goodNum){
        System.out.printf(goodsName+"/////"+goodNum+"@1");
      //  从MAP中获取到要修改的对象
        System.out.printf(map2.get("集合1").get(goodsName).getGoodsId()+"@2");
        GoodssDTO goodssDTO=map2.get("集合1").get(goodsName);
        //进行重新赋值修改
        System.out.printf(goodssDTO.getGoodsNum()+"@3");

        goodssDTO.setGoodsNum(Long.parseLong(goodNum));

        map2.get("集合1").put(goodssDTO.getGoodsId(),goodssDTO);
        System.out.printf(map2.get("集合1").get(goodsName).getGoodsNum()+"");

    }

    //删除其中一个对象
    @RequestMapping("delMap")
    public void delMap(String goodsName){
        map2.get("集合1").remove(goodsName);
        int i=map.get("集合1").size();
        System.out.printf(""+i+"");

    }

//修改选中状态  ??????重写一个公共实体类
@RequestMapping("setingChoose")
    public void setingChoose(String goodsName){
    //  从MAP中获取到要修改的对象
    GoodssDTO goodssDTO=map2.get("集合1").get(goodsName);
    //进行重新赋值修改
    goodssDTO.setChoose(1);
    //放进MApmin中z
    map2.get("集合1").put(goodssDTO.getGoodsId(),goodssDTO);
    System.out.printf(map2.get("集合1").get(goodsName).getChoose()+"");

    //通过传过来的商品名修改其判定属性是否已经选中


                //放进map中

            }

    }







