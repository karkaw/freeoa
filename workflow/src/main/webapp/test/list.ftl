<#macro list data="" >
<script>
    $(function(){
        var addBtn =  $("div div button");
        addBtn.bind("click",function(){

        });
    });
</script>
<div>
    <div>
        <button name="addBtn">新增</button>
    </div>
    <table>
        <thead>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>地址</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <tr id ='1'>
                <td>1</td>
                <td>张三</td>
                <td>18</td>
                <td>111111111111111111</td>
                <td><button>修改</button><button>删除</button></td>
            </tr>
            <tr id ='2'>
                <td>1</td>
                <td>王王</td>
                <td>20</td>
                <td>22222222</td>
                <td><button>修改</button><button>删除</button></td>
            </tr>
        </tbody>

    <table>
</div>
</#macro>