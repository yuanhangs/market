import org.aly.sjc.biz.UserinfoBiz;
import org.aly.sjc.biz.impl.UserinfoBizImpl;
import org.aly.sjc.entity.UserInfo;
import org.aly.sjc.entity.UserInfoAndType;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestUserinfo {

    @Test
    public void login() {
        //实例化对象
        UserinfoBiz biz = new UserinfoBizImpl();
        //封装对象
//        UserInfo user = new UserInfo("孙主管", "aaa");
        UserInfo user = new UserInfo("刘丽", "123456");
        try {
            //调用方法
            UserInfo userInfo = biz.isLogin(user);
            System.out.println(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void all() {
        //实例化对象
        UserinfoBiz biz = new UserinfoBizImpl();
        //参数
        Object[] obj = {""};
        try {
            //调用方法
//            List<UserInfo> userInfos = biz.all();
            List<UserInfo> userInfos = biz.all(obj);
            //循环遍历
            for (UserInfo userInfo : userInfos) {
                System.out.println(userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void del() {
        //实例化对象
        UserinfoBiz biz = new UserinfoBizImpl();
        try {
            biz.del("222");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add() {
        //实例化对象
        UserinfoBiz biz = new UserinfoBizImpl();
        //.String转换成Date类型
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String time = "2019-09-19";
        try {
            Date date = ft.parse(time);
            //UserInfo 实例化
            UserInfo userInfo = new UserInfo("22222", "钉钉2222", "15616", 1, date, "15555555", "", "1");
            biz.add(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        //实例化对象
        UserinfoBiz biz = new UserinfoBizImpl();
        //.String转换成Date类型
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String time = "2026-09-19";
        try {
            Date date = ft.parse(time);
            //UserInfo 实例化
            UserInfo userInfo = new UserInfo("钉钉8888", 1, date, "1888888", "9999", "2", "22222");
            System.out.println(userInfo);
            biz.update(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePassword() {
        //实例化对象
        UserinfoBiz biz = new UserinfoBizImpl();
        try {
            //UserInfo 实例化
            UserInfo userInfo = new UserInfo("888888", "22222", null);
            System.out.println(userInfo);
            biz.updatePassword(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        //实例化对象
        UserinfoBiz biz = new UserinfoBizImpl();
        UserInfoAndType userInfoAndType =null;
        try {
            userInfoAndType=biz.findById("22222");
            System.out.println(userInfoAndType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void showUserList() {
        //实例化对象
        UserinfoBiz biz = new UserinfoBizImpl();
        List<UserInfoAndType> list= null;
        try {
            list = biz.showUserList("钉钉");
            for (UserInfoAndType ut:list) {
                System.out.println(ut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
