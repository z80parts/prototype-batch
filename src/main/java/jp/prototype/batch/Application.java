package jp.prototype.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "jp.prototype" })
@EnableJpaRepositories(basePackages = { "jp.prototype.common.repository" })
@EntityScan(basePackages = { "jp.prototype.common.model" })
public class Application {

  public static void main(String[] args) throws Exception {

    try {
      SpringApplication application = new SpringApplication(Application.class);
      application.setWebApplicationType(WebApplicationType.NONE);

      ConfigurableApplicationContext context = application.run(args);
      int exitCode = SpringApplication.exit(context);
      System.exit(exitCode);
    } catch (Throwable t) {
      System.exit(1);
    }
  }
}
