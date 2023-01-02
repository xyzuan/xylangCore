package id.xyzprjkt.xylang.expression.operator;

import id.xyzprjkt.xylang.expression.Expression;
import id.xyzprjkt.xylang.expression.value.ArrayValue;
import id.xyzprjkt.xylang.expression.value.Value;

public class ArrayAppendOperator extends BinaryOperatorExpression {
    public ArrayAppendOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> left = getLeft().evaluate();
        if (left instanceof ArrayValue) {
            Value<?> right = getRight().evaluate();
            ((ArrayValue) left).appendValue(right);
        }
        return getLeft().evaluate();
    }
}
