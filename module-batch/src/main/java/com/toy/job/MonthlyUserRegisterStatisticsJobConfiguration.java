package com.toy.job;


import com.toy.admin.domain.MonthlyUserRegisterStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class MonthlyUserRegisterStatisticsJobConfiguration {

    public static final String JOB_NAME = "monthlyUserRegisterStatsBatch";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private static final int chunkSize = 100;

    @Bean
    public Job monthlyUserRegisterStatsWriterJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(monthlyUserRegisterStatsWriterStep())
                .build();
    }

    @Bean
    public Step monthlyUserRegisterStatsWriterStep() {
        return stepBuilderFactory.get("monthlyUserRegisterStatsWriterStep")
                .<Object[], MonthlyUserRegisterStats>chunk(chunkSize)
                .reader(monthlyUserRegisterStatsWriterReader())
                .processor(monthlyUserRegisterStatsProcessor())
                .writer(monthlyUserRegisterStatsWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Object[]> monthlyUserRegisterStatsWriterReader() {
        return new JpaPagingItemReaderBuilder<Object[]>()
                .name("monthlyUserRegisterStatsWriterReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("select month(u.createdDate) as month, " +
                        "count(u.id) as count  " +
                        "from User u " +
                        "where year(u.createdDate) = 2021 " +
                        "group by month(u.createdDate)\n" +
                        "\n")
                .build();
    }

    @Bean
    public ItemProcessor<Object[], MonthlyUserRegisterStats> monthlyUserRegisterStatsProcessor() {

        return object -> new MonthlyUserRegisterStats(object);
    }

    @Bean
    public JpaItemWriter<MonthlyUserRegisterStats> monthlyUserRegisterStatsWriter() {
        JpaItemWriter<MonthlyUserRegisterStats> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }


}
