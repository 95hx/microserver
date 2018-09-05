package cn.luv2code.sample.userprovider;

import cn.luv2code.sample.userprovider.config.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

//@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
//导入DynamicDataSourceRegister到容器
@Import({DynamicDataSourceRegister.class})
//WebApplicationInitializer is only needed if you are building a war file and deploying it
public class UserProviderApplication  {

    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class, args);
    }
}
