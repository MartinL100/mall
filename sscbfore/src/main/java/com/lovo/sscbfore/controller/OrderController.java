package com.lovo.sscbfore.controller;

        import com.fasterxml.jackson.core.JsonProcessingException;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.lovo.common.entity.OrderForGoodsDTO;
        import com.lovo.common.entity.OrderManagementDTO;
        import com.lovo.sscbfore.user.entity2.UserEntity;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.client.RestTemplate;

        import javax.servlet.http.HttpServletRequest;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;


@RestController
public class OrderController {

    @Autowired
    ObjectMapper objectMapper;
    //远程调用的模板
    @Autowired
    private RestTemplate restTemplate;

//根据下单日期,订单类型,用户名模糊查询
    @RequestMapping("orderDellist")
    public String orderFindByDateAndTypeAndName(HttpServletRequest request, String orderDateList, Integer orderTypeList, String page , String limit)throws JsonProcessingException {
       //从session中获取登录的用户名
     String userName =((UserEntity)request.getSession().getAttribute("userEntity")).getUserName() ;
//        System.out.printf(""+orderDateList+orderTypeList);
        int page1 =Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);


        if("".equals(orderDateList)||null==orderDateList){
            orderDateList="no";
        }if("".equals(orderTypeList)||null==orderTypeList ){
            orderTypeList=3;
        }

        String url = "http://sscafter/findOrderInfo/"+orderDateList+"/"+orderTypeList+"/"+page+"/"+limit+"/"+userName;

        //远程调用接口
        String orderInfo= restTemplate.getForEntity(url,String.class).getBody();
        orderInfo= "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+orderInfo+"}";

        return orderInfo;

    }
    //根据下单日期,订单类型,用户名 查询总行数
    @RequestMapping("orderDelNum")
    public int orderDateAndTypeAndNameNum(HttpServletRequest request,String orderDate, Integer orderType){
     String userName =((UserEntity)request.getSession().getAttribute("userEntity")).getUserName() ;        //远程调用接口
        if("".equals(orderDate)||null==orderDate ){
            orderDate="no";
        }
        if("".equals(orderType)||null==orderType ){
            orderType=3;
        }
        return Integer.parseInt(restTemplate.getForEntity("http://sscafter/findOrderRows/"+orderDate+"/"+orderType+"/"+userName,String.class).getBody());
    }
//根据订单号查询商品的集合 进行分页
    @RequestMapping("orderNumlist")
    public String orderFindByOrderNum(String orderNum,int page ,int limit){

      String goodsDto=  restTemplate.getForEntity("http://sscafter/findGoodsRows/"+orderNum+"/"+page+"/"+limit,String.class).getBody();

//        try {
//            goodsInfo = objectMapper.writeValueAsString(goodsDto);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        goodsDto= "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+goodsDto+"}";

        return goodsDto;

    }
//根据订单号 查询商品的总行数
    @RequestMapping("orderNum")
    public int goodsNum(String orderNum){

        return Integer.parseInt(restTemplate.getForEntity("http://sscafter/findGoodsRows/"+orderNum,String.class).getBody());
    }

    //根据订单号删除
    @RequestMapping("orderNumDel")
    public  String  orderDel(String orderNum){

        String orders= restTemplate.getForEntity("http://sscafter/delOrderInfo/"+orderNum,String.class).getBody();
        return orders;
    }
}
