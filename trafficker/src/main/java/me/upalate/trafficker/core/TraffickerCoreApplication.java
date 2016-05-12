package me.upalate.trafficker.core;

import me.upalate.trafficker.core.common.utils.UpalateLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TraffickerCoreApplication {

    public static void main(String[] args) {
        final UpalateLogger logger = UpalateLogger.getLogger(TraffickerCoreApplication.class);
        try {
            logger.info("Starting TraffickerCoreApplication");
            SpringApplication.run(TraffickerCoreApplication.class, args);
        } catch (RuntimeException e) {
            logger.debug("RuntimeException while running TraffickerCore", e);
        }
    }
}
