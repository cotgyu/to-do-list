package com.toy.study.test.container;


import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;


@Testcontainers
public class TestContainer {

    @Container
    public static JdbcDatabaseContainer mariaDBContainer = new org.testcontainers.containers.MariaDBContainer("mariadb:10")
            .withDatabaseName("to-do-list-test")
            .withUsername("admin")
            .withPassword("1234")
            //.withConfigurationOverride("conf.d") // DB 서버 추가 설정
            .withInitScript("testContainer/initData.sql") // 초기 데이터
            ;


    @Container
    static DockerComposeContainer dockerComposeContainer =
            new DockerComposeContainer(new File("src/test/resources/testContainer/docker-compose.yml"));

}
