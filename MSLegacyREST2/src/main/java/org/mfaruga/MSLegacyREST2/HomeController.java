package org.mfaruga.MSLegacyREST2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public Greet sayHello() {
		return new Greet("Hello world");
	}
	
}
