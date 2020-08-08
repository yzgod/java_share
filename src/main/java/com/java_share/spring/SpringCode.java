package com.java_share.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yz
 * @date 2020-08-08 10:59
 * <p></p>
 **/
public class SpringCode {


    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "test_0808.xml");

    }


}
