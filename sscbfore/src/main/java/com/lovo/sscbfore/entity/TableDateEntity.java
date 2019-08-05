package com.lovo.sscbfore.entity;

import java.util.List;

/**
 * @author che
 * @title: TableDateEntity
 * @projectName mall
 * @description: layui表格数据实体类
 * @date 2019/8/3  14:55
 */
public class TableDateEntity<T> {

    /**
     * 当前页数
     */
    private int code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据总数
     */
    private int count;

    /**
     * 要展示的数据
     */
    private List<T> date;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getDate() {
        return date;
    }

    public void setDate(List<T> date) {
        this.date = date;
    }
}
