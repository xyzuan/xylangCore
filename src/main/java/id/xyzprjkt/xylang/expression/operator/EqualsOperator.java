package id.xyzprjkt.xylang.expression.operator;

import id.xyzprjkt.xylang.expression.Expression;
import id.xyzprjkt.xylang.expression.value.LogicalValue;
import id.xyzprjkt.xylang.expression.value.Value;

import java.util.Objects;

import static id.xyzprjkt.xylang.expression.value.NullValue.NULL_INSTANCE;

public class EqualsOperator extends BinaryOperatorExpression {
    public EqualsOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> left = getLeft().evaluate();
        Value<?> right = getRight().evaluate();
        boolean result;
        if (left == NULL_INSTANCE || right == NULL_INSTANCE) {
            result = left == right;
        } else if (Objects.equals(left.getClass(), right.getClass())) {
            result = left.getValue().equals(right.getValue());
        } else {
            result = left.toString().equals(right.toString());
        }
        return new LogicalValue(result);
    }
}
