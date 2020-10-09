package jp.prototype.batch.tasklet;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import jp.prototype.batch.util.ItemFactory;
import jp.prototype.common.model.Item;
import jp.prototype.common.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Component
public class WriteToDbTasklet implements Tasklet {

  private ItemService service;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {
    log.info("Execute job.");
    writeToDb();
    return RepeatStatus.FINISHED;
  }

  private void writeToDb() {
    List<Item> items = ItemFactory.create(11, 20, "1");
    log.info("Items are created.");
    service.write(items);
    log.info("Save to db:{} items", items.size());
  }

}
