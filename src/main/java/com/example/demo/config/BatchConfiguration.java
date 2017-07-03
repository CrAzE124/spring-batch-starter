package com.example.demo.config;

import com.example.demo.batch.location.FileReader;
import com.example.demo.batch.location.JobCompletionNotificationListener;
import com.example.demo.batch.location.LocationItemProcessor;
import com.example.demo.batch.location.LocationItemWriter;
import com.example.demo.model.Location;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;

/**
 * Created on 03 July 2017 @ 9:04 PM
 * Component for project "batch-demo"
 *
 * @author Thomas Bezuidenhout
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final LocationItemProcessor itemProcessor;
    private final LocationItemWriter itemWriter;
    private final FileReader fileReader;

    @Autowired
    public BatchConfiguration(
            LocationItemProcessor itemProcessor,
            LocationItemWriter itemWriter,
            FileReader fileReader,
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory
    ) {
        this.itemProcessor = itemProcessor;
        this.itemWriter = itemWriter;
        this.fileReader = fileReader;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step importLocationsJobStep1() {
        return stepBuilderFactory.get("importLocationsJobStep1")
                .<Location, Location> chunk(10)
                .reader(fileReader.locationFlatFileItemReader())
                .processor(itemProcessor)
                .writer(itemWriter.writer())
                .faultTolerant()
                .skip(FlatFileParseException.class)
                .skipLimit(10)
                .build();
    }

    @Bean
    public Job importLocationsJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importLocationsJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(importLocationsJobStep1())
                .end()
                .build();
    }
}
