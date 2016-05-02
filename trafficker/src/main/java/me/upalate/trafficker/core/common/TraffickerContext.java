package me.upalate.trafficker.core.common;

import me.upalate.trafficker.core.common.callback.TraffickerCallback;
import me.upalate.trafficker.core.common.utils.UpalateLogger;

import java.util.List;
import java.util.Stack;

/**
 * <p>
 * A high level component may use TraffickerContext
 * to ensure transactional integrity of all
 * lower-level operations it invokes. The high-level
 * component constructs the TraffickerContext object,
 * passes it to the operations, and is then obligated
 * to resolve the TraffickerContext object.
 * </p>
 *
 * <p>
 * Resolution contains the following ordered steps:
 * <li>call commitTransaction() <b>iff</b> no
 * unhandled throwable is present.</li>
 *
 * <li>call finish() .</li>
 *
 * </p>
 *
 * <p>
 * In most cases, it is an error to provide the TraffickerContext
 * to a method after calling finish(), or between the commitTransaction()
 * and finish() calls.
 * </p>
 *
 * <p>
 * A component that accepts a TraffickerContext as input (i.e. does not
 * construct the object itself) must not perform any of the above resolution.
 * TraffickerContext provides a Trans object which the component may mutate.
 * I'm leaving it at that because I don't know how to use Trans or what
 * its relation is to Throwables that can be thrown.
 * </p>
 *
 * <p>
 * A TraffickerContext can be created in two modes:
 * <li>As a standalone context</li>
 * <li>As a <b>child context</b> fro another TraffickerContext object</li>
 *
 * These child contexts must adhere to the following restrictions:
 * <li>A child context must be created before the parent context is finished.</li>
 * <li>A child context must be closed before its parent context is closed.</li>
 * <li>When using a child context, a developer should be aware of what was
 *     modified by the parent context, to ensure that deadlocks do not occur.</li>
 *<li>A child context should be closed or rolled back before a second child
 *    context is opened for the same parent context.</li>
 *<li>The developer needs to support all of the cases described in TraffickerCallback;
 *    specifically, it is important to handle the case where a child transaction is
 *    committed, but the operation is rolled back.</li>
 *
 * Additionally, the following additional properties apply when child contexts
 * are used:
 * <li>The onOperationCommit() and onOperationRollback() methods of any callbacks
 *     associated with the child context will be executed if (and when)
 *     the parent context is committed or rolled back.  If the parent context
 *     is itself a child, these will instead be executed when that context
 *     is committed or rolled back, and so up the chain. </li>
 *
 * </p>
 * Example:
 * <pre>
 *    Object data;
 *    TraffickerContext context = new TraffickerContext();
 *    try {
 *       performOperation1(context, data);
 *       performOperation2(context, data);
 *       context.commitTransaction();
 *    } catch (OperationException exc) {
 *       log_exception(exc);
 *    } finally {
 *       context.finish();
 *    }
 * </pre>
 *
 * @see me.upalate.trafficker.core.common.callback.TraffickerCallback
 */
public class TraffickerContext {
   private static UpalateLogger logger = new UpalateLogger("TraffickerContext", true);
   private boolean finished = false;
   private boolean committd = false;
   private boolean contructorSucceeded = false;
   private boolean sessionUsed = false;
   private Session session = null;
   private Transaction transaction = null;
   private List<TraffickerCallback> callbacks;
   private Session previousSession = null;
   private List<Throwable> onCommitThrowables = null;
   private StackTraceElement[] constructor_trace;
   private static ThreadLocal<Stack<TraffickerContext>> threadContext = new ThreadLocal<Stack<TraffickerContext>>() {
      @Override
      protected synchronized Stack<TraffickerContext> initialValue() {
         return new Stack<TraffickerContext>();
      }
   };

   /**
    * The pushContext() allows us to track multiple contexts and
    * to log appropriately if there are context issues.  These only apply to parent
    * contexts - child contexts already have an explicit nesting mechanism that
    * is well-defined.
    *
    * Note that we can sometimes get a constructor trace for these objects.
    * A constructor trace is only available if debug logging is turned on for
    * TraffickerContext at the time that a particular context is created.
    * And, since debug logging can be turned on and off at any time, we shouldn't
    * make assumptions about any relationships between the current state of
    * debug logging and the existence of those constructor traces.
    *
    * @param context The context we are pushing.
    */
   private static void pushContext(TraffickerContext context) {
      if (!threadContext.get().isEmpty() &&
           threadContext.get().peek() != null &&
           threadContext.get().peek().isUsed() &&
          !threadContext.get().peek().isFinished()) {
         //todo: logger warn
         if (threadContext.get().peek().constructor_trace != null && context.constructor_trace != null) {
            //todo: logger debug
            String om = "The Enclosing 'Outer' TraffickerContext was created here.";
            Exception outer = threadContext.get().peek().getConstructorTrace(om);
            String im = "The offending 'Inner' TraffickerContext was created here";
            Exception inner = context.getConstructorTrace(im);
         }
      }
      threadContext.get().push(context);
   }

   private static void popContext(TraffickerContext context) {
      if (threadContext.get().peek().equals(context)) {
         threadContext.get().pop();
      } else {
         logger.warn("When finishing TraffickerContext, another, more inner context is " +
                     "present.  Turn on debug logging for this class to see traces of  " +
                     "where all current trafficker contexts were created.");
         for (TraffickerContext tc : threadContext.get()) {
            if (tc.constructor_trace != null) {
               Exception e = tc.getConstructorTrace("Wrong Finish Order.");
            } else {

            }
         }
      }
   }

   private Exception getConstructorTrace(String message) {
      Exception e = new Exception(message);
      e.setStackTrace(constructor_trace);
      return e;
   }

   protected boolean isUsed() {
      return sessionUsed;
   }

   protected boolean isFinished() {
      return finished;
   }
}
