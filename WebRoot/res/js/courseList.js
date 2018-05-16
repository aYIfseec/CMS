layui.use(['form','layer','table','laytpl','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        upload = layui.upload,
        table = layui.table;
    var opTable = 0;//记录目前是哪个数据table显示
    
    //courseList列表
    var tableIns = null;
    loadCourse();
    function loadCourse() {
      tableIns = table.render({
        elem: '#courseList',
        url : '../course/list.html',
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
        id : "courseListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '课程编号', sort:true, minWidth:80, align:"left"},
            {field: 'courseName', title: '课程名',minWidth:120, align:'left'},
            {field: 'teacherName', title: '任课教师',minWidth:120, align:'left'},
            {field: 'startDate', title: '开始时间', minWidth:100, align:'center'},
            {field: 'endDate', title: '结束时间', minWidth:100, align:'center'},
            {field: 'classHour', title: '课时', minWidth:100, align:'center'},
            {field: 'testMode', title: '考核方式', minWidth:100, align:'center'},
            {field: 'studentNum', title: '最大人数', minWidth:100, align:'center'},
            {field: "choiceNum", title: "已选（人）", minWidth:100, align:"center"},
            {title: '操作', width:160, templet:'#courseListBar',fixed:"right",align:"center"}
        ]]
      });
    }
    
    //baseCourseList列表
    var baseCourseTableIns = null;
    function loadBaseCourseList() {
      baseCourseTableIns= table.render({
        elem: '#courseList',
        url : '../basecourse/list.html',
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
        id : "baseCourseListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '课程编号', sort:true, minWidth:80, align:"left"},
            {field: 'name', title: '课程名',minWidth:120, align:'left'},
            {field: 'synopsis', title: '课程简介', minWidth:100, align:'left'},
            {title: '操作', width:160, templet:'#courseListBar',fixed:"right",align:"center"}
        ]]
      });
    }

    //搜索
    $(".search_btn").on("click",function(){
    	//对course的搜索
    	if ($("#searchType").val() == 0) {
    		opTable = 0;
    		if (tableIns == null) {
    			loadCourse();
    		} else {
        		tableIns.reload({
        			page: {
        				curr: 1 //重新从第 1 页开始
        			},
        			where: {
        				searchKey: $(".searchVal").val()  //搜索的关键字
        			}
        		});
    		}
    	} else {
    		opTable = 1;
    		if (baseCourseTableIns == null) {
    			loadBaseCourseList();
    		} else {
        		baseCourseTableIns.reload({
        			page: {
        				curr: 1
        			},
        			where: {
        				searchKey: $(".searchVal").val()
        			}
        		});
    		}
    	}
    });

    //编辑or添加开设课程
    function addCourse(edit){
    	var title = "新增开设课程";
    	if (edit) title = "编辑开设课程";
        var index = layui.layer.open({
            title : title,
            type : 2,
            content : "../course/addPage.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find("#id").val(edit.id);
                    
                    var select = body.find("#teacherSelect");
                    select.html("<option id='defaultTeacher' value='"+edit.tId+"' >"+edit.teacherName+"</option>");
                    select = body.find("#baseCourseSelect");
                    select.html("<option id='defaultCourse' value='"+edit.baseCourseId+"' >"+edit.courseName+"</option>");
                    
                    body.find("#startDate").val(edit.startDate);
                    body.find("#endDate").val(edit.endDate);
                    body.find("#classHour").val(edit.classHour);
                    body.find("#testMode").val(edit.testMode);
                    body.find("#studentNum").val(edit.studentNum);
                    body.find('#reset').attr("disabled","disabled").addClass("layui-disabled");
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回课程列表', '.layui-layer-setwin .layui-layer-close', {
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
    
    function addBaseCourse(edit){
    	var title = "新增基本课程";
    	if (edit) title = "编辑基本课程";
        var index = layui.layer.open({
            title : title,
            type : 2,
            area: ['500px','350px'],
            content : "../basecourse/addPage.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find("#id").val(edit.id);
                    body.find("#name").val(edit.name);
                    body.find("#synopsis").val(edit.synopsis);
                    body.find('#reset').attr("disabled","disabled").addClass("layui-disabled");
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回课程列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500);
            }
        });
    }
    //添加
    $(".addNews_btn").click(function(){
        addCourse();
    });
    $("#addBaseCourse").click(function(){
    	addBaseCourse();
    });
    

    //批量导入
    var uploadInst = upload.render({
        elem: '#upload_btn' //绑定元素
        ,url: '../basecourse/import.html' //上传接口
        ,accept: 'file'
        ,exts:'xls|xlsx'
        ,before: function(obj){
            layer.load(); //上传loading
        }
        ,done: function(res){
        	layer.closeAll('loading'); //关闭loading
        	if (opTable == 1)  {
        		baseCourseTableIns.reload();
			}
        }
        ,error: function(){
        	layer.closeAll('loading'); //关闭loading
        }
    });
    //批量删除
    $(".delAll_btn").click(function(){
    	var checkStatus;
    	var url;
        if (opTable == 0)  {
        	checkStatus = table.checkStatus('courseListTable');
        	url = '../course/deleteList.html';
        } else {
        	checkStatus = table.checkStatus('baseCourseListTable');
        	url = '../basecourse/deleteList.html';
        }
        
        var data = checkStatus.data, cIds = "";
        if(data.length > 0) {
            for (var i in data) {
            	cIds += data[i].id + ",";
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
            	$.ajax({
            		type: 'get',
            		data: {
            			cIds : cIds
            		},
            		url: url,
            		timeout:2000,
            		success:function(res) {
                    	if (res == true) {
                    		if (opTable == 0)  {
            					tableIns.reload({page: {
                                    curr: 1 //重新从第 1 页开始
                                }});
            				} else {
            					baseCourseTableIns.reload({page: {
                                    curr: 1 //重新从第 1 页开始
                                }});
            				}
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
            layer.msg("请选择需要删除的行");
        }
    });
    
    //列表操作
    table.on('tool(courseList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            if (opTable == 0)  {
            	addCourse(data);
            } else {
            	addBaseCourse(data);
            }
        } else if(layEvent === 'del'){ //删除
        	var url;
        	if (opTable == 0)  {
        		url = '../course/delete.html';
            } else {
            	url = '../basecourse/delete.html';
            }
            layer.confirm('确定删除？',{icon:3, title:'提示信息'},function(index){
            	$.ajax({
            		type: 'get',
            		data: {
            			id: data.id,
            		},
            		url: url,
            		success:function(res) {
            			if (res == true) {
            				if (opTable == 0)  {
            					tableIns.reload();
            				} else {
            					baseCourseTableIns.reload();
            				}
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
