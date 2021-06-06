package com.toy.job;


import com.toy.user.domain.UserBoardStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class UserBoardStatisticsJobConfiguration {

    public static final String JOB_NAME = "userBoardStatsBatch";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    private static final int chunkSize = 100;

    @Bean
    public Job userBoardStatsWriterJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(userBoardStatsWriterStep())
                .build();
    }

    @Bean
    public Step userBoardStatsWriterStep() {
        return stepBuilderFactory.get("userBoardStatsWriterStep")
                .<UserBoardStats, UserBoardStats>chunk(chunkSize)
                .reader(userBoardStatsWriterReader())
                .writer(userBoardStatsWriter())
                .build();
    }

    // TODO JpaPagingItemReader 조인하는 방법 찾아서 바꾸기 (현재는 nativeQuery를 사용하여 JdbcCursorItemReader)
    @Bean
    public JdbcCursorItemReader<UserBoardStats> userBoardStatsWriterReader() {
        return new JdbcCursorItemReaderBuilder<UserBoardStats>()
                .fetchSize(chunkSize)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(UserBoardStats.class))
                .sql("select count(b.board_id) as count, u.user_id as user_id, u.name as name \n" +
                        "    from\n" +
                        "        board b \n" +
                        "    left outer join\n" +
                        "        user u on b.user_id=u.user_id \n" +
                        "    where u.del_flag='N'\n" +
                        "    group by u.user_id \n" +
                        "    order by count(b.board_id) desc limit 10")
                .name("userBoardStatsWriter")
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<UserBoardStats> userBoardStatsWriter() {

        return new JdbcBatchItemWriterBuilder<UserBoardStats>()
                .dataSource(dataSource)
                .sql("insert into user_board_stats(count, name, user_id) values (:count, :name, :userId)")
                .beanMapped()
                .build();
    }

}
