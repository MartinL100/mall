package com.lovo.csc.controller.supplierController;

import com.lovo.csc.entity.SupplierGoodsEntity;
import com.lovo.csc.service.supplierService.ISupplierGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SupplierGoodsController {
    @Autowired
    private ISupplierGoodsService supplierGoodsService;
    @RequestMapping("saveSupplierGoods.lovo")
    public String saveSupplierGoods(SupplierGoodsEntity supplierGoods){
        supplierGoodsService.save(supplierGoods);
        return  "{'errorMsg':true}";
    }
    @RequestMapping("getSupplierGoods.lovo")
//    @ResponseBody
    public List<SupplierGoodsEntity> findBySuppliegrId(String supplierId){
        List<SupplierGoodsEntity> list=supplierGoodsService.findBySuppliegrId(supplierId);
        return  list;
    }
    /**
     * @return
     */
    @RequestMapping("deleteByCodeId.lovo")
    public String deleteByCodeId(String codeId){
        supplierGoodsService.deleteByCodeId(codeId);
        return  "{'errorMsg':true}";
    }
}
