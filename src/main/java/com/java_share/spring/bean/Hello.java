package com.java_share.spring.bean;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author yz
 * @date 2020-08-08 11:03
 * <p></p>
 **/
public class Hello implements InitializingBean {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("hello world "+ name);
    }
}
