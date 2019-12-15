package batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {

        try {
        	SpringApplication application = new SpringApplication(Application.class);
            // application.setWebApplicationType(WebApplicationType.NONE);

            ConfigurableApplicationContext context = application.run(args);
            int exitCode = SpringApplication.exit(context);
            System.exit(exitCode);
        } catch (Throwable t) {
            System.exit(1);
        }
    }
}
