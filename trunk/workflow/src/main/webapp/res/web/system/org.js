$(function(){
    //添加
    var widget = UI.get("orgWidget");
    var addbut = widget.addButton('addbnt', '添加');
    addbut.unbind("click").bind("click",function(){
        UI.get("orgForm").removeValues();
        var grid = UI.get("orgGrid");
        try{
            var parentCode = grid.getSelection(); //获取grid勾选中的数据
            if(parentCode != null){
                $("#parentId").val(parentCode.code) ;
            }
        }catch(e) {
            console.log(e);
        }
        UI.get("orgModle").show();//显示Modal层
    });

    //提交保存
    var submitBtn = UI.get("orgModle").getSubmitBtn();//获取Madal的提交按钮
    submitBtn.unbind("click").bind("click",function(){
        var params = {}
        UI.get("orgForm").setParam(params).submit(function(){ //提交数据
            UI.get("orgGrid").reload();       //提交完成刷新grid
            UI.get("orgTree").reload();     //重新加载树
            UI.get("orgModle").hide();      //关闭Modal
        });
    });

    //多选删除
    var delbut = widget.addButton('delbnt', '删除');
    delbut.unbind("click").bind("click",function(){
        var ids = UI.get("orgGrid").getSelectValues(true);
        if(ids != null){
            deleteResource(ids);
        }
    });

    //删除按钮
    $("#orgGrid").find("tr a:eq(1)").bind("click",function(){
        var id = $(this).parent().parent("tr").find("input:checkbox").val();
        if(id != null){
            deleteResource([id]);
        }
    });

    //提交删除数据
    var deleteResource = function(data){
        var form = new UI.Form({url:'delete.do'});
        form.setParam({"ids":data});
        form.submit(function(){
            UI.get("orgGrid").reload();   //提交完成刷新grid
            UI.get("orgTree").reload();     //重新加载树
        })
    };

    //删除操作的按钮
    $(".form-group .icon-remove").bind("click",function(){
        var div = $(this).parent().parent(".form-group");
        div.remove();
    });

    //修改填充表单数据
    $("#orgGrid").find("tr a:eq(0)").bind("click",function(){
        $("#orgOperForm .form-group:gt(0)").each(function(idx,el){
            $(this).remove();
        });

        UI.get("orgModle").show();//显示Modal层
        var id = $(this).parent().parent("tr").find("input:checkbox").val();
        var form = new UI.Form({id:"orgForm",url:'list.do'});
        form.setParam({_id:id});
        form.fillForm(fillDataSuccess);
    });

    var fillDataSuccess = function(data){
        var _data ;
        if(typeof data.msgType != ""){
            _data =  data.result;
        }
        _data = _data[0] ;
        for(var d in _data){
            try{
                $("*[name="+d+"]",$("#orgForm")).val(_data[d]);
            }catch (e){}
        }
    };

});