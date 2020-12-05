package org.aly.sjc.dao;

import org.aly.sjc.entity.UserInfo;
import org.aly.sjc.entity.UserInfoAndType;

import java.sql.ResultSet;
import java.util.List;

public interface UserinfoDao {
    //登录
    UserInfo isLogin(UserInfo userInfo) throws Exception;

    //添加
    int add(UserInfo userInfo)throws Exception;

    //删除
    int del(String id) throws Exception;

    //修改信息
    int update(UserInfo userInfo) throws Exception;
    //修改当前用户的密码
    int updatePassword(UserInfo userInfo) throws Exception;
    //根据ID查询
    UserInfoAndType findById(String id)throws Exception;
    //根据用户列表界面展示信息
    List<UserInfoAndType>  showUserList(String userName)throws Exception;
    //查询所有(2)
    List<UserInfo> all(Object... param) throws Exception;
}
