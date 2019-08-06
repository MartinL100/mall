package com.lovo.sscbfore.controller;

        import com.lovo.common.entity.OrderManagementDTO;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.List;

@RestController
public class OrderController {

    //根据订单号删除
    @RequestMapping("sscAfter/delOrderInfo/{orderNum}")
    public  void orderDel(@PathVariable("orderNum")String orderNum){

    }

//根据下单日期,订单类型,用户名模糊查询
    @RequestMapping("sscAfter/findOrderInfo/{orderDate}/{orderType}/{currentPage}/{rows}/{userName}")
    public List<OrderManagementDTO> orderFindByDateAndTypeAndName(@PathVariable("orderDate")String ordeDate,@PathVariable("orderType")String ordeType,@PathVariable("currentPage")String currentPage,@PathVariable("rows")String rows,@PathVariable("userName")String userName){

        return null;
    }
    //根据下单日期,订单类型,用户名 查询总行数
    @RequestMapping("sscAfter/findOrderRows/{orderDate}/{orderType}/{userName}")
    public int orderDateAndTypeAndNameNum(){

        return 0;
    }
//根据订单号查询商品的集合 进行分页
    @RequestMapping("sscAfter/findGoods/{orderNum}/{currentPage}/{rows}")
    public List<OrderManagementDTO> orderFindByOrderNum(@PathVariable("orderNum")String orderNum,@PathVariable("currentPage")String currentPage,@PathVariable("rows")String rows){

        return null;
    }
//根据订单号 查询商品的总行数
    @RequestMapping("sscAfter/findGoodsRows/{orderNum}/{currentPage}/{rows}")
    public int goodsNum(){

        return 0;
    }
}
