package com.toy.common.service;

import com.toy.admin.repository.MonthlyUserRegisterStatsRepository;
import com.toy.admin.repository.UserBoardStatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {

    private final UserBoardStatsRepository userBoardStatsRepository;
    private final MonthlyUserRegisterStatsRepository monthlyUserRegisterStatsRepository;

    @Value("${script.path}")
    private String path;

    @Scheduled(cron = "0 0 0,12 * * ?")
    public void monthlyUserRegisterStatsBatchExecute() throws IOException {
        log.info("monthlyUserRegisterStatsBatch 배치 실행");

        monthlyUserRegisterStatsRepository.deleteAll();

        Runtime rt = Runtime.getRuntime();
        rt.exec("sh " + path + "monthlyUserRegisterStatsBatch.sh");
    }

    @Scheduled(cron = "0 30 0,12 * * ?")
    public void userBoardStatsBatchExecute() throws IOException {
        log.info("userBoardStatsBatch 배치 실행");

        userBoardStatsRepository.deleteAll();

        Runtime rt = Runtime.getRuntime();
        rt.exec("sh " + path + "userBoardStatsBatch.sh");
    }
}
