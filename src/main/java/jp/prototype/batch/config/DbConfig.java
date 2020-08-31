package jp.prototype.batch.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DbConfig {

  private String url;

  private String username;

  private String password;

  private String driverClassName;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    driverManagerDataSource.setDriverClassName(driverClassName);
    driverManagerDataSource.setUrl(url);
    driverManagerDataSource.setUsername(username);
    driverManagerDataSource.setPassword(password);
    return driverManagerDataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(transactionManager().getDataSource());
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

}