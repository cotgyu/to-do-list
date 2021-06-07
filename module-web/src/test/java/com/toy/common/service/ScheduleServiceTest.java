package com.toy.common.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ScheduleServiceTest {

    @Test
    @DisplayName("monthlyUserRegisterStatsBatch 쉘 실행 테스트")
    public void executeShellTest() throws Exception{
        //given when
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("sh /Users/sg/IdeaProjects/to-do-list/script/batch/monthlyUserRegisterStatsBatch.sh");

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line = null;

        while((line = br.readLine()) != null) {
            System.out.println(line);
        }

    }

    @Test
    @DisplayName("userBoardStatsBatch 쉘 실행 테스트")
    public void executeShellTest2() throws Exception{
        //given when
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("sh /Users/sg/IdeaProjects/to-do-list/script/batch/userBoardStatsBatch.sh");

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line = null;

        while((line = br.readLine()) != null) {
            System.out.println(line);
        }

    }
}