package org.mfaruga;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsBootFirstApplicationTests {

	@Test
	public void contextLoads() {
		RestTemplate restTemplate = new RestTemplate();
		Greet greet = restTemplate.getForObject("http://localhost:8080", Greet.class);
		Assert.assertNotEquals("Different message!", greet.getMessage());
	}

	@Test
	public void testVanillaService() {
		RestTemplate restTemplate = new RestTemplate();
		Greet greet = restTemplate.getForObject("http://localhost:8080", Greet.class);
		Assert	.assertEquals("Hello World!", greet.getMessage());
	}
	
}
