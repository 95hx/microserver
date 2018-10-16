package cn.luv2code.sample.userprovider.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Map;

public class DataSourceBuilder {
    public static HikariDataSource getHikariDataSource(Map<String, Object> dsMap) {
        String driverClassName = dsMap.get("driver-class-name").toString();
        String url = dsMap.get("url").toString();
        String username = dsMap.get("username").toString();
        String password = dsMap.get("password").toString();
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }
}
