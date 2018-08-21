package cn.luv2code.sample.userprovider.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.primary.url}")
    private String dbUrl1;

    @Value("${spring.datasource.primary.username}")
    private String username1;

    @Value("${spring.datasource.primary.password}")
    private String password1;

    @Value("${spring.datasource.primary.driver-class-name}")
    private String driverClassName1;

    @Value("${spring.datasource.secondary.url}")
    private String dbUrl2;

    @Value("${spring.datasource.secondary.username}")
    private String username2;

    @Value("${spring.datasource.secondary.password}")
    private String password2;

    @Value("${spring.datasource.secondary.driver-class-name}")
    private String driverClassName2;

    @Value("${spring.datasource.thirdary.url}")
    private String dbUrl3;

    @Value("${spring.datasource.thirdary.username}")
    private String username3;

    @Value("${spring.datasource.thirdary.password}")
    private String password3;

    @Value("${spring.datasource.thirdary.driver-class-name}")
    private String driverClassName3;



    @Bean(name = "thirdDataSource")
    @Qualifier("thirdDataSource")
    public HikariDataSource thirdDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dbUrl3);
        dataSource.setUsername(username3);
        dataSource.setPassword(password3);
        return dataSource;
    }


}
