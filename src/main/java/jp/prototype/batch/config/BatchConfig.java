package jp.prototype.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import jp.prototype.batch.listener.JobListener;
import jp.prototype.batch.tasklet.AdditionalTasklet;
import jp.prototype.batch.tasklet.WriteToDbTasklet;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

  @Autowired
  private WriteToDbTasklet exportTasklet;

  @Autowired
  private AdditionalTasklet additionalTasklet;

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
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

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public JobRepository jobRepository() throws Exception {
    MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean(
        new ResourcelessTransactionManager());
    JobRepository jobRepository = factoryBean.getObject();
    return jobRepository;
  }

  @Bean
  public JobLauncher jobLauncher(JobRepository jobRepository) {
    SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
    jobLauncher.setJobRepository(jobRepository);
    return jobLauncher;
  }
}
