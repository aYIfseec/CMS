layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    
    //登录按钮
    form.on("submit(login)",function(data){
    	var userName = $("#userName").val();
    	var password = $("#password").val();
    	password = hex_md5(password);
    	var userType = $("#userType").val();
		var verifyCode = $("#code").val();
        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        $.ajax({
			type : "post",
			dataType : "text",
			async : false,//不加直接error
			url : '/CMS/login/doLogin.html',
			data : {"username": userName,
					"password": password,
					"userType": userType,
					"verifyCode": verifyCode
			},
			success : function(msg) {
				if (msg == "false") {
					layer.msg('用户名或密码错误！', {icon: 5});
					$("#codeimg").click();
				} else if (msg == "code_error") {
					layer.msg('验证码错误！', {icon: 5});
					$("#codeimg").click();
				} else {
					window.location.href = '/CMS/main/index.html';
				}
				document.getElementById("password").value = "";
			}
		});
        $(this).text("登录").attr("disabled",false).removeClass("layui-disabled");
        return false;
    });
    
    //刷新验证码
    $("#codeimg").click(function(){
    	$("#codeimg").attr('src', '/CMS/login/getVerifyCode.html?timestamp=' + (new Date()).getTime());
    });
    
    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    });
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    });
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    });
});
