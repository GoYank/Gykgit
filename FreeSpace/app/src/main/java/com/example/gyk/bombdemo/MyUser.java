package com.example.gyk.bombdemo;

import cn.bmob.v3.BmobUser;

/**
 * 项目名：BombDemo
 * 包名：com.example.gyk.bombdemo
 * 文件名：MyUser
 * 创建者：Gyk
 * 创建时间：2018/3/30 12:49
 * 描述：  用户实用类
 */

public class MyUser extends BmobUser {
    private String name;
    private int age;
    private boolean sex;
    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
