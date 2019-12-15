package batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class JobListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobListener.class);

	@Override
	public void beforeJob(JobExecution stepExecution) {
		log.info("Start job.");
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("End job.");
	}
	
}
