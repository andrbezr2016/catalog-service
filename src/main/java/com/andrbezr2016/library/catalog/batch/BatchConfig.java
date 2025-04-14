package com.andrbezr2016.library.catalog.batch;

import com.andrbezr2016.library.catalog.dto.BookInput;
import com.andrbezr2016.library.catalog.service.BookService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.ConsumerItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Bean
    public ItemReader<BookInput> reader(@Value("${batch.input}") Resource inputCsv) {
        return new JsonItemReaderBuilder<BookInput>()
                .name("bookItemReader")
                .resource(inputCsv)
                .jsonObjectReader(new JacksonJsonObjectReader<>(BookInput.class))
                .build();
    }

    @Bean
    public ItemWriter<BookInput> writer(BookService bookService) {
        return new ConsumerItemWriter<>(bookService::addBook);
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemReader<BookInput> reader, ItemWriter<BookInput> writer) {
        return new StepBuilder("step1", jobRepository)
                .<BookInput, BookInput>chunk(10, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importBookJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importBookJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }
}
