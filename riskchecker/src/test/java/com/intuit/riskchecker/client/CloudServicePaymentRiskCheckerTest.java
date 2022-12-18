package com.intuit.riskchecker.client;

import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.riskchecker.exception.CustomRestExceptionHandler;
import com.intuit.riskchecker.model.PaymentRequest;
import com.intuit.riskchecker.model.PaymentStatus;

import reactor.core.publisher.Mono;

//@SpringBootTest(classes = { CloudServicePaymentRiskChecker.class, CustomRestExceptionHandler.class})
//@ActiveProfiles("test")
//@AutoConfigureWebTestClient
//@SpringBootTest
//@RunWith(SpringRunner.class)
@WebFluxTest(CloudServicePaymentRiskChecker.class)
//@AutoConfigureWebTestClient
@ContextConfiguration(classes = {ClientConfiguration.class})
//@AutoConfigureWireMock(port = 8084)
//@TestPropertySource(properties = {
//		"risk.engiene.service.url=https://payment-risk:8084/api/v1/check",
//})
//@ExtendWith(SpringExtension.class)
public class CloudServicePaymentRiskCheckerTest {
	
	private CloudServicePaymentRiskChecker underTest;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private WebClient webClient;

	private final static String SERVICE_URL = "https://payment-risk:8080/api/v1/check";

	@Test
	void successTest() throws JsonProcessingException {
		underTest = new CloudServicePaymentRiskChecker(webClient, SERVICE_URL);
		PaymentStatus expected = PaymentStatus.builder().isApproved(true).build();

		stubFor(post(urlEqualTo(SERVICE_URL)).willReturn(ok().withHeader("Content-Type", "application/json")
				.withBody(objectMapper.writeValueAsString(expected))));

		
		Mono<ResponseEntity<PaymentStatus>> response = underTest.isPaymentApproved(new PaymentRequest());
		
		PaymentStatus actual = response.block().getBody();
		
		assertEquals(expected, actual);
		
//		webTestClient.post().uri(SERVICE_URL).bodyValue(paymentStatus).exchange().expectStatus().isOk();
//				.expectBody(PaymentStatus.class).consumeWith(paymantStatusExchangeResult -> {
//					PaymentStatus response = paymantStatusExchangeResult.getResponseBody();
//					assertEquals(true, response.isApproved());
//
//				});
		
		
	}
}
