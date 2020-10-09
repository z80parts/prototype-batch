package jp.prototype.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.prototype.batch.listener.JobListener;
import jp.prototype.batch.tasklet.AdditionalTasklet;
import jp.prototype.batch.tasklet.WriteToDbTasklet;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@EnableBatchProcessing
public class BatchConfig {

  private WriteToDbTasklet exportTasklet;

  private AdditionalTasklet additionalTasklet;

  private JobBuilderFactory jobBuilderFactory;

  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Step mainStep() {
    return stepBuilderFactory.get("main").tasklet(exportTasklet).build();
  }

  @Bean
  public Step subStep() {
    return stepBuilderFactory.get("sub").tasklet(additionalTasklet).build();
  }

  @Bean
  public Job job() throws Exception {
    // We provide a RunIdIncrementer to ensure that each execution of the job gets
    // an unique instance. This will help Spring to differentiate multiple
    // executions of the same job even if rest of the job parameters are same.
    return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer())
        .listener(listener()).start(mainStep()).next(subStep()).build();
  }

  @Bean
  public JobListener listener() {
    return new JobListener();
  }

}
