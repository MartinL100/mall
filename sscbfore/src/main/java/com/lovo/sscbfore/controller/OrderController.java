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
        import java.util.ArrayList;
        import java.util.List;


@RestController
public class OrderController {

    @Autowired
    ObjectMapper objectMapper;
    //远程调用的模板
    @Autowired
    private RestTemplate restTemplate;
    //根据订单号删除
    @RequestMapping("orderNumDel")
    public  String  orderDel(String orderNum){

        String orders= restTemplate.getForEntity("http://sscAfter/delOrderInfo/"+orderNum,String.class).getBody();
        return orders;
   }
//根据下单日期,订单类型,用户名模糊查询
    @RequestMapping("orderDellist")
    public String orderFindByDateAndTypeAndName(HttpServletRequest request, String orderDate, int orderType, int page , int limit)throws JsonProcessingException {
       //从session中获取登录的用户名
        String userName =((UserEntity)request.getSession().getAttribute("userEntity")).getUserName() ;
        if("".equals(orderDate)||null==orderDate){
            orderDate="no";
        }

        //远程调用接口
        String orderInfo= restTemplate.getForEntity("http://sscAfter/findOrderInfo/"+orderDate+"/"+orderType+"/"+page+"/"+limit+"/"+userName,String.class).getBody();

//        List<> orderInfo="";
//        try {
//            orderInfo = objectMapper.readValues(order,List.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        orderInfo= "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+orderInfo+"}";

        return orderInfo;

    }
    //根据下单日期,订单类型,用户名 查询总行数
    @RequestMapping("orderDelNum")
    public int orderDateAndTypeAndNameNum(HttpServletRequest request,String orderDate, int orderType){
        String userName =((UserEntity)request.getSession().getAttribute("userEntity")).getUserName() ;        //远程调用接口
        if("".equals(orderDate)||null==orderDate){
            orderDate="no";
        }
        return Integer.parseInt(restTemplate.getForEntity("http://sscAfter/findOrderRows/"+orderDate+"/"+orderType+"/"+userName,String.class).getBody());
    }
//根据订单号查询商品的集合 进行分页
    @RequestMapping("orderNumlist")
    public String orderFindByOrderNum(String orderNum,int pageTwo ,int limit){

      String goodsDto=  restTemplate.getForEntity("http://sscAfter/findGoodsRows/"+orderNum+"/"+pageTwo+"/"+limit,String.class).getBody();
        String goodsInfo="";
//        try {
//            goodsInfo = objectMapper.writeValueAsString(goodsDto);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        goodsInfo= "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+goodsInfo+"}";

        return goodsInfo;

    }
//根据订单号 查询商品的总行数
    @RequestMapping("orderNum")
    public int goodsNum(String orderNum){

        return Integer.parseInt(restTemplate.getForEntity("http://sscAfter/findGoodsRows/"+orderNum,String.class).getBody());
    }
}
