package com.example.demo.batch.location;

import lombok.extern.java.Log;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * Created on 03 July 2017 @ 9:18 PM
 * Component for project "batch-demo"
 *
 * @author Thomas Bezuidenhout
 */
@Log
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Job finished. Took " + String.valueOf(jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime()) + "ms to complete");
        }
    }
}
