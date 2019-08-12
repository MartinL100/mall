package com.lovo.sscbfore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.common.entity.GoodsDTO;
import com.lovo.common.entity.GoodssDTO;
import com.lovo.sscbfore.user.entity2.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ShoppingController {
    //加入购物车的MAP
    @Autowired
    ObjectMapper objectMapper;
   public static Map<String ,Map<String,GoodssDTO>>map2 = new HashMap<String, Map<String,GoodssDTO>>();

    @RequestMapping("shoppingAll")
    public String shopping(){

        String info2="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[";
        for (Map<String,GoodssDTO> m:map2.values()){
            for (GoodssDTO g:m.values()) {
                info2+="{\"choose\":\""+g.getChoose()+"\",\"goodsId\":\""+g.getGoodsId()+"\",\"goodsName\":\""+g.getGoodsName()+"\",\"goodsNum\":\""+g.getGoodsNum()+"\",\"goodsPrice\":\""+g.getGoodsPrice()+"\",\"goodsDiscount\":\""+g.getGoodsType()+"\"}," ;
            }
        }
        String str1 = info2.substring(0, info2.length()-1);
        System.out.print(str1);
        str1+="]}";
        System.out.printf(str1);
      return str1;
    }
    //加入Map集合
    @RequestMapping("shoppingJion")
    public void joinMap(String info, HttpServletRequest request) throws IOException {
        System.out.println(info);
        GoodssDTO  dto=objectMapper.readValue(info,GoodssDTO.class);
        //判断有没有当前用户
        String userName =((UserEntity)request.getSession().getAttribute("userEntity")).getUserName() ;
        Map<String,GoodssDTO> map=map2.get(userName);
        //如果没有当前用户
        if(map==null){
            Map map1 = new HashMap();
            map1.put(dto.getGoodsName(),dto);
            map2.put(userName,map1);
        }else{
            //将商品信息放入当前用户的map中
            map.put(dto.getGoodsName(),dto);
        }



//
//
//
//
//
//
//
//
//
//
//        System.out.printf(""+map2.size()+"*/*");
//        //判断公用MAP是否为空
//        if (map2.size()==0) {
//            //如果为空新建一个 用户名为KEY 商品信息为VLUE的对象（MAP）
//            System.out.printf(dto.getGoodsName());
//            Map<String, GoodssDTO> mapMin = new HashMap<String, GoodssDTO>();
//            mapMin.put(dto.getGoodsName(), dto);
//            map2.put("集合1", mapMin);
//            System.out.printf("啦啦"+map2.get("集合1").size()+"啦啦啦");
//            //集合1应该是Seeion里的用户名
////        spring mvc中session获取
////        HttpSession session= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
////        String openId = (String) session.getAttribute("openId");
//            //如果不为空 加入一个以用户名为key 的对象(商品信息的MAP)
//        }else {
//            map2.get("集合1").put(dto.getGoodsName(),dto);
//            System.out.printf("啦啦"+map2.get("集合1").size()+"啦啦啦");
//        }

    }

//修改数量
    @RequestMapping("setingMap")
    public void setingMap(String goodsName,String goodNum){
        System.out.printf(goodsName+"/////"+goodNum+"@1");
      //  从MAP中获取到要修改的对象
        System.out.printf(map2.get("集合1").get(goodsName).getGoodsId()+"@2");
        //找到要修改的对象赋值给goodssDTO
        GoodssDTO goodssDTO=map2.get("集合1").get(goodsName);
        System.out.printf(goodssDTO.getGoodsNum()+"@3");
        //进行重新赋值修改
        goodssDTO.setGoodsNum(Long.parseLong(goodNum));
        //删除MAP里的对象
        map2.get("集合1").remove(goodsName);
        //重新把修改过的对象加入到MAP中
        map2.get("集合1").put(goodssDTO.getGoodsName(),goodssDTO);
        System.out.printf(map2.get("集合1").get(goodsName).getGoodsNum()+"");

    }

    //删除其中一个对象
    @RequestMapping("delMap")
    public void delMap(String goodsName){
        map2.get("集合1").remove(goodsName);

    }

//修改选中状态  ??????重写一个公共实体类
@RequestMapping("setingChoose")
    public void setingChoose(String goodsName){
    System.out.printf(""+goodsName);
    //找到要修改的对象赋值给goodssDTO
    GoodssDTO goodssDTO=map2.get("集合1").get(goodsName);
    //进行重新赋值修改
    goodssDTO.setChoose(1);
    //删除MAP里的对象
    map2.get("集合1").remove(goodsName);
    //放进MAp中z
    map2.get("集合1").put(goodssDTO.getGoodsId(),goodssDTO);
//    System.out.printf(map2.get("集合1").get(goodsName).getChoose()+"");

    //通过传过来的商品名修改其判定属性是否已经选中


                //放进map中

            }

    }







