package org.aly.sjc.entity;

import java.util.Date;

/**
 * @ClassName: UserInfo
 * @Description: TODO
 * @Author: 沈佳程
 * @date: 2020/12/2 14:37
 * @Version: V1.0
 */

public class UserInfo {
    private String userId;
    private String userName;
    private String passWord;
    private Integer sex;
    private Date bornDate;
    private String userTel;
    private String userAddress;
    private String typeID;

    public UserInfo() {
    }

    //用来修改当前用户密码的逻辑，userName可以传空值
    public UserInfo(String passWord,String userId, String userName) {
        this.passWord = passWord;
        this.userId = userId;
        this.userName = userName;
    }

    //用来判断登录的构造方法
    public UserInfo(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    //用来新增用户的构造方法
    public UserInfo(String userId, String userName, String passWord, Integer sex, Date bornDate, String userTel, String userAddress, String typeID) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.sex = sex;
        this.bornDate = bornDate;
        this.userTel = userTel;
        this.userAddress = userAddress;
        this.typeID = typeID;
    }

    //构造方法：修改的构造方法
    public UserInfo(String userName, Integer sex, Date bornDate, String userTel, String userAddress, String typeID, String userId) {
        this.userName = userName;
        this.sex = sex;
        this.bornDate = bornDate;
        this.userTel = userTel;
        this.userAddress = userAddress;
        this.typeID = typeID;
        this.userId = userId;
    }


    /*
     *封装
     * */

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", sex=" + sex +
                ", bornDate='" + bornDate + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", typeID=" + typeID +
                '}';
    }
}
