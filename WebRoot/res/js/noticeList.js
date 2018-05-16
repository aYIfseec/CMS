layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#noticeList',
        url : '/CMS/notice/list.html',
        request: {
        	pageName: 'curr' 
        	,limitName: 'nums'
        },
        where: {
            searchKey: $(".searchVal").val()
        },
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 10,
        limits : [10,15,20,25],
        id : "noticeListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', width:60, align:"center"},
            {field: 'title', title: '公告标题', width:350},
            {field: 'author', title: '发布者', align:'center'},
            {field: 'auth', title: '浏览权限',  align:'center',templet:"#noticeStatus"},
            {field: 'date', title: '发布时间', align:'center', minWidth:110 },
            {title: '操作', width:170, templet:'#noticeListBar',fixed:"right",align:"center"}
        ]]
    });


    //搜索
    $(".search_btn").on("click",function(){
        table.reload("noticeListTable",{
            page: {curr: 1},
            where: { searchKey: $(".searchVal").val() }
        });
    });

    //编辑公告
    function addNotice(edit){
        var index = layui.layer.open({
            title : "编辑公告",
            type : 2,
            content : "/CMS/notice/addPage.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find("#opType").val(1);
                	body.find("#id").val(edit.id);
                	body.find("#title").val(edit.title);
                    body.find("#status").val(edit.auth);
                    body.find("#author").val(edit.author);
                    body.find("#notice_content").val(edit.content);
                    form.render();
                } else {
                	body.find("#opType").val(0);
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回公告列表', '.layui-layer-setwin .layui-layer-close', {
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
    $(".addNotice_btn").click(function(){
        addNotice();
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('noticeListTable'),
            data = checkStatus.data,
            nIds = "";
        if(data.length > 0) {
            for (var i in data) {
            	nIds += data[i].id + ",";
            }
            layer.confirm('确定删除选中的公告？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/CMS/notice/deleteList.html",{
                     nIds : nIds  //将需要删除的newsId作为参数传入
                },function(data){
                	tableIns.reload({page: {curr: 1 }});
                	layer.close(index);
                });
            });
        }else{
            layer.msg("请选择需要删除的文章");
        }
    });

    //列表操作
    table.on('tool(noticeList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addNotice(data);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此公告？',{icon:3, title:'提示信息'},function(index){
                 $.get("/CMS/notice/delete.html",{
                     id : data.id
                 },function(res){
                    tableIns.reload();
                    layer.close(index);
                 });
            });
        } else if(layEvent === 'look'){ //查看
        	var index = layui.layer.open({
                title : "公告详情",
                type : 2,
                content : "/CMS/notice/look.html",
                success : function(layero, index){
                    var body = layui.layer.getChildFrame('body', index);
                    body.find("#title").html(data.title);
                    body.find("#author").html(data.author);
                    body.find("#date").html(data.date);
                    body.find("#content").html(data.content);
                    
                    setTimeout(function(){
                        layui.layer.tips('点击此处返回公告列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500);
                }
            });
        	layui.layer.full(index);
            window.sessionStorage.setItem("index",index);
            $(window).on("resize",function(){
                layui.layer.full(window.sessionStorage.getItem("index"));
            });
        }
    });
});