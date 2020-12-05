package org.aly.sjc.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BaseDao
 * @Description: TODO
 * @Author: HLX
 * @date: 2020/11/19 9:13
 * @Version: V1.0
 */
public class BaseDao {

    protected Connection con ;
    protected PreparedStatement ps ;
    protected ResultSet rs;

    //声明常量
    private static  final String  DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/supermarket";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";

    /**
     * 连接方法
     * @return
     */
    public Connection getConnection(){
        //1.加载驱动
        try {
            Class.forName(DRIVER);
            //2.连接对象
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭所有的连接
     * @param rs
     * @param ps
     * @param con
     */
    public void closeAll(ResultSet rs, PreparedStatement ps, Connection con) {
       if(rs!=null){  //结果集对象
           try {
               rs.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

        if(ps!=null){ //预编译对象
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(con!=null){  //连接对象
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 查询   这个查询方法被本类的excuteOneQuery（） excuteMoreQuery（）调用
     * @param sql
     * @param objs
     * @return
     * @throws SQLException
     */
    public ResultSet queryQuery(String sql,Object...objs) throws SQLException {
        //1.连接对象
        con = getConnection();

        //2.获得预编译对象
        ps  =con.prepareStatement(sql);

        //3.设置参数
        if(objs!=null) {
            //循环多个参数
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i+1,objs[i]);
            }
        }
        //4.获得连接
        rs = ps.executeQuery();

        //不能关闭(业务层关闭)
        return rs;
    }

    /**
     * 添加，删除，修改
     * @param sql
     * @param objs
     * @return
     * @throws SQLException
     */
    public int queryUpdate(String sql,Object...objs) throws SQLException {
        int count=0;
        //1.连接对象
        con = getConnection();
        //2.获得预编译对象
        ps  =con.prepareStatement(sql);
        //3.设置参数
        if(objs!=null) {
            //循环多个参数
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i+1,objs[i]);
            }
        }
        //4.执行sql
        count=ps.executeUpdate();
        //5.关闭所有的连接
        closeAll(null,ps,con);
        return count;
    }
    /**
     * 通过反射机制查询单条记录
     * @param sql
     * @param cls
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T>T excuteOneQuery(String sql,Class<T> cls,Object...params) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        T resultObject=cls.newInstance();  //得到实例 UserInfo的对象
        //查询数据
        ResultSet rs=this.queryQuery(sql,params);
        // 获取元数据，getMetaData()是用来获得表结构
        ResultSetMetaData metaData=rs.getMetaData();
        //getColumnCount(),获取列数，
        int colLen=metaData.getColumnCount();
        while(rs.next()){
            for (int i = 0; i <colLen ; i++) {
                //获取数据库 列名称 从1开始；
                String colsName=metaData.getColumnName(i+1);
                //根据列名获取，这一行的某一列数据
                Object colsValue=rs.getObject(colsName);
                //利用反射字段读取
                Field field=cls.getDeclaredField(colsName);
                field.setAccessible(true);  //设置字段访问权限
                field.set(resultObject,colsValue);
            }

        }
        return resultObject;
    }

    /**
     * 通过反射机制查询多条记录
     * @param sql
     * @param cls
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> List<T> excuteMoreQuery(String sql, Class<T> cls, Object...params) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        List<T> list=new ArrayList<>();
        //查询数据
        ResultSet rs=this.queryQuery(sql,params);
        // 获取元数据
        ResultSetMetaData metaData=rs.getMetaData();
        //获取列数
        int colLen=metaData.getColumnCount();
        //循环
        while(rs.next()){
            //获得实例 (自动调用默认的构造方法)
            T resultObject=cls.newInstance();
            for (int i = 0; i <colLen ; i++) {
                //获取数据库列名称
                String colsName=metaData.getColumnName(i+1);
                //获得值
                Object colsValue=rs.getObject(colsName);

                //利用反射字段读取
                Field field=cls.getDeclaredField(colsName);
                field.setAccessible(true);
                field.set(resultObject,colsValue);
            }
            list.add(resultObject);
        }
        return list;
    }
}
