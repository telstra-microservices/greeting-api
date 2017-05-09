package au.com.telstra.media.microservices.services;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class GreetingService {

	@HystrixCommand(fallbackMethod = "getDefaultGreeting")
	public String getGreeting(String name, Boolean circuitBreaker) {
		if(circuitBreaker != null && circuitBreaker) {
			Integer.parseInt("na");
		}
		return "Hello " + name + ", welcome to the jungle...";
	}

	@SuppressWarnings("unused")
	private String getDefaultGreeting(String name, Boolean fallback) {
		return "Hello user? ... ";
	}

}
