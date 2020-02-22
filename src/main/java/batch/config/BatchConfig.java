package batch.config;

import batch.listener.JobListener;
import batch.tasklet.ImportTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

  @Autowired
  private ImportTasklet tasklet;

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Step step() {
    return stepBuilderFactory.get("step").tasklet(tasklet).build();
  }

  @Bean
  public Job job(Step step, JobListener listener) throws Exception {
    return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer())
        .listener(listener).start(step).build();
  }

  @Bean
  public JobListener listener() {
    return new JobListener();
  }

}
