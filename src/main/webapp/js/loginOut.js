$(function () {
    //单击事件
    $(".out").click(function () {
            $.post("loginOut.user", {}, function (msg) {
                if (msg == 1) {
                    window.location.href = "login.jsp";
                } else {
                    alert("会话没有删除！");
                }
            });

    });
});