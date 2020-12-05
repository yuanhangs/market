$(function () {
    //单击事件
    $("#userAddSave").click(function () {
        var userId = $("#userId").val();
        var userName = $("#userName").val();
        var userpassword = $("#userpassword").val();
        var userRemi = $("#userRemi").val();
        var sex = $("input[name='sex']:checked").val();
        var sang_Calender = $("#sang_Calender").val();
        var userphone = $("#userphone").val();
        var userAddress = $("#userAddress").val();
        var userlei = $("input[name='userlei']:checked").val();

        if (userpassword == userRemi) {
            $.post("userAdd.user", {
                "userId": userId,
                "userName": userName,
                "userpassword": userpassword,
                "sex": sex,
                "sang_Calender": sang_Calender,
                "userphone": userphone,
                "userAddress": userAddress,
                "userlei": userlei
            }, function (msg) {
                if (msg == 1) {
                    window.location.href = "welcome.jsp";
                } else if (msg == 2) {
                    alert("用户编号已存在，请重新输入！");
                } else if (msg == 3) {
                    alert("用户名称已存在，请重新输入！");
                } else {
                    alert("出错啦！");
                }
            });
        } else {
            alert("用户密码和确认密码，输入不一致！");
        }


    });
});