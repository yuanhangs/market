$(function () {
    //单击事件
    $("#passwordSave").click(function () {
        //获得值
        var oldPassword = $("#oldPassword").val();
        var newPassword = $("#newPassword").val();
        var reNewPassword = $("#reNewPassword").val();
        //判断
        if (newPassword == oldPassword) {
            alert("*新密码和老密码不能一致，请重新输入！");
            return;
        } else if (newPassword != reNewPassword) {
            alert("*确认密码和新密码不一致，请重新输入！");
            return;
        } else {
            //oldPassword传过去判断于数据库中密码是否一致，newPassword用于替换
            $.post("updatePassword.user", {"oldPassword": oldPassword, "newPassword": newPassword}, function (msg) {
                if (msg == 1) {
                    window.location.href = "login.jsp";
                } else {
                    alert("*输入的原密码不正确");
                    return;
                }
            });
        }
    });
});