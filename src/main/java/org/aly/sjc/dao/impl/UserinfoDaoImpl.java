package org.aly.sjc.dao.impl;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.aly.sjc.dao.UserinfoDao;
import org.aly.sjc.entity.UserInfo;
import org.aly.sjc.entity.UserInfoAndType;
import org.aly.sjc.util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 底层数据实现
 */
public class UserinfoDaoImpl extends BaseDao implements UserinfoDao {
    @Override   //登录
    public UserInfo isLogin(UserInfo userInfo) throws Exception {
        //sql语句
        String sql = "SELECT * from userinfo WHERE userName=? and `passWord`=?";
        //参数
        Object[] obj = {userInfo.getUserName(), userInfo.getPassWord()};
        //调用方法，UserInfo.class 代表对象，这个方法返回的是这个UserInfo实例化后的对象
        return this.excuteOneQuery(sql, UserInfo.class, obj);
    }

    @Override  //添加
    public int add(UserInfo userInfo) throws Exception {
        //sql语句
        String sql = "INSERT into userinfo(`userId`,userName,`passWord`,sex,bornDate,userTel,userAddress,typeID) VALUES(?,?,?,?,?,?,?,?)";
        //参数
        Object[] obj = {userInfo.getUserId(), userInfo.getUserName(), userInfo.getPassWord(), userInfo.getSex(), userInfo.getBornDate(), userInfo.getUserTel(), userInfo.getUserAddress(), userInfo.getTypeID()};
        //调用方法，UserInfo.class 代表对象，这个方法返回的是这个UserInfo实例化后的对象
        return this.queryUpdate(sql, obj);
    }

    @Override   //删除
    public int del(String id) throws Exception {
        //sql语句
        String sql = "DELETE FROM userinfo a WHERE userId=?";
        //参数
        Object[] obj = {id};
        //调用方法，UserInfo.class 代表对象，这个方法返回的是这个UserInfo实例化后的对象
        return this.queryUpdate(sql, obj);
    }

    @Override   //修改 UPDATE userinfo SET userName=?,sex=?,borndate=?,userTel=?,userAddress=?,typeID=? WHERE  userId=?
    public int update(UserInfo userInfo) throws Exception {
        //sql语句
        String sql = "UPDATE userinfo SET userName=?,sex=?,borndate=?,userTel=?,userAddress=?,typeID=? WHERE  userId=?";
        //参数
        Object[] obj = {userInfo.getUserName(), userInfo.getSex(), userInfo.getBornDate(), userInfo.getUserTel(), userInfo.getUserAddress(), userInfo.getTypeID(), userInfo.getUserId()};
        //调用方法，UserInfo.class 代表对象，这个方法返回的是这个UserInfo实例化后的对象
        return this.queryUpdate(sql, obj);
    }

    @Override   //修改当前用户的密码
    // UPDATE userinfo SET `passWord`=? WHERE  userId=?
    public int updatePassword(UserInfo userInfo) throws Exception {
        //sql语句
        String sql = "UPDATE userinfo SET `passWord`=? WHERE  userId=?";
        //参数
        Object[] obj = {userInfo.getPassWord(), userInfo.getUserId()};
        //调用方法，UserInfo.class 代表对象，这个方法返回的是这个UserInfo实例化后的对象
        return this.queryUpdate(sql, obj);
    }

    @Override   //根据ID查询,就是查看功能界面
    public UserInfoAndType findById(String id) throws Exception {
        //sql语句
        String sql = "SELECT a.userId,a.userName,a.sex,a.bornDate,a.userTel,a.userAddress,b.typeName from userinfo a,usertype b WHERE a.typeID=b.typeID AND userId=?";
        //参数
        Object[] obj = {id};
        //调用方法
        ResultSet re = this.queryQuery(sql, obj);
        re.next();
        UserInfoAndType userInfoAndType = new UserInfoAndType(re.getString(1), re.getString(2), re.getInt(3), re.getDate(4), re.getString(5), re.getNString(6), re.getNString(7));
        return userInfoAndType;
    }

    @Override
    //根据用户列表界面展示信息，和模糊搜索
    public List<UserInfoAndType> showUserList(String userName) throws Exception {
        //存储数据
        List<UserInfoAndType> list = new ArrayList<>();
        String sql = null;
        Object[] obj = null;
        if (userName == null) {
            //sql语句
            sql = "SELECT a.userId,a.userName,a.sex,a.borndate,a.userTel,b.typeName FROM userinfo a,usertype b WHERE a.typeID=b.typeID";
            //设置值
            obj = new Object[]{};
        } else {
            //sql语句
            sql = "SELECT a.userId,a.userName,a.sex,a.borndate,a.userTel,b.typeName FROM userinfo a,usertype b WHERE a.typeID=b.typeID AND a.userName LIKE ?";
            //设置值
            obj = new Object[]{"%" + userName + "%"};
        }
        //定义对象用来接运行方法后返回的数据
        ResultSet re = null;
        try {
            //、调用方法
            re = this.queryQuery(sql, obj);
            while (re.next()) {
                UserInfoAndType userInfoAndType = new UserInfoAndType(re.getString(1), re.getString(2), re.getInt(3), re.getDate(4), re.getString(5), re.getString(6));
                //取到的数据放入集合
                list.add(userInfoAndType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll(re, this.ps, this.con);
        }
        return list;
    }

    @Override   //查询所有(2)
    public List<UserInfo> all(Object... param) throws Exception {
        String sql = "";
        List<UserInfo> list = null;
        if (param.length == 0) {
            //sql语句
            sql = "SELECT * from userinfo";
            //调用方法
            list = this.excuteMoreQuery(sql, UserInfo.class);
        } else {
            sql = "SELECT * from userinfo where username like ?";
            //参数
            Object[] obj = {"%" + param[0] + "%"};
            //调用方法
            list = this.excuteMoreQuery(sql, UserInfo.class, obj);
        }

        return list;
    }
}
