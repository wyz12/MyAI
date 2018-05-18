package com.zxwl.myai;

/**
 * Created by sks on 2018/5/9.
 */

public class javaBean  {
    private  String phone;
    private String pass;

    public javaBean(String phone, String pass) {
        this.phone = phone;
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
