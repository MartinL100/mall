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

        return restTemplate.getForEntity("http://sscAfter/delOrderInfo/"+orderNum+"/",String.class).getBody();

   }
//根据下单日期,订单类型,用户名模糊查询
    @RequestMapping("orderDellist")
    public String orderFindByDateAndTypeAndName(HttpServletRequest request, String orderDate, String orderType, int pageTwo , int limit)throws JsonProcessingException {
       //从session中获取登录的用户名
        String userName =((UserEntity)request.getSession().getAttribute("userEntity")).getUserName() ;

        //远程调用接口
        List<OrderManagementDTO> order= restTemplate.getForEntity("http://sscAfter/findOrderInfo/"+orderDate+"/"+orderType+"/"+pageTwo+"/"+limit+"/"+userName+"/",List.class).getBody();
        String orderInfo="";
        try {
            orderInfo = objectMapper.writeValueAsString(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        orderInfo= "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+orderInfo+"}";

        return orderInfo;

    }
    //根据下单日期,订单类型,用户名 查询总行数
    @RequestMapping("orderDelNum")
    public int orderDateAndTypeAndNameNum(HttpServletRequest request,String orderDate, String orderType){
        String userName =((UserEntity)request.getSession().getAttribute("userEntity")).getUserName() ;        //远程调用接口

        return restTemplate.getForEntity("http://sscAfter/findOrderRows/"+orderDate+"/"+orderType+"/"+userName+"/",int.class).getBody();
    }
//根据订单号查询商品的集合 进行分页
    @RequestMapping("orderNumlist")
    public String orderFindByOrderNum(String orderNum,int pageTwo ,int limit){

      List<OrderForGoodsDTO> goodsDto=  restTemplate.getForEntity("http://sscAfter/findGoodsRows/"+orderNum+"/"+pageTwo+"/"+limit+"/",List.class).getBody();
        String goodsInfo="";
        try {
            goodsInfo = objectMapper.writeValueAsString(goodsDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        goodsInfo= "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":"+goodsInfo+"}";

        return goodsInfo;

    }
//根据订单号 查询商品的总行数
    @RequestMapping("orderNum")
    public int goodsNum(String orderNum){

        return restTemplate.getForEntity("http://sscAfter/findGoodsRows/"+orderNum+"/",int.class).getBody();
    }
}
