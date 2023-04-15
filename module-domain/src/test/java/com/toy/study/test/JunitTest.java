package com.toy.study.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JunitTest {

    @Test
    void test1() {
        String a  = "1";
        int number = 1;

        Assertions.assertEquals(Integer.parseInt(a), number);


        org.assertj.core.api.Assertions.assertThat(a.equals(number));
    }
}
