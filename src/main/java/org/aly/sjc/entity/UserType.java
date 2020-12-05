package org.aly.sjc.entity;

/**
 * @ClassName: UserType
 * @Description: TODO
 * @Author: 沈佳程
 * @date: 2020/12/2 14:37
 * @Version: V1.0
 */

public class UserType {
    private String typeID;
    private String typeName;

    public UserType() {
    }

    public UserType(String typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
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
        return "UserType{" +
                "typeID=" + typeID +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
