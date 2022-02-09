package com.springdemo;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author huapeng.zhang@hand-china.com
 * @date 2022/02/09 11:22 下午
 */
public class FactoryBeanDemo implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setAge(24+"");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
