package com.springdemo;

import java.util.List;

/**
 * @author huapeng.zhang@hand-china.com
 * @date 2022/01/27 10:40 下午
 */
public class User {

    private String name;
    private String age;
    private UserTwo userTwo;
    private UserThree userThree;
    private List<String> strs;

    public void setStrs(List<String> strs) {
        this.strs = strs;
    }

    public void setUserThree(UserThree userThree) {
        this.userThree = userThree;
    }
    public void setUserTwo(UserTwo userTwo) {
        this.userTwo = userTwo;
    }

    public User() {
    }

    public User(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", userTwo=" + userTwo +
                ", userThree=" + userThree +
                ", strs=" + strs +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
