package com.lovo.sscbfore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.common.entity.GoodsDTO;
import com.lovo.common.entity.GoodssDTO;
import com.lovo.common.entity.OrderDTO;
import com.lovo.sscbfore.user.entity2.UserEntity;
import com.lovo.sscbfore.util.DealErroInfos;
import com.lovo.sscbfore.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MakeDelController {
    @Autowired
    ObjectMapper objectMapper;
    //远程调用的模板
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("initDel")
    public String initDel(HttpServletRequest request){
        String userName=((UserEntity)request.getSession().getAttribute("userEntity")).getUserName();
        //模拟从map中读取数据
        List<GoodssDTO> goodsDTOList = (List<GoodssDTO>) ShoppingController.map2.get(userName);
        List<GoodssDTO> goodsDTOListTemp = new ArrayList<>();
        for (GoodssDTO goodsDTO:goodsDTOList) {
            if(goodsDTO.getChoose()==1){
                goodsDTOListTemp.add(goodsDTO);
            }
        }
        String goodInfo="";
        try {
             goodInfo = objectMapper.writeValueAsString(goodsDTOListTemp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        goodInfo= "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+goodInfo+"}";
//        String info="{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[{\"id\":10000,\"username\":\"user-0\",\"sex\":\"女\",\"city\":\"城市-0\",\"sign\":\"签名-0\",\"experience\":255,\"logins\":24,\"wealth\":82830700,\"classify\":\"作家\",\"score\":57},{\"id\":10001,\"username\":\"user-1\",\"sex\":\"男\",\"city\":\"城市-1\",\"sign\":\"签名-1\",\"experience\":884,\"logins\":58,\"wealth\":64928690,\"classify\":\"词人\",\"score\":27},{\"id\":10002,\"username\":\"user-2\",\"sex\":\"女\",\"city\":\"城市-2\",\"sign\":\"签名-2\",\"experience\":650,\"logins\":77,\"wealth\":6298078,\"classify\":\"酱油\",\"score\":31},{\"id\":10003,\"username\":\"user-3\",\"sex\":\"女\",\"city\":\"城市-3\",\"sign\":\"签名-3\",\"experience\":362,\"logins\":157,\"wealth\":37117017,\"classify\":\"诗人\",\"score\":68},{\"id\":10004,\"username\":\"user-4\",\"sex\":\"男\",\"city\":\"城市-4\",\"sign\":\"签名-4\",\"experience\":807,\"logins\":51,\"wealth\":76263262,\"classify\":\"作家\",\"score\":6},{\"id\":10005,\"username\":\"user-5\",\"sex\":\"女\",\"city\":\"城市-5\",\"sign\":\"签名-5\",\"experience\":173,\"logins\":68,\"wealth\":60344147,\"classify\":\"作家\",\"score\":87},{\"id\":10006,\"username\":\"user-6\",\"sex\":\"女\",\"city\":\"城市-6\",\"sign\":\"签名-6\",\"experience\":982,\"logins\":37,\"wealth\":57768166,\"classify\":\"作家\",\"score\":34},{\"id\":10007,\"username\":\"user-7\",\"sex\":\"男\",\"city\":\"城市-7\",\"sign\":\"签名-7\",\"experience\":727,\"logins\":150,\"wealth\":82030578,\"classify\":\"作家\",\"score\":28},{\"id\":10008,\"username\":\"user-8\",\"sex\":\"男\",\"city\":\"城市-8\",\"sign\":\"签名-8\",\"experience\":951,\"logins\":133,\"wealth\":16503371,\"classify\":\"词人\",\"score\":14},{\"id\":10009,\"username\":\"user-9\",\"sex\":\"女\",\"city\":\"城市-9\",\"sign\":\"签名-9\",\"experience\":484,\"logins\":25,\"wealth\":86801934,\"classify\":\"词人\",\"score\":75},{\"id\":10010,\"username\":\"user-10\",\"sex\":\"女\",\"city\":\"城市-10\",\"sign\":\"签名-10\",\"experience\":1016,\"logins\":182,\"wealth\":71294671,\"classify\":\"诗人\",\"score\":34},{\"id\":10011,\"username\":\"user-11\",\"sex\":\"女\",\"city\":\"城市-11\",\"sign\":\"签名-11\",\"experience\":492,\"logins\":107,\"wealth\":8062783,\"classify\":\"诗人\",\"score\":6},{\"id\":10012,\"username\":\"user-12\",\"sex\":\"女\",\"city\":\"城市-12\",\"sign\":\"签名-12\",\"experience\":106,\"logins\":176,\"wealth\":42622704,\"classify\":\"词人\",\"score\":54},{\"id\":10013,\"username\":\"user-13\",\"sex\":\"男\",\"city\":\"城市-13\",\"sign\":\"签名-13\",\"experience\":1047,\"logins\":94,\"wealth\":59508583,\"classify\":\"诗人\",\"score\":63},{\"id\":10014,\"username\":\"user-14\",\"sex\":\"男\",\"city\":\"城市-14\",\"sign\":\"签名-14\",\"experience\":873,\"logins\":116,\"wealth\":72549912,\"classify\":\"词人\",\"score\":8},{\"id\":10015,\"username\":\"user-15\",\"sex\":\"女\",\"city\":\"城市-15\",\"sign\":\"签名-15\",\"experience\":1068,\"logins\":27,\"wealth\":52737025,\"classify\":\"作家\",\"score\":28},{\"id\":10016,\"username\":\"user-16\",\"sex\":\"女\",\"city\":\"城市-16\",\"sign\":\"签名-16\",\"experience\":862,\"logins\":168,\"wealth\":37069775,\"classify\":\"酱油\",\"score\":86},{\"id\":10017,\"username\":\"user-17\",\"sex\":\"女\",\"city\":\"城市-17\",\"sign\":\"签名-17\",\"experience\":1060,\"logins\":187,\"wealth\":66099525,\"classify\":\"作家\",\"score\":69},{\"id\":10018,\"username\":\"user-18\",\"sex\":\"女\",\"city\":\"城市-18\",\"sign\":\"签名-18\",\"experience\":866,\"logins\":88,\"wealth\":81722326,\"classify\":\"词人\",\"score\":74},{\"id\":10019,\"username\":\"user-19\",\"sex\":\"女\",\"city\":\"城市-19\",\"sign\":\"签名-19\",\"experience\":682,\"logins\":106,\"wealth\":68647362,\"classify\":\"词人\",\"score\":51},{\"id\":10020,\"username\":\"user-20\",\"sex\":\"男\",\"city\":\"城市-20\",\"sign\":\"签名-20\",\"experience\":770,\"logins\":24,\"wealth\":92420248,\"classify\":\"诗人\",\"score\":87},{\"id\":10021,\"username\":\"user-21\",\"sex\":\"男\",\"city\":\"城市-21\",\"sign\":\"签名-21\",\"experience\":184,\"logins\":131,\"wealth\":71566045,\"classify\":\"词人\",\"score\":99},{\"id\":10022,\"username\":\"user-22\",\"sex\":\"男\",\"city\":\"城市-22\",\"sign\":\"签名-22\",\"experience\":739,\"logins\":152,\"wealth\":60907929,\"classify\":\"作家\",\"score\":18},{\"id\":10023,\"username\":\"user-23\",\"sex\":\"女\",\"city\":\"城市-23\",\"sign\":\"签名-23\",\"experience\":127,\"logins\":82,\"wealth\":14765943,\"classify\":\"作家\",\"score\":30},{\"id\":10024,\"username\":\"user-24\",\"sex\":\"女\",\"city\":\"城市-24\",\"sign\":\"签名-24\",\"experience\":212,\"logins\":133,\"wealth\":59011052,\"classify\":\"词人\",\"score\":76},{\"id\":10025,\"username\":\"user-25\",\"sex\":\"女\",\"city\":\"城市-25\",\"sign\":\"签名-25\",\"experience\":938,\"logins\":182,\"wealth\":91183097,\"classify\":\"作家\",\"score\":69},{\"id\":10026,\"username\":\"user-26\",\"sex\":\"男\",\"city\":\"城市-26\",\"sign\":\"签名-26\",\"experience\":978,\"logins\":7,\"wealth\":48008413,\"classify\":\"作家\",\"score\":65},{\"id\":10027,\"username\":\"user-27\",\"sex\":\"女\",\"city\":\"城市-27\",\"sign\":\"签名-27\",\"experience\":371,\"logins\":44,\"wealth\":64419691,\"classify\":\"诗人\",\"score\":60},{\"id\":10028,\"username\":\"user-28\",\"sex\":\"女\",\"city\":\"城市-28\",\"sign\":\"签名-28\",\"experience\":977,\"logins\":21,\"wealth\":75935022,\"classify\":\"作家\",\"score\":37},{\"id\":10029,\"username\":\"user-29\",\"sex\":\"男\",\"city\":\"城市-29\",\"sign\":\"签名-29\",\"experience\":647,\"logins\":107,\"wealth\":97450636,\"classify\":\"酱油\",\"score\":27}]}";
        return  goodInfo;
    }

    /**
     * 处理交易
     * @param OrderInfo 订单信息
     * @param GoodsInfo 商品集合信息
     * @return 交易结果
     * @throws IOException json转换异常
     */
    @RequestMapping("makeDel")
    public String makeDel(String OrderInfo,String GoodsInfo,HttpServletRequest request) throws IOException {
        String errorInfo = "";
        OrderDTO orderDTO= objectMapper.readValue(OrderInfo, OrderDTO.class);
        List<GoodsDTO> goodsDTOS= objectMapper.readValue(GoodsInfo, new TypeReference<List<GoodsDTO>>() {});
        //获取商品集合中所有的商品，封装为map，进行库存验证
        Map<String,String>map = new HashMap<String,String>();
        for (GoodsDTO goods:goodsDTOS) {
            map.put(goods.getGoodsId(),goods.getGoodsNum().toString());
        }
          //Cloud请求进行第一次库存验证
       String goodsMap= objectMapper.writeValueAsString(map);
        errorInfo= restTemplate.postForEntity(UrlUtil.IS_ENOUGH_URL,goodsMap,String.class).getBody();
        List<String> goodsErrorInfos = objectMapper.readValue(errorInfo,new TypeReference<List<String>>() {});
//        errorInfo="[\"aaaaa\",\"zzzzz\"]";//此处为为模拟库存不足的情况
        //如果存在商品库存不足
        if(goodsErrorInfos.size()>0){
            String errorInfoTemp="";
            for (GoodsDTO goodsDTO:goodsDTOS) {
                for (String info:goodsErrorInfos) {
                        if (goodsDTO.getGoodsId().equals(info)){
                            errorInfoTemp+=goodsDTO.getGoodsName()+"  ";
                        }
                }
            }
            errorInfoTemp+= DealErroInfos.NOT_ENOUGH;
            return errorInfoTemp;
        }
        //如果库存充足，则cloud调用生成订单
                //模拟从session中获取用户名
        String userName=((UserEntity)request.getSession().getAttribute("userEntity")).getUserName();
        orderDTO.setUserName(userName);
        //生成订单号
        String orderNum = DealErroInfos.getOrderNum(orderDTO);
        orderDTO.setOrderNum(orderNum);
        orderDTO.setOrderDate(new Timestamp(System.currentTimeMillis()).toString());
        orderDTO.setGoodsDTOList(goodsDTOS);
        //商品信息json
        String orderInfo = objectMapper.writeValueAsString(orderDTO);
        String infoTemp = URLEncoder.encode(orderInfo, "UTF-8");
        //保存订单
        String str= restTemplate.postForEntity(UrlUtil.SAVE_ORDER_URL,infoTemp,String.class).getBody();
        //post提交审核商品
        errorInfo=  restTemplate.postForEntity(UrlUtil.CHECK_ORDER_URL,orderInfo,String.class).getBody();
        Map<String,String>errorInfoMap = objectMapper.readValue(errorInfo,new TypeReference<Map<String,String>>() {});
        //审核未成功，返回错误信息
        if(!StringUtils.isEmpty(errorInfoMap.get("errorInfo"))&&!("null".equals(errorInfoMap.get("errorInfo")))){
            return errorInfoMap.get("errorInfo");
        }
        //审核成功，减少库存
       restTemplate.postForEntity(UrlUtil.UPDATE_GOODS_NUM_URL,goodsMap,String.class).getBody();
        //修改订单状态
        restTemplate.getForEntity(UrlUtil.UPDATE_ORDER_STATUE_URL+orderDTO.getOrderNum(),null);
        errorInfo="实际扣款:"+errorInfoMap.get("payMoney")+"（元）\n"+DealErroInfos.DEAL_SUCCEED;

        return  errorInfo;

    }


    @RequestMapping("depositMoneyIsEnough")
    public String depositMoneyIsEnough(float allPrice,String payMethod,HttpServletRequest request){
            String errorInfo = "";
            //模拟从session中获取用户名
            String userName=((UserEntity)request.getSession().getAttribute("userEntity")).getUserName();
            String url = UrlUtil.MONEY_IS_ENOUGH+userName+"/"+allPrice+"/"+payMethod;
            //获取折后价
           Float discountPrice= restTemplate
                   .getForEntity(url,Float.class)
                   .getBody();


           if(discountPrice<0){
               errorInfo=DealErroInfos.MONEY_NOT_ENOUGH;
           }else {
               errorInfo = discountPrice+"";
           }
            return  errorInfo;
    }

    @RequestMapping("buyRight")
    public String buyRight(HttpServletRequest request, String goodsInfo) throws IOException {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("userEntity");
        //判断登录的用户是否实名注册
        String statu = userEntity.getUserState();
        if("0".equals(statu)||"2".equals(statu)){
            return DealErroInfos.DATA_REGISTER;
        }else   if("3".equals(statu)){
            return DealErroInfos.UNFREZZE;
        }
        String info = "";
        GoodsDTO goodsDTO=objectMapper.readValue(goodsInfo,GoodsDTO.class);
        List<GoodsDTO>goodsDTOList = new ArrayList<>();
        goodsDTOList.add(goodsDTO);
        request.getSession().setAttribute("buyRight",goodsDTOList);

        return  goodsInfo;
    }


    @RequestMapping("presellMakeDealInit")
    public String presellMakeDealInit(HttpServletRequest request) throws IOException {
        String goodInfo="";
        List<GoodsDTO>goodsDTOList= (List<GoodsDTO>) request.getSession().getAttribute("buyRight");
        goodInfo = objectMapper.writeValueAsString(goodsDTOList);
        goodInfo= "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+goodInfo+"}";
        return goodInfo;
    }


}
