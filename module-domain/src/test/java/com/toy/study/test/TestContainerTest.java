package com.toy.study.test;

import com.toy.study.test.container.TestContainer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.wait.strategy.Wait;

import static com.toy.study.test.container.TestContainer.mariaDBContainer;

@SpringBootTest
@Slf4j
class TestContainerTest extends TestContainer{

    @Test
    void test() {
        String dbName = "to-do-list-test";
        Assertions.assertEquals(dbName, mariaDBContainer.getDatabaseName());
    }
}
