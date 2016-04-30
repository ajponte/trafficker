package me.upalate.trafficker.core.controller;

import me.upalate.trafficker.core.dto.users.User;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	private final String firstName = "";
	private final AtomicLong counter = new AtomicLong();
	@RequestMapping("/user")
	public User user(@RequestParam(value="firstName") String firstName) {
		return new User(counter.incrementAndGet(),
						firstName);
	}
}