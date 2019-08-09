package com.lovo.psc.groupOne.controller1;

import com.lovo.psc.entity.SupplierEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.lovo.psc.groupOne.service.ISupplierService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class SupplierController1 {
    @Autowired
    private ISupplierService supplierService1;

    @RequestMapping("getSupplierInfo.psc")
    @ResponseBody
    public SupplierEntity getInfo(HttpServletRequest request){
        SupplierEntity supplier = (SupplierEntity) request.getSession().getAttribute("supplier");
        return supplier ;
    }

    @RequestMapping("login.psc")
    @ResponseBody
    public String login(String supplierName, String supplierPwd, HttpServletRequest request) {
        SupplierEntity supplier = supplierService1.login(supplierName,supplierPwd);
        String result = "no";
        if(null!=supplier){
//            supplier.setSupplyCenterList(null);
////            supplier.setBackGoodsList(null);
////            supplier.setSupplierGoodsList(null);
            request.getSession().setAttribute("supplier",supplier);
            result = "ok";
        }
        return result;
    }

    @RequestMapping("findSupplierNameBySupplierId/{supplierId}")
    @ResponseBody
    public String findBySupplierId(@PathVariable(name = "supplierId") String supplierId){
        return supplierService1.findBySupplierId(supplierId).getSupplierName();
    }
}
