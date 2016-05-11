package me.upalate.trafficker.core.common.utils;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * TODO: WARN and NOTICE
 */
public class UpalateLogger {
   private static String fullClassName = UpalateLogger.class.getName();
   private static final char START_TAG = '{';
   private static final char END_TAG = '}';
   protected Logger logger;

   /**
    * Internal constructor; use one of the factory methods.
    *
    * @param name The name of the logger.
    * @param additivity True only if this logger inherits from its parent.
    * @param appenders The appenders that should be attached to this logger (and
    *                  its children), if any.
    */
   protected UpalateLogger(String name, boolean additivity, String... appenders) {
      boolean exists = LogManager.getLogger(name) != null;
      logger = Logger.getLogger(name);
      logger.setAdditivity(additivity);
   }

   /**
    * Factory method to get a new UpalateLogger.
    * By default, loggers will be additive and will
    * inherit appenders.
    *
    * @param name The name of the logger
    * @return A new UpalateLogger
    */
   public static UpalateLogger getLogger(String name) {
      return new UpalateLogger(name, true);
   }

   /**
    * Factory method to get a new UpalateLogger.
    * By default, loggers will be additive.
    *
    * @param name The name of logger
    * @param appenders The appenders which should be attached to
    *                  this logger (and its children), if any
    * @return A new UpalateLogger
    */
   public static UpalateLogger getLogger(String name,
                                         String... appenders) {
      return new UpalateLogger(name, true, appenders);
   }

   /**
    * Factory method to get a new UpalateLogger.
    *
    * @param name The name of the logger
    * @param additivity True only if this logger inherits from its parent
    * @param appenders The appenders which should be attached to this
    *                  logger (and its children), if any
    * @return A new UpalateLogger
    */
   public static UpalateLogger getLogger(String name,
                                         boolean additivity,
                                         String... appenders) {
      return new UpalateLogger(name, additivity, appenders);
   }

   /**
    * Factory method to get a new UpalateLogger.
    * By default, loggers will be additive and will inherit appenders.
    *
    * @param clazz The class to use as the name of the logger
    * @return A new UpalateLogger
    */
   public static UpalateLogger getLogger(Class clazz) {
      return getLogger(clazz.getName());
   }

   /**
    * Factory method to get a new UpalateLogger.
    * By default, loggers will be additive
    *
    * @param clazz The class to use as the name of the logger
    * @param appenders The appenders which should be attached to
    *                  this logger (and its children), if any
    * @return A new UpalateLogger
    */
   public static UpalateLogger getLogger(Class clazz,
                                         String... appenders) {
      return getLogger(clazz.getName(), true, appenders);
   }

   /**
    * Factory method to get a new UpalateLogger.
    *
    * @param clazz The class to use to name the logger
    * @param additivity True only if this logger inherits from
    *                   its parent
    * @param appenders The appenders which should be atteched to logger (and
    *                  its children), if any
    * @return The created or retrieved logger
    */
   public static UpalateLogger getLogger(Class clazz,
                                         boolean additivity,
                                         String... appenders) {
      return getLogger(clazz.getName(), additivity, appenders);
   }

   public boolean isDebugEnabled() {
      return logger.isDebugEnabled();
   }

   public boolean isInfoEnabled() {
      return logger.isInfoEnabled();
   }

   public boolean isEnabledFor(Level level) {
      return  logger.isEnabledFor(level);
   }

   public void debug(Object message) {
      this.log(Level.DEBUG, message, null);
   }

   public void debug(Object message, Throwable throwable) {
      this.log(Level.DEBUG, message, throwable);
   }

   public void debugP(String pattern, Object... params) {
      this.logP(Level.DEBUG, pattern, params);
   }

   public void info(Object message) {
      this.log(Level.INFO, message, null);
   }

   public void info(Object message, Throwable throwable) {
      this.log(Level.INFO, message, throwable);
   }

   public void infoP(String pattern, Object... params) {
      this.logP(Level.INFO, pattern, params);
   }

   public void log(Level level,
                   Object message,
                   Throwable throwable) {
      try {
         logger.log(fullClassName, level, message, throwable);
      } catch (Exception e) {
         // Warn and try again
         logger.log(fullClassName,
                    Level.WARN,
                    "Caught Exception during logging. " +
                    "One more attempt will be made " +
                    "To log this message",
                    null);
         logger.log(fullClassName, level, message, throwable);
      }
   }

   /**
    * Logs a formatted message at the appropriate level.
    * "message" is a string containing message description and one or more '{}' entries.
    * Formatting of text takes place only if the logger is enabled for the level.
    *
    * @param level The log level
    * @param message The message to log
    * @param params The optional parameters to append to the message
    */
   private void logP(Level level,
                     String message,
                     Object... params) {
      if (logger.isEnabledFor(level)) {
         StringBuilder sb = new StringBuilder(message.length() + 60);
         formatMessage(sb, message, params);
         logger.log(level, sb.toString());
      }
   }

   void formatMessage(StringBuilder sb, String pattern, Object[] params) {

      int nextParam = 0;
      boolean sawStart = false;

      for (int i = 0; i < pattern.length(); i++) {
         char c = pattern.charAt(i);
         if (sawStart && c == END_TAG) {
            // output message
            if (nextParam < params.length) {
               // message
               Object paramObj = params[nextParam++];
               if (paramObj == null) {
                  sb.append("<null>");
               }
               else {
                  sb.append(paramObj.toString());
               }
            }
            else {
               sb.append("<undefined param>");
            }

            // update state
            sawStart = false;
         }
         else {
            // if the last character was an open brace, output it now because we didn't then.
            if (sawStart) {
               sb.append(START_TAG);
            }

            if (c == START_TAG) {
               // this is a start too
               sawStart = true;
            }
            else {
               // this is not a start, just output it.
               sawStart = false;
               sb.append(c);
            }
         }
      }

      // if the last character was an open brace, that needs to be output too.
      if (sawStart) {
         sb.append(START_TAG);
      }
   }
}
