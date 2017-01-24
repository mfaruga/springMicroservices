package org.mfaruga;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsBootFirstApplicationTests {

	@Test
	public void contextLoads() {
		RestTemplate restTemplate = new RestTemplate();
		Greet greet = restTemplate.getForObject("http://localhost:9090", Greet.class);
		System.out.println(greet.getMessage());
		Assert.assertTrue(greet.getMessage().startsWith("Hello World"));
	}

	@Test
	public void testVanillaService() {
		RestTemplate restTemplate = new RestTemplate();
		Greet greet = restTemplate.getForObject("http://localhost:9090", Greet.class);
		Assert.assertTrue(greet.getMessage().startsWith("Hello World"));
	}

	@Test
	public void testSecureService() {
		String plainCreds = "guest:guest123";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encode(plainCreds.getBytes())));
		HttpEntity<String> request = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Greet> response = restTemplate.exchange("http://localhost:9090", HttpMethod.GET, request,
				Greet.class);
		Assert.assertTrue(response.getBody().getMessage().startsWith("Hello World"	));
	}

}
