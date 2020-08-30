package jp.prototype.batch.config;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.prototype.common.model.Company;


@Configuration
public class WriterConfig {

  @Bean
  public FlatFileItemWriter<Company> csvWriter() {
    FlatFileItemWriter<Company> writer = new FlatFileItemWriter<>();
    writer.setLineAggregator(company -> {
      StringBuilder sb = new StringBuilder();
      sb.append(company.getCode());
      sb.append(",");
      sb.append(company.getName());
      return sb.toString();
    });
    return writer;
  }

  @Bean
  public FlatFileItemWriter<Company> tsvWriter() {
    FlatFileItemWriter<Company> writer = new FlatFileItemWriter<>();
    writer.setLineAggregator(company -> {
      StringBuilder sb = new StringBuilder();
      sb.append(company.getCode());
      sb.append("\t");
      sb.append(company.getName());
      return sb.toString();
    });
    return writer;
  }

}
