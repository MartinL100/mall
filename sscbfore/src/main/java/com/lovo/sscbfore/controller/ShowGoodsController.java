package com.lovo.sscbfore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author che
 * @title: ShowGoodsController
 * @projectName mall
 * @description: 商品展示controller
 * @date 2019/8/8  9:44
 */
@Controller
public class ShowGoodsController {

    @RequestMapping("showgoods/jump/{goodsId}")
    public ModelAndView jumpAddGoodsTwo(@PathVariable("goodsId") String goodsId) {
        ModelAndView mv = new ModelAndView("/page/show/addGoodsTwo.html");
        return mv;
    }


    /**
     * 查询所有商品信息
     *
     * @return
     */
    @RequestMapping("showgoods/req/allgoods")
    @ResponseBody
    public String goodsTableData() {

        return null;
    }


}
