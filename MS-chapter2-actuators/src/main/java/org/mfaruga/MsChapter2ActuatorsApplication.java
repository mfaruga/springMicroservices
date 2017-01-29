package org.mfaruga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MsChapter2ActuatorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsChapter2ActuatorsApplication.class, args);
	}
}

@RestController
class GreetingController {
	
	@Autowired
	TPSHealth health;
	
	@Autowired
	CounterService counterService;
	
	@Autowired
	GaugeService gaugeService;
	
	
	@RequestMapping("/")
	Greet greet() {
		health.updateTx();
		this.counterService.increment("greet.txnCount");
		this.gaugeService.submit("greet.customgauge", 1.0);
		return new Greet("Hello World!");
	}

}

class Greet {
	private String message;

	public Greet() {
	}

	public Greet(String message) {
		this.setMessage(message);
	}
	// add getter and setter

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
