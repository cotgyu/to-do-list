package com.toy.study.test;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 9999)
class WireMockTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    void test_json() {
        String body = "Hello Response1";

        String response = restTemplate.getForObject("http://localhost:9999/request", String.class);

        Assertions.assertEquals(response, body);
    }

    @Test
    void test_wireMock_method() {
        String body = "Hello Response2";
        stubFor(WireMock.get(urlEqualTo("/request2"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", "application/json")
                                .withBody("Hello Response2")
                ));

        String response = restTemplate.getForObject("http://localhost:9999/request2", String.class);

        Assertions.assertEquals(response, body);
    }
}
