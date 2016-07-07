package me.upalate.trafficker.core;

import me.upalate.logging.UpalateLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TraffickerApplication {

	public static UpalateLogger logger = UpalateLogger.getLogger(TraffickerApplication.class);
	public static void main(String[] args) {
        try {
            logger.info("Starting trafficker application.");
            SpringApplication.run(TraffickerApplication.class, args);
        } catch (RuntimeException e) {
            logger.warn("Runtime exception while running Trafficker.", e);
        }

	}
}
