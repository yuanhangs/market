package org.aly.sjc.entity;

import java.util.Date;

/**
 * @ClassName: UserInfoAndType
 * @Description: TODO
 * @Author: 沈佳程
 * @date: 2020/12/3 17:08
 * @Version: V1.0
 */

public class UserInfoAndType {
    private String userId;
    private String userName;
    private String passWord;
    private Integer sex;
    private Date bornDate;
    private String userTel;
    private String userAddress;
    private String typeID;
    private String typeName;

    public UserInfoAndType() {
    }
    //这个构造方法是用来查看用户列表页；
    public UserInfoAndType(String userId, String userName, Integer sex, Date bornDate, String userTel, String typeName) {
        this.userId = userId;
        this.userName = userName;
        this.sex = sex;
        this.bornDate = bornDate;
        this.userTel = userTel;
        this.typeName = typeName;
    }

    //这个构造方法是用来查看某条数据的详细；
    public UserInfoAndType(String userId, String userName, Integer sex, Date bornDate, String userTel, String userAddress, String typeName) {
        this.userId = userId;
        this.userName = userName;
        this.sex = sex;
        this.bornDate = bornDate;
        this.userTel = userTel;
        this.userAddress = userAddress;
        this.typeName = typeName;
    }

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "UserInfoAndType{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", sex=" + sex +
                ", bornDate=" + bornDate +
                ", userTel='" + userTel + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", typeID='" + typeID + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
