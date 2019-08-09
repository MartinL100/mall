package com.lovo.sscbfore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

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


    /**
     * 添加商品图片
     *
     * @param request       请求对象
     * @param multipartFile 多媒体对象
     * @return 上传成功或失败
     */
    @RequestMapping(value = "addGoods/ad/", method = RequestMethod.POST)
    @ResponseBody
    public String addGoods(HttpServletRequest request, MultipartFile multipartFile) {
        File tarFile = new File("c:/");
        return null;
    }
}
