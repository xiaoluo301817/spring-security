
var handler2 = function (captchaObj) {
    $("#submit2").click(function (e) {

        var username = $("#username").val();
        var password = $("#password").val();
        if (username == null || username == "") {//判断用户名是否输入
            alert("用户名不能为空");
            return false;
        }
        if (password == null || password == "") {//判断密码是否输入
            alert("密码不能为空");
            return false;
        }

        var result = captchaObj.getValidate();
        if (!result) {
            layui.use(['layer','form'], function(){
                layer.open({
                    title: '提示信息',
                    content: '当前元素尚未验证，请验证后在登陆' //这里content是一个普通的String
                });
            });

        } else {
            $.ajax({
                url: '/login/verifyLogin',
                type: 'POST',
                dataType: 'json',
                data: {
                    username: username,
                    password: password,
                    geetest_challenge: result.geetest_challenge,
                    geetest_validate: result.geetest_validate,
                    geetest_seccode: result.geetest_seccode
                },
                success: function (data) {
                    if (data.status === 'success') {
                        window.location.href = "/user/gotoIndex"
                    } else if (data.status === 'fail') {

                        var flag=1;
                        layui.use(['layer','form'], function(){

                            layer.open({
                                title: '提示信息',
                                content: '输入的账户或是密码错误' //这里content是一个普通的String
                            });
                            flag =2;
                        });
                        if (flag=2) {
                            $('#username').val('');
                            $('#password').val('');
                            captchaObj.reset();
                            $('#username').focus();
                        }

                    }
                }
            })
        }
        e.preventDefault();
    });
    // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
    captchaObj.appendTo("#captcha2");
    captchaObj.onReady(function () {
        $("#wait2").hide();
    });
    // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
};
$.ajax({
    url: "/gt/startCaptcha?t=" + (new Date()).getTime(), // 加随机数防止缓存
    type: "get",
    dataType: "json",
    success: function (data) {
        // 调用 initGeetest 初始化参数
        // 参数1：配置参数
        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
        initGeetest({
            gt: data.gt,
            challenge: data.challenge,
            new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
            offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
            product: "popup", // 产品形式，包括：float，popup
            width: "100%"
            // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
        }, handler2);
    }
});




