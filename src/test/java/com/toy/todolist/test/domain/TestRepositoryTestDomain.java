package com.toy.todolist.test.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class TestRepositoryTestDomain {

    @Autowired
    TestRepository testRepository;

    @Test
    @Commit
    public void basicTest() {
        // given
        TestDomain testDomain = new TestDomain("name1");
        testRepository.save(testDomain);

        // when
        TestDomain testDomain1 = testRepository.findById(1L).get();

        // then
        assertThat(testDomain1).isEqualTo(testDomain);
    }
}