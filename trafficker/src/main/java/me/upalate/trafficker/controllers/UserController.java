package me.upalate.trafficker.controllers;

import me.upalate.trafficker.dto.UpalateUser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserController {

	private AtomicLong counter = new AtomicLong();

	/**
	 * For testing purposes only!
	 * @return a random UpalateUser.
	 */
	@RequestMapping("/users")
	public UpalateUser getUpalateUser() {
		return new UpalateUser("fistname", "lastname", counter.incrementAndGet());
	}
}
