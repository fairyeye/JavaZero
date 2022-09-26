package com.springdemo;

/**
 * @author huapeng.zhang@hand-china.com
 * @date 2022/02/09 11:09 下午
 */
public class UserThree {

    private String tName;
    private String tAge;

    public void settName(String tName) {
        this.tName = tName;
    }

    public void settAge(String tAge) {
        this.tAge = tAge;
    }

    @Override
    public String toString() {
        return "UserTwo{" +
                "tName='" + tName + '\'' +
                ", tAge='" + tAge + '\'' +
                '}';
    }
}
