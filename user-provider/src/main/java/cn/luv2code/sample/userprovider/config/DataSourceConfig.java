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





    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    public HikariDataSource primaryDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dbUrl1);
        dataSource.setUsername(username1);
        dataSource.setPassword(password1);
        return dataSource;
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    public HikariDataSource secondaryDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dbUrl2);
        dataSource.setUsername(username2);
        dataSource.setPassword(password2);
        return dataSource;
    }


}
