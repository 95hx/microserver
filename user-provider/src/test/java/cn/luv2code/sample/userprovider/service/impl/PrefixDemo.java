package cn.luv2code.sample.userprovider.service.impl;

import cn.luv2code.sample.userprovider.UserProviderApplication;
import org.junit.After;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.naming.Context;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
custom:
  datasource:
    names: ds1,ds2
    ds1:
      driver-class-name: com.mysql.jdbc.Driver
      url: jdbc:mysql://118.25.49.50:3306/db_cooper?characterEncoding=utf8&useSSL=false
      username: root
      password: 123456
    ds2:
      driver-class-name: com.mysql.jdbc.Driver
      url: jdbc:mysql://118.25.49.50:3306/db_cooper?characterEncoding=utf8&useSSL=false
      username: root
      password: 123456
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
public class PrefixDemo {

    @Value("${custom.datasource.ds1.driver-class-name}")
    private String driver;

    @Value("${custom.datasource.ds1.url}")
    private String url;

    @Value("${custom.datasource.ds1.username}")
    private String username;

    @Value("${custom.datasource.ds1.password}")
    private String password;


    @Test
    public void outPut() {
        ApplicationContext context= SpringApplication.run(UserProviderApplication.class);
        Environment env = context.getEnvironment();
        FlagType flagType = Binder.get(env)
                .bind("custom.datasource.ds1", FlagType.class)
                .get();
        System.out.println(flagType.getUsername());
        LinkedHashMap<String,String> map=(LinkedHashMap)Binder.get(env)
                .bind("spring.datasource", Bindable.mapOf(String.class,String.class))
                .get();
        System.out.println(map);
//        Binder binder = Binder.get(context.getEnvironment());
//
//        // 绑定简单配置
//        FooProperties foo = binder.bind("com.didispace", Bindable.of(FooProperties.class)).get();
//        System.out.println(foo.getFoo());
    }
}

@ConfigurationProperties(prefix = "com.didispace")
class FooProperties {

    private String foo;

}