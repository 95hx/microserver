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
@Import({DynamicDataSourceRegister.class})
public class UserProviderApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(UserProviderApplication.class, args);
	}
}
