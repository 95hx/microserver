package cn.luv2code.sample.userprovider.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hx
 * @Title: UseMyProperties
 * @ProjectName micro-server-demo
 * @Description: TODO
 * @date 2018/8/2213:52
 */
@Component
@EnableConfigurationProperties(MyProperties.class)
public class UseMyProperties {
    private final MyProperties myProperties;
    public UseMyProperties(MyProperties myProperties) {
        this.myProperties = myProperties;
    }

    public String getProperties() {
        String datasource = myProperties.getUsername();
        return datasource;
    }
}
