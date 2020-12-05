$(function () {
    //单击事件
    $("#login").click(function () {
        //获得值
        var name = $("#user").val();
        var pass = $("#mima").val();
        //判断
        if (name == "") {
           alert("用户名不能为空");
            return;
        }
        if (pass == "") {
            alert("密码不能为空");
            return;
        } else {  //不为空进行get处理
            $.post("login.user", {"name": name, "pass": pass}, function (msg) {
                if (msg == 1) {
                    window.location.href = "welcome.jsp";
                } else {
                    alert("用户名或者密码不正确");
                }
            });
        }
    });
});