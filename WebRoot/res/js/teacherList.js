layui.use(['form','layer','table','laytpl','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        upload = layui.upload,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#teacherList',
        url : '../teacher/list.html',
        request: {
        	pageName: 'curr' //页码的参数名称
        	,limitName: 'nums' //每页数据量的参数名
        },
        where: {
            searchKey: $(".searchVal").val()  //搜索的关键字
        },
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        loading : true,
        id : "teacherListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '教职工号', sort:true, width:150, align:"center"},
            {field: 'name', title: '姓名', width:150, align:'center'},
            {field: 'synopsis', title: '简介', align:'center'},
            {title: '操作', width:220, templet:'#teacherListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
        	tableIns.reload({
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	searchKey: $(".searchVal").val()  //搜索的关键字
                }
            });
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(edit){
    	var title = "添加教师";
    	if (edit) title = "编辑教师信息";
        var index = layui.layer.open({
            title : title,
            type : 2,
            content : "../teacher/addPage.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find("#opType").val("1");
                    body.find(".id").val(edit.id);
                    body.find(".id").attr("readonly","readonly").addClass("layui-disabled");
                    body.find(".name").val(edit.name);
                    body.find("#password").val("111222");
                    body.find("#password").attr("readonly","readonly").addClass("layui-disabled");
                    body.find("#re_password").val("111222");
                    body.find("#re_password").attr("readonly","readonly").addClass("layui-disabled");
                    body.find("#synopsis").val(edit.synopsis);
                    body.find('#reset').attr("disabled","disabled").addClass("layui-disabled");
                    form.render();
                } else {
                	body.find("#opType").val("0");
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回学生列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500);
            }
        });
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        });
    }
    //添加用户
    $(".addNews_btn").click(function(){
        addUser();
    });

    //批量导入
    var uploadInst = upload.render({
        elem: '#upload_btn' //绑定元素
        ,url: '../teacher/import.html' //上传接口
        ,accept: 'file'
        ,exts:'xls|xlsx'
        ,before: function(obj){
            layer.load(); //上传loading
        }
        ,done: function(res){
        	layer.closeAll('loading'); //关闭loading
        	tableIns.reload();
        }
        ,error: function(){
        	layer.closeAll('loading'); //关闭loading
        	//layer.msg("操作失败！", {icon: 5,time:1000});
        }
    });
    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('teacherListTable'),
            data = checkStatus.data,
            tId = "";
        if(data.length > 0) {
            for (var i in data) {
            	tId += data[i].id + ",";
            }
            //console.log(stuId);
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
            	$.ajax({
            		type: 'get',
            		data: {
            			tIds : tId
            		},
            		url: '../teacher/deleteList.html',
            		timeout:2000,
            		success:function(res) {
                    	if (res == true) {
                    		//console.log(tableIns);
                    		tableIns.reload({page: {
                                curr: 1 //重新从第 1 页开始
                            }});
                    	} else {
                    		layer.msg(res, {icon: 5,time:1000});
                    	}
                    	layer.close(index);
            		},
            		error:function() {
            			layer.msg("操作失败！", {icon: 5,time:1000});
            			layer.close(index);
            		}
            	});
            });
        }else{
            layer.msg("请选择需要删除的用户");
        }
    });
    
    function resetPswd(id) {
    	$.ajax({
    		type: 'get',
    		data: {
    			id: id,
    		},
    		url: '../teacher/resetPswd.html',
    		success:function(res) {
    			if (res == true) {
					top.layer.msg("重置成功！");
				}else {
					layer.msg(res, {icon: 5,time:1000});
				}
    		}
    	});
    }
    
    //列表操作
    table.on('tool(teacherList)', function(obj){
    	//alert("DA");
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            addUser(data);
        } else if(layEvent === 'reset') {
        	layer.confirm('确定重置此用户密码？密码将被重置为123456！',{icon:3, title:'提示信息'},function(index){
        		resetPswd(data.id);
                layer.close(index);
            });
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
            	$.ajax({
            		type: 'get',
            		data: {
            			id: data.id,
            		},
            		url: '../teacher/delete.html',
            		success:function(res) {
            			if (res == true) {
        					//top.layer.msg("删除成功！");
        					tableIns.reload();
        	                layer.close(index);
        				}else {
        					layer.msg(res, {icon: 5,time:1000});
        				}
            		}
            	});
            });
        }
    });
});