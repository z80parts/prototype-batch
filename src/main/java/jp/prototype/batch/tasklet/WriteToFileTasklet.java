package jp.prototype.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jp.prototype.common.model.Company;

@Component
public class WriteToFileTasklet implements Tasklet {

  private static final Logger log = LoggerFactory.getLogger(WriteToFileTasklet.class);

  private FlatFileItemWriter<Company> csvWriter;

  private FlatFileItemWriter<Company> tsvWriter;

  public WriteToFileTasklet(@Qualifier("csvWriter") FlatFileItemWriter<Company> csvWriter,
      @Qualifier("tsvWriter") FlatFileItemWriter<Company> tsvWriter) {
    super();
    this.csvWriter = csvWriter;
    this.tsvWriter = tsvWriter;
  }

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {

    log.info("Execute job.");
    log.info("==================>" + csvWriter.toString());
    log.info("tsvWriter=========>" + tsvWriter.toString());
    return RepeatStatus.FINISHED;
  }

}
