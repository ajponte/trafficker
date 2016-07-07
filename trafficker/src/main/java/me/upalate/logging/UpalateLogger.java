package me.upalate.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This object can be used to log operations.  Contains
 * functionality to log INFO, DEBUG, WARN messages.
 */
public class UpalateLogger {
    protected Logger logger;

    /**
     * A new logger.
     * @param logName
     */
    public UpalateLogger (String logName) {
        logger = LoggerFactory.getLogger(logName);
    }

    /**
     * A new logger.
     * @param clazz The class whose we are logging.
     */
    public UpalateLogger (Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    /**
     * Factory method to create a new logger.
     *
     * @param logName
     * @return A new UpalateLogger
     */
    public static UpalateLogger getLogger(String logName) {
        return new UpalateLogger(logName);
    }

    /**
     * Factory method to create a new logger.
     *
     * @param clazz
     * @return A new UpalateLogger
     */
    public static UpalateLogger getLogger(Class<?> clazz) {
        return new UpalateLogger(clazz);
    }

    /**
     * Logs an info message.
     *
     * @param message The message to log
     */
    public void info(String message) {
        logger.info(message);
    }

    /**
     * Logs a debug message.
     *
     * @param message The message to log
     */
    public void debug(String message) {
        logger.debug(message);
    }

    /**
     * Logs an info message as well as a throwable.
     *
     * @param message The message to log
     * @param throwable The throwable to log
     */
    public void info(String message, Throwable throwable) {
        logger.debug(message, throwable);
    }

    /**
     * Logs a debug message as well as a throwable.
     *
     * @param mesage The message to log
     * @param throwable The throwable to log
     */
    public void debug(String mesage, Throwable throwable) {
        logger.debug(mesage, throwable);
    }

    /**
     * Logs a warn message.
     *
     * @param message The message to log
     */
    public void warn(String message) {
        logger.warn(message);
    }

    /**
     * Logs a warn message as well as a throwable.
     *
     * @param message The message to log
     * @param throwable The throwable to log
     */
    public void warn(String message, Throwable throwable) {
        logger.warn(message, throwable);
    }

}
