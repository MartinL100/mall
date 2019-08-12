package com.lovo.csc.controller.supplierController;

import com.lovo.csc.entity.IndentEntity;
import com.lovo.csc.service.supplierService.IIndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndentController {
    @Autowired
    private IIndentService indentService;
    @RequestMapping("saveIndent.lovo")
    public  String saveIndent(IndentEntity indent){
        indentService.save(indent);
        return  "{'errorMsg':true}";
    }
    @RequestMapping("getIndent.lovo")
//    @ResponseBody
    public  IndentEntity findByIndentId(String id){
        IndentEntity indent=indentService.findByIndentId(id);
        return  indent;
    }
    /**
     *
     * @param page 当前页
     * @param rows 每页显示的行数
     * @return
     */
    @RequestMapping("indentPage.lovo")
    public Map<String,Object> page(int page, int rows, String indentId, String startDate, String endDate, String indentStatus){
        Map<String,Object> map=new HashMap<>();
        List<IndentEntity> list= indentService.findIndent(page,rows,indentId,startDate,endDate,indentStatus);
        map.put("rows",list);
        map.put("pageTwo",page);
        long total=indentService.countAll(indentId,startDate,endDate,indentStatus);
        map.put("total",total);
        return  map;
    }
}
