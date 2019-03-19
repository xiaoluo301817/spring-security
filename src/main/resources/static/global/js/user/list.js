layui.use('table', function () {
    var table = layui.table;

    /*设置表头的数据*/
    var dataJSON = [
        {type: 'checkbox', fixed: 'left'}
        , {field: 'id', title: '序号', width: 80, fixed: 'left', unresize: true, sort: true}
        , {field: 'username', title: '用户名', width: 120, edit: 'text'}
        , {field: 'email', title: '邮箱', width: 300, edit: 'text'}
        , {field: 'sex', title: '性别', width: 80}
        , {field: 'phone', title: '手机号码', width: 300}
        , {field: 'adress', title: '联系方式', width: 200}
        , {field: 'status', title: '状态', width: 100, templet: '#statusTemplet'}
        , {field: 'updateDate', title: '登录时间', width: 200, sort: true, templet: '#updateDateTime'}
        , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 150}
    ];
    var jsonARR = [dataJSON];

    const tableIns = table.render({
        elem: '#user'
        , url: '/user/getPageDataList'
        , toolbar: '#toolbarDemo'
        , title: '用户数据表'
        , cols: jsonARR
        , page: true
    });

    //头工具栏事件
    table.on('toolbar(user)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'getCheckData':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选' : '未全选');
                break;
        }
        ;
    });

    //监听行工具事件
    table.on('tool(user)', function (obj) {
        var data = obj.data;
        //console.log(obj)
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            layer.prompt({
                formType: 2
                , value: data.email
            }, function (value, index) {
                obj.update({
                    email: value
                });
                layer.close(index);
            });
        }
    });



    $('#searchUser').click(()=>{
        searchUser();
    })



    function searchUser(){
        var user =$('input[name="username"]').val();
        if (user=="" || user==null){
            layer.open({
                title: '提示信息'
                ,content: '查询的用户名不能为空'
            });
            return false;
        }
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                username: user
            },
            page: {
                curr: 1 //重新从第 1 页开始
            }
        });

        $('input[name="username"]').val('');
        $('input[name="username"]').focus();

    }
});