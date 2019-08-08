package com.lovo.csc.controller.supplierController;

import com.lovo.csc.entity.SupplyEntity;
import com.lovo.csc.service.supplierService.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SupplyController {
    @Autowired
    private ISupplyService supplyService;
    @RequestMapping("saveSupply.lovo")
    public  String saveSupplier(SupplyEntity supply){
        supplyService.save(supply);
        return  "{'errorMsg':true}";
    }
    @RequestMapping("getSupply.lovo")
//    @ResponseBody
    public  List<SupplyEntity> findSupply(String indentId){
        List<SupplyEntity> supply=supplyService.findSupply(indentId);
        return  supply;
    }
    /**
     *
     * @param page 当前页
     * @param rows 每页显示的行数
     * @return
     */
    @RequestMapping("supplyPage.lovo")
    public Map<String,Object> page(int page, int rows, String goodsName, String indentStatus){
        Map<String,Object> map=new HashMap<>();
        List<SupplyEntity> list= supplyService.findAll(page,rows,goodsName,indentStatus);
        map.put("rows",list);
        map.put("page",page);
        long total=supplyService.countAll(goodsName,indentStatus);
        map.put("total",total);
        return  map;
    }
}
