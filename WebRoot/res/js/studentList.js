layui.use(['form','layer','table','laytpl','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        upload = layui.upload,
        table = layui.table;

    //学生列表
    var tableIns = table.render({
        elem: '#studentList',
        url : '/CMS/student/list.html',
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
        id : "studentListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '学号', sort:true, minWidth:150, align:"center"},
            {field: 'name', title: '姓名',minWidth:120, align:'center'},
            {field: 'sex', title: '性别', minWidth:60, align:'center'},
            {field: 'admissionDate', title: '入学时间', minWidth:150, align:'center'},
            {field: 'major', title: '专业', minWidth:150, align:'center'},
            {field: 'grade', title: '班级', minWidth:150, align:'center'},
            {field: 'education', title: '学历层次', minWidth:120, align:'center'},
            {title: '操作', width:200, templet:'#studentListBar',fixed:"right",align:"center"}
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

    //编辑or添加用户
    function addUser(edit){
    	var title = "添加学生";
    	if (edit) title = "编辑学生信息";
    	//console.log(edit);
        var index = layui.layer.open({
            title : title,
            type : 2,
            content : "/CMS/student/addPage.html",
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
                    body.find(".sex input[value="+edit.sex+"]").prop("checked","checked");  //性别
                    body.find("#admissionDate").val(edit.admissionDate);
                    body.find(".education").val(edit.education);
                    body.find("#grade").val(edit.grade);
                    body.find("#major").val(edit.major);
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
        //改变窗口大小时，重置弹窗的宽高
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        });
    }
    //添加用户
    $(".addUser_btn").click(function(){
        addUser();
    });

    //批量导入
    upload.render({
        elem: '#upload_btn' //绑定元素（按钮）
        ,url: '/CMS/student/import.html' //后台接收接口
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
        var checkStatus = table.checkStatus('studentListTable'),
            data = checkStatus.data,
            stuId = "";
        if(data.length > 0) {
            for (var i in data) {
            	stuId += data[i].id + ",";
            }
            //console.log(stuId);
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
            	$.ajax({
            		type: 'get',
            		data: {
            			stuIds : stuId
            		},
            		url: '../student/deleteList.html',
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
    		url: '../student/resetPswd.html',
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
    table.on('tool(studentList)', function(obj){
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
            		url: '../student/delete.html',
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
