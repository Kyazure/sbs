package online.kyralo.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: guohezuzi
 * \* Date: 2018-11-09
 * \* Time: 下午7:07
 * \* Description:数据库连接池配置 ref:https://github.com/alibaba/druid/wiki
 * \
 */
@Configuration
public class DaoConfig {
    @Bean
    @Primary
    @Profile("dev")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Profile("test")
    public DataSource dateSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/sbs?serverTimezone=Hongkong&useSSL=false&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("991010");
        return dataSource;
    }
}
