package me.upalate.trafficker.core.common.callback;

/**
 * Allows a class to define operations that will be
 * performed at various points in the lifecycle of
 * this Trafficker operation.  Possible call
 * sequences are:
 *
 * 1. onCommit() is called, then onOperationCommit() is called.
 *    - This is the standard sequence of event for a single-context
 *       and a nested-context scenario.
 * 2. onRollback() is called, then onOperationRollback() is called.
 * 3. onCommit() is called, then onOperationRollback is called.
 */
public interface TraffickerCallback {
   /**
    * Executed when (and if) the transaction containing this callback
    * is committed.
    *
    * @throws Exception if an issue occurs - these will be caught and logged.
    */
   public void onCommit() throws Exception;

   /**
    * Executed if the transaction containing this callback is rolled back.
    *
    * @throws Exception if an issue occurs - these will be caught and logged.
    */
   public void onRollback() throws Exception;

   /**
    *
    * @throws Exception if an issue occurs - these will be caught and logged.
    */
   public void onOperationCommit() throws Exception;

   /**
    *
    * @throws Exception if an issue occurs - these will be caught and logged.
    */
   public void onOperatioRollback() throws Exception;
}
