package com.lovo.sscbfore.controller;

import com.lovo.sscbfore.service.IAddGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author che
 * @title: AddGoodsController
 * @projectName mall
 * @description: 商品展示controller
 * @date 2019/8/8  9:44
 */
@Controller
public class AddGoodsController {

    @Autowired
    IAddGoodsService addGoodsService;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("addgoods/jump/{goodsId}")
    public ModelAndView jumpAddGoodsTwo(@PathVariable("goodsId") String goodsId) {
        ModelAndView mv = new ModelAndView("/page/show/addGoodsTwo.html");
        return mv;
    }


    /**
     * 查询所有商品信息
     *
     * @return 商品信息集合
     */
    @RequestMapping("addgoods/req/allgoods")
    @ResponseBody
    public String goodsTableData() {

        return null;
    }


    @RequestMapping(value = "addGoods/ad/", method = RequestMethod.POST)
    @ResponseBody
    public String addGoods(String goodsId, String goodsPrice, HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        addGoodsService.addGoods(goodsId, goodsPrice, file);
        return "{'info':'Success'}";
    }


}
