$(function(){
    //添加
    var widget = UI.get("userWidget");
    var addbut = widget.addButton('addbnt', '添加');
    addbut.unbind("click").bind("click",function(){
        $("#resOperForm .form-group:gt(0)").each(function(){
            $(this).remove();
        });
        UI.get("resForm").removeValues();
        var grid = UI.get("resGrid");
        try{
            var parentCode = grid.getSelection(); //获取grid勾选中的数据
            if(parentCode != null){
                $("#parentId").val(parentCode.code) ;
            }
        }catch(e) {
            console.log(e);
        }
        UI.get("resModle").show();//显示Modal层
    });

    //提交保存
    var submitBtn = UI.get("resModle").getSubmitBtn();//获取Madal的提交按钮
    submitBtn.unbind("click").bind("click",function(){
        var func =[];
        $("#resOperForm .form-group").each(function(idx,el){
            func.push(UI.getFormParam($(el)));
        });
        var params = {"func":func}
        UI.get("resForm").setParam(params).submit(function(){ //提交数据
            UI.get("resGrid").reload();       //提交完成刷新grid
            UI.get("resTree").reload();     //重新加载树
            UI.get("resModle").hide();      //关闭Modal
        });
    });

    //多选删除
    var delbut = widget.addButton('delbnt', '删除');
    delbut.unbind("click").bind("click",function(){
        var ids = UI.get("resGrid").getSelectValues(true);
        if(ids != null){
            deleteResource(ids);
        }
    });

    //删除按钮
    $("#resGrid").find("tr a:eq(1)").bind("click",function(){
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
            UI.get("resGrid").reload();   //提交完成刷新grid
            UI.get("resTree").reload();     //重新加载树
        })
    }

    //删除操作的按钮
    $(".form-group .icon-remove").bind("click",function(){
        var div = $(this).parent().parent(".form-group");
        div.remove();
    });

    //添加操作按钮
    var resModal =UI.get("resModle");
    var addOperBtn = resModal.addButton("addOperBtn","添加操作");
    addOperBtn.unbind("click").bind("click",function(){
        var div = $(".panel.panel-default").eq(1).find(".form-group:eq(0)");
        var ndiv = div.clone(true);
        ndiv.find("input").val("");
        ndiv.appendTo(div.parent(".panel-body"));
        ndiv.show();
    });

    //修改填充表单数据
    $("#resGrid").find("tr a:eq(0)").bind("click",function(){
        $("#resOperForm .form-group:gt(0)").each(function(idx,el){
            $(this).remove();
        });

        UI.get("resModle").show();//显示Modal层
        var id = $(this).parent().parent("tr").find("input:checkbox").val();
        var form = new UI.Form({id:"resForm",url:'list.do'});
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
                if(d == "func"){
                    var func = _data["func"] ;
                    for(var i =1 ;i< func.length ;i++){
                        $("#addOperBtn").click();
                    }
                    $("#resOperForm .form-group").each(function(idx,el){
                        var _func = func[idx];
                        for(var f in _func){
                            $("*[name="+f+"]",$(this)).val(_func[f]);
                        }
                    });
                }else{
                    $("*[name="+d+"]",$("#resForm")).val(_data[d]);
                }
            }catch (e){}
        }
    };

});