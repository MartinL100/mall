package com.lovo.psc.wangwei.controller;


import com.lovo.psc.entity.IndentEntity;
import com.lovo.psc.wangwei.dto.InderDTO;
import com.lovo.psc.wangwei.service.IIndenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndentController4 {

    @Autowired
    private IIndenService indenService;

   @RequestMapping("pageInden")
    public Map<String,Object> page(String goodsName, String indentId, String zbTag, String ghTag, String startDate, String endDate, int page, int rows, HttpServletRequest request){
//       SupplierEntity a  = (SupplierEntity) request.getSession().getAttribute("SupplierEntity");
//      String  supplierId =a.getSupplierId();

     String  supplierName="1";
       System.out.println(indentId);
     if(zbTag=="null"){
         zbTag=null;
     }
       if(ghTag=="null"){
           ghTag=null;
       }

        List<InderDTO> list =indenService.findInerList(goodsName,  indentId,  zbTag,  ghTag,  supplierName,  startDate,  endDate,   page,  rows);


        Map<String, Object> map = new HashMap<>();
        map.put("rows", list);
        map.put("page", page);
        long total = indenService.CountInerList(goodsName,  indentId,  zbTag,  ghTag,  supplierName,  startDate,  endDate);
        map.put("total", total);

        return map;
    }
    @RequestMapping("findIndent")
    public Map<String,Object> findIndent(String supplierId,String indentId ,String indentStatus, String startDate,String endDate, int page, int rows) {
       String supplierName="1";
     List<IndentEntity> list=   indenService.findIner( supplierName, indentId , indentStatus,  startDate, endDate,  page,  rows);

        Map<String, Object> map = new HashMap<>();
        map.put("rows", list);
        map.put("page", page);
        long total = indenService.CountIner( supplierName, indentId , indentStatus,  startDate, endDate);
        map.put("total", total);

        return map;





    }
}
