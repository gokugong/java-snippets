package misc;

import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackTest {
  private static final Logger logger = LoggerFactory.getLogger(StackTest.class);
  public static void main(String[] args) {
    Stack stack = new Stack();
    stack.push(new Integer(1));
    stack.push("string");
    while (stack.size() > 0)
      logger.debug("Stack element: {}", stack.pop());
  }
}
