package jp.prototype.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class AdditionalTasklet implements Tasklet {
  
  private static final Logger log = LoggerFactory.getLogger(AdditionalTasklet.class);
  
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {

    log.info("Execute job.");
    return RepeatStatus.FINISHED;
  }

}
