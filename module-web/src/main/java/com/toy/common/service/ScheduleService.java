package com.toy.common.service;

import com.toy.admin.repository.MonthlyUserRegisterStatsRepository;
import com.toy.admin.repository.UserBoardStatsRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {

    private final UserBoardStatsRepository userBoardStatsRepository;
    private final MonthlyUserRegisterStatsRepository monthlyUserRegisterStatsRepository;

    @Scheduled(cron="0 0 0,12 * * ?")
    public void monthlyUserRegisterStatsBatchExecute() throws IOException {
        log.info("monthlyUserRegisterStatsBatch 배치 실행");

        monthlyUserRegisterStatsRepository.deleteAll();

        Runtime rt = Runtime.getRuntime();
        rt.exec("sh /home/ec2-user/project/todolist/zip/monthlyUserRegisterStatsBatch.sh");
    }

    @Scheduled(cron="0 12 0,8 * * ?")
    public void userBoardStatsBatchExecute()  throws IOException {
        log.info("userBoardStatsBatch 배치 실행");

        userBoardStatsRepository.deleteAll();;

        Runtime rt = Runtime.getRuntime();
        rt.exec("sh /home/ec2-user/project/todolist/zip/userBoardStatsBatch.sh");
    }
}
