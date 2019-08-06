let userOrder = null;

//原退货单列表
let oldReturnList = [];

//老退货单列表
let returnList = [];

layui.config({
    base: '../../layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'element', 'laytpl'], function () {
    let table = layui.table;
    let laytpl = layui.laytpl;

    //监听工具条
    table.on('tool(returnTable)', function (obj) {
        let data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                for (let i = 0; i < returnList.length; i++) {
                    if (data.goodsId == returnList[i].goodsId) {
                        if (i === 0) {
                            returnList.splice(0, 1);
                            oldReturnList.splice(0, 1);
                        } else {
                            returnList.splice(i, 1);
                            oldReturnList.splice(i, 1);
                        }
                    }
                }
                obj.del();
                table2(returnList);
                layer.close(index);
            });
        }
    });

    //监听退货表修改事件
    table.on('edit(returnTable)', function (obj) {
        let data = obj.data;

        for (let i = 0; i < oldReturnList.length; i++) {
            if (oldReturnList[i].goodsId === data.goodsId) {
                if (oldReturnList[i].goodsNum < data.goodsNum) {
                    returnList[i].goodsNum = oldReturnList[i].goodsNum;
                    table2(returnList);
                    layer.msg('修改失败，该货物最多退' + oldReturnList[i].goodsNum + '个');
                } else if (data.goodsNum <= 0) {
                    returnList[i].goodsNum = 1;
                    table2(returnList);
                    layer.msg('修改失败，该货物最少退' + 1 + '个');
                } else if (0 < data.goodsNum <= oldReturnList[i].goodsNum && /^\d+$/.test(data.goodsNum)) {
                    returnList[i].goodsNum = parseInt(data.goodsNum);
                    table2(returnList);
                    layer.msg('修改成功');
                } else if (/^\d+$/.test(data.goodsNum) === false) {
                    returnList[i].goodsNum = oldReturnList[i].goodsNum;
                    table2(returnList);
                    layer.msg('修改失败，请输入一个有效数字');
                }
                break;
            }
            table2(returnList);
        }
    });

    let $ = layui.$, active = {
        getCheckData: function () {
            table1();
            let checkStatus = table.checkStatus('orderTable')
                , data = checkStatus.data;
            if (oldReturnList != null && oldReturnList.length === 0) {
                oldReturnList = data;
            } else {
                $.each(data, function () {
                    for (let i = 0; i < oldReturnList.length; i++) {
                        if (this.goodsId == oldReturnList[i].goodsId) {
                            return;
                        }
                    }
                    oldReturnList.push(this)
                });
            }
            let str = JSON.stringify(oldReturnList);
            returnList = eval("(" + str + ")");
            table2(returnList);
        }
        , getCheckLength: function () { //获取选中数目
            let checkStatus = table.checkStatus('goodsId')
                , data = checkStatus.data;
            layer.msg('选中了：' + data.length + ' 个');
        }
        , isAll: function () {
            let orderTableDate = table.cache.orderTable;
            $.each(orderTableDate, function (index, item) {
                orderTableDate[index] = table.clearCacheKey(item)
            });

            if (oldReturnList != null && oldReturnList.length === 0) {
                oldReturnList = orderTableDate;
            } else {
                $.each(orderTableDate, function () {
                    for (let i = 0; i < oldReturnList.length; i++) {
                        if (this.goodsId == oldReturnList[i].goodsId) {
                            return;
                        }
                    }
                    oldReturnList.push(this)
                });
            }
            let str = JSON.stringify(oldReturnList);
            returnList = eval("(" + str + ")");
            table2(returnList);
        }
        , submit: function () {
            let returnListDate = table.cache.returnTable;
            $.each(returnListDate, function (index, item) {
                returnListDate[index] = table.clearCacheKey(item)
            });
            alert(JSON.stringify(returnList))
        }
    };

    //layui active 注册
    $('.layui-btn').on('click', function () {
        let type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //初始化加载
    $.ready(table1(), table2(), getOrder());

    function getOrder() {
        $.get('/returngoods/123/jump/123', null, function (res) {
            userOrder = eval("(" + res + ")");
            // let data = userOrder;
            let data = { //数据
                "payMethod": userOrder.payMethod
                , "orderNum": userOrder.orderNum
            };

            let getTpl = demo.innerHTML
                , view = document.getElementById('view')
                , view2 = document.getElementById('view2');
            laytpl(getTpl).render(data, function (html) {
                view.innerHTML = html;
                view2.innerHTML = html;
            });
        })
    }


    //加载表格1
    function table1() {
        table.render({
            elem: '#orderTable'
            , url: '/returngoods/req'
            , id: 'orderTable'
            , page: true
            // , cellMinWidth: 80
            , cols: [[
                {field: 'ch', title: '选择', type: 'checkbox'}
                , {field: 'goodsId', title: '商品编号', sort: true}
                , {field: 'goodsName', title: '商品名称'}
                , {field: 'goodsNum', sort: true, title: '商品数量'}
                , {field: 'goodsPrice', title: '商品价格'}
            ]]
        });
    }

    //加载表格2
    function table2(data) {
        table.render({
            elem: '#returnTable'
            , data: data
            , id: 'returnTable'
            , page: true
            // , cellMinWidth: 80
            , cols: [[
                {field: 'goodsId', title: '商品编号', sort: true}
                , {field: 'goodsName', title: '商品名称'}
                , {field: 'goodsNum', edit: 'text', sort: true, title: '商品数量'}
                , {field: 'goodsPrice', title: '商品价格'}
                , {align: 'center', toolbar: '#barDemo', title: '操作'}
            ]]
        });
    }
});