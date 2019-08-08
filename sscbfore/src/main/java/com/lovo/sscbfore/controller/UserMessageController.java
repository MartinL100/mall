package com.lovo.sscbfore.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.lovo.sscbfore.entity.TableDateEntity;
import com.lovo.sscbfore.service.IUserMessageService;
import com.lovo.sscbfore.user.entity2.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author che
 * @title: UserMessageController
 * @projectName mall
 * @description: 用户消息Controller
 * @date 2019/8/6  17:29
 */
@RestController
public class UserMessageController {

    /**
     * 用户消息业务接口
     */
    @Autowired
    IUserMessageService messageService;


    /**
     * 按用户名称查找用户所有消息 并且分页
     *
     * @param userName 用户名
     * @param request  请求对象
     * @return 用户消息集合
     */
    @RequestMapping("/userMessage/{userName}")
    public String getUserMessages(@PathVariable("userName") String userName, HttpServletRequest request) {

        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));


        List<UserInfoEntity> messageList = messageService.findAllUserMessagePageAble(userName, page, limit);

        for (UserInfoEntity info : messageList) {
            info.setUserInfo(null);
            info.setMessageDate("" + DateUtil.date(Long.parseLong(info.getMessageDate())));
        }

        TableDateEntity<UserInfoEntity> tableDate = new TableDateEntity<>();
        tableDate.setMsg("");
        tableDate.setCount(messageService.countUserMessages(userName));
        tableDate.setCode(0);
        tableDate.setData(messageList);

        JSON json = JSONUtil.parse(tableDate);
        return json.toString();
    }

    /**
     * 更新用户消息状态
     *
     * @param userName 用户名
     * @return 更新成功消息
     */
    @RequestMapping("/userMessage/up/{userName}")
    public String updateUserMessage(@PathVariable("userName") String userName) {
        messageService.updateUserMessage(userName);
        return "{'info': 'Success'}";
    }

    /**
     * 按用户名查询用户新消息数量
     *
     * @param userName 用户名
     * @return {'newMessage':  消息数量 }
     */
    @RequestMapping("/userMessage/countNew/{userName}")
    public String countUserNewMessage(@PathVariable("userName") String userName) {
        return "{'newMessage':" + messageService.countUserNewMessage(userName) + "}";
    }

}
