package cn.luv2code.sample.userprovider;

import cn.luv2code.sample.userprovider.config.DataSourceBuilder;
import cn.luv2code.sample.userprovider.config.DynamicDataSourceRegister;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.util.LinkedHashMap;
import java.util.Map;

//@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
//导入DynamicDataSourceRegister到容器
@Import({DynamicDataSourceRegister.class})
//WebApplicationInitializer is only needed if you are building a war file and deploying it
public class UserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class, args);
    }

    @Bean(name = "iniRealm")
    public Realm iniRealm() {
        IniRealm realm = new IniRealm("classpath:user.ini");
        return realm;
    }

    @Bean(name = "jdbcRealm")
    public Realm jdbcRealm(Environment env) {
        JdbcRealm realm = new JdbcRealm();
        Map<String, Object> dsMap = (LinkedHashMap) Binder.get(env)
                .bind("spring.datasource", Bindable.mapOf(String.class, String.class))
                .get();
        //创建数据源;
        realm.setDataSource(buildDataSource(dsMap));
        realm.setPermissionsLookupEnabled(true);
        String sqlAuth = "select password from user where username=?";
        String sqlRole = "select role_name from user_roles where username=?";
        String sqlPerm = "select permission from roles_permissions where role_name=?";

        realm.setAuthenticationQuery(sqlAuth);
        realm.setUserRolesQuery(sqlRole);
        realm.setPermissionsQuery(sqlPerm);
        return realm;
    }

    @Bean
    public DefaultSecurityManager securityManager(@Qualifier("jdbcRealm") Realm realm) {
        DefaultSecurityManager manager = new DefaultSecurityManager(realm);
        manager.setRealm(realm);
        return manager;
    }

    public HikariDataSource buildDataSource(Map<String, Object> dsMap) {
        return DataSourceBuilder.getHikariDataSource(dsMap);
    }
}
