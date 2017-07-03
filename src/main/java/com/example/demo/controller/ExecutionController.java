package com.example.demo.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;

/**
 * Created on 03 July 2017 @ 9:03 PM
 * Component for project "batch-demo"
 *
 * @author Thomas Bezuidenhout
 */
@RequestMapping("/")
@RestController
public class ExecutionController {
    private final Job job;
    private final JobLauncher jobLauncher;

    private final JobRepository jobRepository;

    @Autowired
    public ExecutionController(@Qualifier("importLocationsJob") Job job, JobLauncher jobLauncher, JobRepository jobRepository) {
        this.job = job;
        this.jobLauncher = jobLauncher;
        this.jobRepository = jobRepository;
    }

    @GetMapping("/")
    public ResponseEntity runJob() throws Exception {
        jobLauncher.run(job, new JobParameters());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/stats")
    public String getStats() {
        return jobRepository.getLastJobExecution("importLocationsJob", new JobParameters()).toString();
    }
}
