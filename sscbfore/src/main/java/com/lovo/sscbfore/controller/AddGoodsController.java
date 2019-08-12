package com.lovo.sscbfore.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.lovo.sscbfore.entity.GoodsEntity;
import com.lovo.sscbfore.entity.TableDateEntity;
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
    public ModelAndView jumpAddGoodsTwo(@PathVariable("goodsId") String goodsId, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/page/show/addGoodsTwo.html");
        request.getSession().setAttribute("goodsId", goodsId);
        return mv;
    }


    /**
     * 查询所有商品信息
     *
     * @return 商品信息集合
     */
    @RequestMapping("addgoods/req/allgoods/{goodsName}/{goodsType}")
    @ResponseBody
    public String goodsTableData(int page, int limit, @PathVariable("goodsName") String goodsName, @PathVariable("goodsType") String goodsType, HttpServletRequest request) {

//        http://sscAfter/findAll/{goodsType}/{goodsName}/{currentPage}/{rows}
        final String AllgoodsUrl = "http://sscAfter/findAll/";
        final String AllgoodsCountUrl = "http://sscAfter/findAllCount/";

        //获取商品信息
        String url1 = AllgoodsUrl + goodsType + "/" + goodsName + "/" + (page) + "/" + limit;
        //获取商品信息总数
        String url2 = AllgoodsCountUrl + goodsType + "/" + goodsName + "/";

        String goodsListStr = restTemplate.getForEntity(url1, String.class).getBody();
        Long totalRows = restTemplate.getForEntity(url2, Long.class).getBody();

        JSONArray goodsListJsonArray = JSONUtil.parseArray(goodsListStr);

        TableDateEntity tableDateEntity = new TableDateEntity();
        tableDateEntity.setCode(0);
        tableDateEntity.setMsg("");
        tableDateEntity.setCount(Integer.parseInt(totalRows + ""));
        tableDateEntity.setData(goodsListJsonArray);

        return JSONUtil.toJsonStr(tableDateEntity);
    }


    @RequestMapping(value = "addGoods/ad/", method = RequestMethod.POST)
    @ResponseBody
    public String addGoods(String goodsId, String goodsPrice, HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        int n = addGoodsService.addGoods(goodsId, goodsPrice, file);

        if (n == 0) {
            return "{'info':'Success'}";
        } else {
            return "{'info':'Failed'}";
        }
    }

    @RequestMapping(value = "addGoods/findgoods/{goodsId}")
    @ResponseBody
    public String findGoodsBiGoodsId(@PathVariable("goodsId") String goodsId, HttpServletRequest request) {

        goodsId = (String) request.getSession().getAttribute("goodsId");

        final String findGoodsUrl = "http://sscAfter/findGoodsByGoodsId/";
        GoodsEntity goodsEntity = restTemplate.getForEntity(findGoodsUrl + goodsId, GoodsEntity.class).getBody();
        return JSONUtil.toJsonStr(goodsEntity);
    }

}
