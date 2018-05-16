layui.use(['form','layer','laydate'],function(){
    var form = layui.form,
    	laydate = layui.laydate,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    laydate.render({
        elem: '#admissionDate' //渲染日历input
    });
    
    form.on("submit(addStudent)",function(data){
    	var pwd = $('#re_password').val();
    	if (pwd != $('#password').val()) {
    		layer.msg('密码不一致！', {icon: 5,time:1000});
    		return false;
    	}
    	pwd = hex_md5(pwd);
    	//弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	$.ajax({
    		type: 'post',
    		url: '/CMS/student/add.html',
    		data: {
    			opType: $("#opType").val(),
				id : $(".id").val(),
	            name : $(".name").val(),
	            sex : $("input[type='radio']:checked").val(),
	            password : pwd,
	            admissionDate : $('#admissionDate').val(),
	            major : $('#major').val(),
	            grade : $("#grade").val(),
	            education:$(".education").val()
			},
			timeout:2000,
    		success:function(msg) {
				top.layer.close(index);
				if (msg == true) {
					if ($("#opType").val() == 1) {//是修改操作
						//修改成功则刷新父页面
						layer.closeAll("iframe");
				           parent.location.reload();
					} else {
						top.layer.msg("操作成功！");
					}
				}else {
					layer.msg(msg, {icon: 5,time:1000});
				}
			},
    		error:function() {
    			layer.msg("操作失败！", {icon: 5,time:1000});
    			layer.close(index);
    		}
    	});
        return false;
    });
});