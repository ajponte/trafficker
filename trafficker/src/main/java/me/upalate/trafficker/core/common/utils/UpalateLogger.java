package me.upalate.trafficker.core.common.utils;

public class UpalateLogger {
   private String logName;
   private boolean debug = false;
   public UpalateLogger(String logName) {
      this.logName = logName;
   }

   public UpalateLogger(String logName, boolean debug) {
      this.logName = logName;
      this.debug = debug;
   }

   public void debug(Exception e) {
      //todo
   }

   public void debug(String msg) {
      //todo
   }

   public void warn(String msg) {
      //todo
   }

   public boolean isDebugEnabled() {
      return debug;
   }
}
