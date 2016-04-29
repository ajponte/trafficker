package me.upalate.trafficker.hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle Greeting resources.
 */
@RestController
public class GreetingController {
	private final String template = "Hello, %s!";
   private final AtomicLong counter = new AtomicLong();
   
	@RequestMapping("/greeting")
	/**
    * Accepts a String in terms of the HTTP paramater, NAME, 
	 * fromats the NAME to the template.
    */
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
		return new Greeting(counter.incrementAndGet(),
									String.format(template, name));
	}
}
