/**
 * Created by yaling.he on 2015/11/17.
 */

//供应商管理页面上点击删除按钮弹出删除框(providerList.html)
$(function () {
    $('.removeProvider').click(function () {
        $('.zhezhao').css('display', 'block');
        $('#removeProv').fadeIn();
    });
});

$(function () {
    $('#no').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeProv').fadeOut();

    });

});



//订单管理页面上点击删除按钮弹出删除框(billList.html)
$(function () {
    $('.removeBill').click(function () {
        $('.zhezhao').css('display', 'block');
        $('#removeBi').fadeIn();
    });
});

$(function () {
    $('#no').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeBi').fadeOut();
    });
});

///////////////////////////////
//用户管理页面上点击删除按钮弹出删除框(userList.html)
var id="";
$(function () {
    $('.removeUser').click(function () {
        id=$(this).prev("[ type='hidden']").val();
       // alert(id);
        $('.zhezhao').css('display', 'block');
        $('#removeUse').fadeIn();

    });
});

$(function () {
    $('#no').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeUse').fadeOut();
    });

    $('#yes').click(function () {
        alert("idd===>"+id);
        document.location.href="removeUser.user?delUserId="+id;
        $('.zhezhao').css('display', 'none');
        $('#removeProv').fadeOut();
    });
});

//del


// function funDel(id){
//     if(window.confirm("****您确定要删除吗?")){
//         document.location.href="removeUser.user?delUserId="+id;
//     }
// }