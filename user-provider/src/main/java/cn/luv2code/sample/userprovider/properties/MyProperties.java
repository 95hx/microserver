package cn.luv2code.sample.userprovider.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hx
 * @Title: MyProperties
 * @ProjectName micro-server-demo
 * @Description: TODO
 * @date 2018/8/2213:47
 */
@ConfigurationProperties(prefix = "spring.datasource")
@Component
public class MyProperties {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
