package org.aly.sjc.servlet;

import org.aly.sjc.biz.UserinfoBiz;
import org.aly.sjc.biz.impl.UserinfoBizImpl;
import org.aly.sjc.entity.UserInfo;
import org.aly.sjc.entity.UserInfoAndType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserinfoServlet", urlPatterns = "*.user")
public class UserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //获取请求路径   /add.do
        String path = request.getServletPath();

        //截取 add
        String pathName = path.substring(1, path.lastIndexOf("."));

        //利用反射机制
        try {
            //pathName方法名; 后面2个是参数
            Method method = getClass().getDeclaredMethod(pathName, HttpServletRequest.class, HttpServletResponse.class);

            //调用
            method.invoke(this, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭
        out.flush();
        out.close();
    }
    /*
     * 以下全部是方法======================================================================
     * */

    /**
     * 登录
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //请求数据
        //2.获取参数值
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        //3.封装
        UserInfo user = new UserInfo(name, pass);
        //实例化对象
        UserinfoBiz biz = new UserinfoBizImpl();
        try {
            //调用方法,返回的是个对象
            UserInfo userInfo = biz.isLogin(user);
            if (userInfo.getUserName() != null) {
                System.out.println(userInfo);
                //会话对象
                HttpSession session = request.getSession();
                session.setAttribute("name", userInfo);
                //响应结果
                out.print(1);
            } else {
                //响应结果
                out.print(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭
        out.flush();
        out.close();
    }

    /**
     * 退出
     */
    protected void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //会话对象
        HttpSession session = request.getSession();
        session.removeAttribute("name");
        if (session.getAttribute("name") == null) {
            //响应结果
            out.print(1);
        } else {
            //响应结果
            out.print(0);
        }
        //关闭
        out.flush();
        out.close();
    }

    /**
     * 修改密码
     */
    protected void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //请求数据
        //2.获取参数值
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        //3.获取会话中储存的密码，获取会话值要UserInfo来接
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("name");
        String passWord = userInfo.getPassWord();
        //4.得到用户的ID
        String id = userInfo.getUserId();
        //判断前台输入老密码和会话中的密码是否一致,一致修改密码
        if (passWord.equals(oldPassword)) {
            //5.封装
            UserInfo user = new UserInfo(newPassword, id, null);
            //6.调用方法修改密码
            UserinfoBiz biz = new UserinfoBizImpl();
            try {
                System.out.println(user);
                biz.updatePassword(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //移除会话
            session.removeAttribute("name");
            //响应结果
            out.print(1);
        } else {
            //响应结果
            out.print(0);
        }
        //关闭
        out.flush();
        out.close();
    }


    /**
     * 添加用户
     */
    protected void userAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //请求数据
        /*1.获取参数值=============*/
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userpassword = request.getParameter("userpassword");

        String sex = request.getParameter("sex");
        Integer newSex=new Integer(sex);//str 转化成 Integer

        //用户的出生日期，精确到时分秒，需要修改，值截取年月日
        String sang_Calender = request.getParameter("sang_Calender");
        String date1=sang_Calender.substring(0,10);

        String userphone = request.getParameter("userphone");
        String userAddress = request.getParameter("userAddress");
        String userlei = request.getParameter("userlei");
        //打印一波
        System.out.println("newSex=" + newSex);
        System.out.println("date1=" + date1);
        System.out.println("userlei=" + userlei);
        /*2.获取数据库中所有账户的账号和密码*/
        //实例化对象
        UserinfoBiz biz = new UserinfoBizImpl();
        //参数
        Object[] obj = {""};
        try {
            List<UserInfo> lists = biz.all(obj);
            //循环判断
            for (int i = 0; i < lists.size(); i++) {
                //在结合找是否存在相同账号
                if (lists.get(i).getUserId().equals(userId)) {
                    //响应结果
                    out.print(2);
                    break;
                    //在结合找是否存在相同账号名称
                } else if (lists.get(i).getUserName().equals(userName)) {
                    //响应结果
                    out.print(3);
                    break;
                }else  if(i==lists.size()-1){
                   //调用插入方法
                    //.String转换成Date类型
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = ft.parse(date1);
                    //UserInfo 实例化
                    UserInfo userInfo = new UserInfo(userId, userName, userpassword, newSex, date, userphone, userAddress, userlei);
                    biz.add(userInfo);
                    //响应结果
                    out.print(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //关闭
        out.flush();
        out.close();
    }
    /**
     * 用户列表页显示用户数据
     */
    protected void userList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //中文处理使用过滤器
        //业务对象
        UserinfoBiz biz = new UserinfoBizImpl();
        //调用业务方法
        List<UserInfoAndType> lists= null;
        try {
            lists = biz.showUserList("");
            for (UserInfoAndType ut:lists) {
                System.out.println(ut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //保存数据(request ,使用转发)
        request.setAttribute("lists", lists);
        request.getRequestDispatcher("userList.jsp").forward(request, response);
        //关闭
        out.flush();
        out.close();

    }
    protected void removeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String id=request.getParameter("delUserId");
        System.out.println(id);
        //中文处理使用过滤器
        //业务对象
        UserinfoBiz biz = new UserinfoBizImpl();
//        try {
//            biz.del(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //关闭
        out.flush();
        out.close();

    }
}
