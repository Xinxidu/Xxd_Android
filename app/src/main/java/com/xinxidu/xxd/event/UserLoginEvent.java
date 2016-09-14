package com.xinxidu.xxd.event;

/**
 * Created by limingquan on 2016/9/13.
 */
public class UserLoginEvent {

    private String userName;
    private String userPass;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
