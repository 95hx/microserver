package cn.luv2code.sample.userprovider.utils;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

/**
 * instance of DozerBeanMapper
 */
//@Component
@Configuration
@ImportResource()//可指定配置文件
public class DozerBeanMapperBean {
    @Value("${server.port}")
    private String getFromYml;

    @Bean(name = "dozerBeanMapper", initMethod = "")
    //proxyMode:容器启动时bean还没创建 通过cglib代理这个接口或者类注入到其它需要这个bean的bean中
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON,proxyMode = ScopedProxyMode.DEFAULT)
    public DozerBeanMapper getDozerBeanMapper() {
        System.out.println(getFromYml);
        return new DozerBeanMapper();
    }

}
