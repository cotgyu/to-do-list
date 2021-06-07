package com.toy.common.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@NoArgsConstructor
public class ScheduleService {

    @Scheduled(cron="0 0 0,12 * * ?")
    public void monthlyUserRegisterStatsBatchExecute() throws IOException {
        log.info("monthlyUserRegisterStatsBatch 배치 실행");
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("sh /Users/sg/IdeaProjects/to-do-list/script/batch/monthlyUserRegisterStatsBatch.sh");
    }

    @Scheduled(cron="0 12 0,8 * * ?")
    public void userBoardStatsBatchExecute()  throws IOException {
        log.info("userBoardStatsBatch 배치 실행");
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("sh /Users/sg/IdeaProjects/to-do-list/script/batch/userBoardStatsBatch.sh");
    }
}
