<script>
    T.Model = function(){
        var  _this = this ;

        this.proid = "${proid!}" ;

        this.jsonData = null ;

        this.init = function(){
            $.post("../../frame/template/data.do",{proid:_this.proid},function(data){
                _this.jsonData = data ;
                _this.fullForm();
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
                            var tr = $("tr:eq("+(i+1)+")",table)[0];
                            if(i>0){
                                var otr = $("tr:eq("+i+")",table)[0];
                                tr = otr.clone(true);
                                tr.prependTo(table);
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

    $(function(){
        T.ele = new T.Model();

        $("#addBtn").bind("click",function(){

        });
    });
</script>

<div id="applyForm">
    <p>  <span new="">请假/休假申请单</span> </p>
    <div align="center">
        <table class="listTab0">
            <tbody>
            <tr>
                <td>申请人</td><td><input      name="tec-test:user" type="text" value=""/></font></td>
                <td>申请日期</td><td><input     name="tec-test:applyDate" type="text" value=""/></font></td>
            </tr>
            <tr>
                <td colspan="6">
                    <div><input type="button" value="添加" id="addBtn"></input></div>
                    <table border="1" type="list" name="tec-test:testList"><tr><td>姓名</td><td>年龄</td><td>操作</td></tr>
                        <tr>
                            <td><input name="tec-test:testList:person:name" value=''/></td>
                            <td><input name="tec-test:testList:person:age" value=''/></td>
                            <td><input type="button" value="删除"></input> <input type="button" value="修改"></input> </td>
                        </tr>
                    </table>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
