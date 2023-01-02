package id.xyzprjkt.xylang.context;

import id.xyzprjkt.xylang.expression.value.ClassValue;
import id.xyzprjkt.xylang.expression.value.ThisValue;

import java.util.Stack;

/**
 * Holds class instances at the current point of execution
 *
 * @see ThisValue#getValue()
 * @see ThisValue#getValue()
 */
public class ClassInstanceContext {
    private static final Stack<ClassValue> values = new Stack<>();

    public static ClassValue getValue() {
        return values.peek();
    }

    public static void pushValue(ClassValue instance) {
        values.push(instance);
    }

    public static void popValue() {
        values.pop();
    }
}
