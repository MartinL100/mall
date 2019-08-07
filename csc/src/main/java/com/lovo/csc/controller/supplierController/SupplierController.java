package com.lovo.csc.controller.supplierController;

import com.lovo.csc.entity.SupplierEntity;
import com.lovo.csc.service.supplierService.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;
    @RequestMapping("saveSupplier.lovo")
    public  String saveSupplier(SupplierEntity supplier){
        supplierService.save(supplier);
        return  "{'errorMsg':true}";
    }
    @RequestMapping("getSupplier.lovo")
//    @ResponseBody
    public  SupplierEntity findBySupplierId(String id){
        SupplierEntity supplier=supplierService.findBySupplierId(id);
        return  supplier;
    }
    /**
     *
     * @param page 当前页
     * @param rows 每页显示的行数
     * @return
     */
    @RequestMapping("supplierPage.lovo")
    public Map<String,Object> page(int page, int rows,String supplierName,String supplierType,String supplierLevel,String supplierTag){
        Map<String,Object> map=new HashMap<>();
        List<SupplierEntity> list= supplierService.findAll(page,rows,supplierName,supplierType,supplierLevel,supplierTag);
        map.put("rows",list);
        map.put("page",page);
        long total=supplierService.countAll(supplierName,supplierType,supplierLevel,supplierTag);
        map.put("total",total);
        return  map;
    }
}
