package com.damuzee.system.perm.demain;

/**
 * Created by Administrator on 2014/7/24.
 */
public class User {

    private  String userName ;
    private String passWord;
    private String lastName;
    private String firstName ;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getUserName() {
        return userName;
    }
}
