layui.use(['form','layer','layedit','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        layedit = layui.layedit,
        $ = layui.jquery;

    form.verify({
    	title : function(val){
            if(val == ''){
                return "标题不能为空";
            }
        }
    });
    
    
    //设置富文本中图片上传接口
    layedit.set({
    	  uploadImage: {
    	    url: '/CMS/notice/uploadImg.html',
    	    type: 'post'
    	  }
    });
    //创建一个编辑器
    var editIndex = layedit.build('notice_content',{
        height : 535,
        uploadImage : { url : "/CMS/notice/uploadImg.html" }
    });
    //用于同步编辑器内容到textarea
    layedit.sync(editIndex);
    
    form.on("submit(addNotice)",function(data){
    	console.log(data);
    	console.log($("#id").val());
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.ajax({
    		type: 'post',
    		url: "/CMS/notice/add.html",
    		data : {
	        	 opType: $("#opType").val(),
	        	 id : $("#id").val(),
	             title : $("#title").val(),  //标题
	             author: $("#author").val(),
	             content : layedit.getContent(editIndex).split('<audio controls="controls" style="display: none;"></audio>')[0],  //文章内容
	             auth : $("#status").val()
    		},
    		success: function(res){
	        	 top.layer.close(index);
	        	 if (res == true) {
	        		 top.layer.msg("保存成功！");
	        		 layer.closeAll("iframe");
	        		 parent.location.reload();
	        	 } else {
	        		 layer.msg("操作失败！", {icon: 5,time:1000});
	        	 }
    		},
    		error:function() {
    			layer.msg("操作失败！", {icon: 5,time:1000});
    			top.layer.close(index);
    		}
        });
        return false;
    });

});