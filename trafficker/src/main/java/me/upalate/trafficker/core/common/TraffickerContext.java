package me.upalate.trafficker.core.common;

/**
 * A high level component may use TraffickerContext
 * to ensure transactional integrity of all
 * lower-level operations it invokes. The high-level
 * component constructs the TraffickerContext object,
 * passes it to the operations, and is then obligated
 * to resolve the TraffickerContext object.
 *
 * Resolution contains the following ordered steps:
 * <li>call commitTransaction() <b>iff</b> no
 * unhandled throwable is present.</li>
 *
 * <li>call finish() .</li>
 *
 * In most cases, it is an error to provide the TraffickerContext
 * to a method after calling finish(), or between the commitTransaction()
 * and finish() calls.
 *
 * A component that accepts a TraffickerContext as input (i.e. does not
 * construct the object itself) must not perform any of the above resolution.
 * TraffickerContext provides a Trans object which the component may mutate.
 * I'm leaving it at that because I don't know how to use Trans or what
 * its relation is to Throwables that can be thrown.
 *
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
}
