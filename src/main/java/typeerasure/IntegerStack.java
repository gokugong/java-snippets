/**
 * @see <a herf="https://www.baeldung.com/java-type-erasure">Type Erasure</a>
 */
package typeerasure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;


public class IntegerStack extends Stack<Integer>
{
    private static final Logger logger = LoggerFactory.getLogger(IntegerStack.class);

    public Integer push(Integer value)
    {
        logger.debug("push to IntegerStack {}", value);
        return super.push(value);
    }

    public static void main(String[] args)
    {
        Stack stackDummy = new Stack();
        stackDummy.push(new Integer(1));
        stackDummy.push("string");
        logger.debug("stack lat element: {}", stackDummy.pop());
        Stack stack = new IntegerStack();
        // Got Exception: java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
        stack.push("string");
        Integer i = ((IntegerStack) stack).pop();
        logger.debug("stack pop: {}", i);
    }
}