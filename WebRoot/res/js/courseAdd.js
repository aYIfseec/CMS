layui.use(['form','layer','laydate'],function(){
    var form = layui.form,
    	laydate = layui.laydate,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    
    laydate.render({ elem: '#startDate' });
    laydate.render({ elem: '#endDate' });
    
    var inputObj = $("#teacherDiv").find("input");
    inputObj.bind("keyup", function (event){
    	 if (event.keyCode == "13") {
 	      	loadTeacher();
         }
    });
    function loadTeacher() {
    	var searchKey = inputObj.val();
      	$.ajax({
      		type: 'get',
      		url: '../teacher/listForSelect.html',
      		data:{searchKey: searchKey},
      		timeout:1500,
      		success:function(msg) {
      			var select = $('#teacherSelect');
      			var options = "";//select.html();
      			var data = msg.data;
      			for (var i in data) {
      				options += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
      			}
      			select.html(options);
      			form.render('select');
      			//渲染后要重新绑定
      			inputObj = $("#teacherDiv").find("input");
      			inputObj.click();
      			inputObj.bind("keyup", function (event){
      				if (event.keyCode == "13") {
      		        	loadTeacher();
      		        }
      		    });
      			//课程的搜索绑定
      			inputObj2 = $("#baseCourseDiv").find("input");
      			inputObj2.bind("keyup", function (event){
      				if (event.keyCode == "13") {
      		        	loadCourse();
      		        }
      		    });
      		}
      	});
    }
    
    var inputObj2 = $("#baseCourseDiv").find("input");
    inputObj2.bind("keyup", function (event){
    	if (event.keyCode == "13") {
        	loadCourse();
        }
    });
    function loadCourse() {
    	var searchKey = inputObj2.val();
      	$.ajax({
      		type: 'get',
      		url: '/CMS/basecourse/listForSelect.html',
      		data:{searchKey: searchKey},
      		timeout:1500,
      		success:function(msg) {
      			var select = $('#baseCourseSelect');
      			var options = "";
      			var data = msg.data;
      			for (var i in data) {
      				options += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
      			}
      			select.html(options);
      			form.render('select');
      			//渲染后要重新绑定
      			inputObj = $("#teacherDiv").find("input");
      			inputObj.bind("keyup", function (event){
      				if (event.keyCode == "13") {
      		        	loadTeacher();
      		        }
      		    });
      			//课程的搜索绑定
      			inputObj2 = $("#baseCourseDiv").find("input");
      			inputObj2.click();
      			inputObj2.bind("keyup", function (event){
      				if (event.keyCode == "13") {
      		        	loadCourse();
      		        }
      		    });
      		}
      	});
    }
    
    $(document).ready(function (){
    	if (inputObj.val() == null || inputObj.val() == "") {
        	loadTeacher();
        	loadCourse();
        }
    });
    
    
    form.on("submit(addCourse)",function(data){
    	//弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	var id = $("#id").val();
        $.ajax({
    		type: 'post',
    		url: '../course/add.html',
    		data: {
				id : id,
	            tId : $("#teacherSelect").val(),
	            baseCourseId: $("#baseCourseSelect").val(),
	            startDate: $("#startDate").val(),
	            endDate: $("#endDate").val(),
	            classHour: $("#classHour").val(),
	            testMode : $("#testMode").val(),
	            studentNum : $("#studentNum").val()
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