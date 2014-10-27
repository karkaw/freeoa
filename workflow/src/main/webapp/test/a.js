T = function(){
    var  _this = this ;

    this.proid = "${proid!}" ;

    this.jsonData = null ;

    this.dataChange = {addValue:{},editValue:{},delValue:{}} ;

    this.init = function(){
        $.post("../../frame/template/data.do",{proid:_this.proid},function(data){
            _this.jsonData = data ;
            _this.fullForm();
        });
    };

    //监听数据改变的事件
    this.dataChangeListen = function(){
        var table = $("table[type=list]");

        $("#addBtn").bind("click",function(e){
            var otr = $("tr:eq("+($("tr",table).size()-1)+")",table);
            var tr = otr.clone(true);
            $("input",tr).val("");
            tr.insertAfter(otr);

        });

        $("tr",table).each(function(idx,ele){
            $("input[type=button]:eq(0)",$(ele)).bind("click",function(){
                var tr = $(this).parent().parent("tr");
                tr.remove();
            })
        });

        $("input",$("#applyForm")).each(function(idx,ele){
            $(ele).focus(function(){
                var oval = $(this).val();
                $(this).blur(function(){
                    var nval = $(this).val();
                    if(oval != nval){
                        var title = $(this).attr("title");
                        T.dataChange.editValue[title] = oval + ":" + nval ;
                    }
                })
            });
        });
    };


    //在模板中必须实现的方法，用来填充表单数据
    this.fullForm=function(){
        if(_this.jsonData != null){
            for(var d in _this.jsonData){
                var value = _this.jsonData[d] ;
                if(d == 'tec-test:testList'){
                    //保存List数据
                    var table = $("table[type=list]");
                    for(var  i = 0 ; i< value.length ; i++){
                        var otr = $("tr:eq("+($("tr",table).size()-1)+")",table);
                        if(i>0){
                            var tr = otr.clone(true);
                            tr.insertAfter(otr);
                        }
                        var tData  = value[i] ;
                        for(var d in tData){
                            $("input[name='"+d+"']",tr).val(tData[d]);
                        }
                    }
                }else{
                    $("#applyForm input[name='"+d+"']").val(value);
                }
            }
        }
    };

    //在模板中必须实现 的方法，用来获取表单的数据
    this.getFormData=function(){
        var datas = Sj.getFormValues($("#applyForm"));

        var table = $("table[type=list]");
        var name = table.attr("name");
        var property = $("tr",table);

        var list = new Array();
        property.each(function(idx,ele){
            var formData = Sj.getFormValues($(this));
            list.push(formData);
        });

        datas[name] = list ;
        return datas;
    };

    this.init();
}