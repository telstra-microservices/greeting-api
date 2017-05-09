package au.com.telstra.media.microservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import au.com.telstra.media.microservices.services.GreetingService;

@EnableHystrix
@EnableCircuitBreaker
@RestController
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = { "au.com.telstra.media.microservices" })
public class GreetingController {

	@Autowired
	private GreetingService greetingService;

	// http://localhost:8081/greeting?name=Jerry&circuit-breaker=true
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	String greeting(@RequestParam(value = "name", required = true) String name,
					@RequestParam(value = "circuit-breaker", required = false) Boolean circuitBreaker) {
		return greetingService.getGreeting(name, circuitBreaker);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GreetingController.class, args);
	}

}
