package org.aly.sjc.biz.impl;

import org.aly.sjc.biz.UserinfoBiz;
import org.aly.sjc.dao.UserinfoDao;
import org.aly.sjc.dao.impl.UserinfoDaoImpl;
import org.aly.sjc.entity.UserInfo;
import org.aly.sjc.entity.UserInfoAndType;

import java.sql.ResultSet;
import java.util.List;

/**
 * 业务层
 */
public class UserinfoBizImpl implements UserinfoBiz {
    //底层对象
    private UserinfoDao dao = new UserinfoDaoImpl();

    @Override    //登录
    public UserInfo isLogin(UserInfo userInfo) throws Exception {
        return dao.isLogin(userInfo);
    }

    @Override  //添加
    public int add(UserInfo userInfo) throws Exception {
        return dao.add(userInfo);
    }

    @Override //删除
    public int del(String id) throws Exception {
        return dao.del(id);
    }

    @Override //修改信息
    public int update(UserInfo userInfo) throws Exception {
        return dao.update(userInfo);
    }
    @Override //修改密码
    public int updatePassword(UserInfo userInfo) throws Exception {
        return dao.updatePassword(userInfo);
    }
    @Override   //根据ID查询,查看
    public UserInfoAndType findById(String id) throws Exception {
        return dao.findById(id);
    }
    @Override //根据用户列表界面展示信息
   public List<UserInfoAndType>  showUserList(String userName)throws Exception{
    return  dao.showUserList(userName);
    }
    @Override   //查询所有(2)
    public List<UserInfo> all(Object... param) throws Exception {
        return dao.all(param);
    }
}
