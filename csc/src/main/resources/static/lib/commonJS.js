
function sendErroInfo(info) {

    if(info!=null&&info!=""){

        layui.use('layer', function(){
            var layer = layui.layer;

            layer.msg(info,{icon: 5});
        });
    }

}

