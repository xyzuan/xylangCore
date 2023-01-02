package id.xyzprjkt.xylang.expression.operator;

import id.xyzprjkt.xylang.exception.ExecutionException;
import id.xyzprjkt.xylang.expression.Expression;
import id.xyzprjkt.xylang.expression.value.NumericValue;
import id.xyzprjkt.xylang.expression.value.TextValue;
import id.xyzprjkt.xylang.expression.value.Value;

import static id.xyzprjkt.xylang.expression.value.NullValue.NULL_INSTANCE;

public class MultiplicationOperator extends BinaryOperatorExpression {
    public MultiplicationOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> left = getLeft().evaluate();
        Value<?> right = getRight().evaluate();
        if (left == NULL_INSTANCE || right == NULL_INSTANCE) {
            throw new ExecutionException(String.format("Unable to perform multiplication for NULL values `%s`, '%s'", left, right));
        } else if (left instanceof NumericValue && right instanceof NumericValue) {
            return new NumericValue(((NumericValue) left).getValue() * ((NumericValue) right).getValue());
        } else if (left instanceof NumericValue) {
            return new TextValue(right.toString().repeat(((NumericValue) left).getValue().intValue()));
        } else if (right instanceof NumericValue) {
            return new TextValue(left.toString().repeat(((NumericValue) right).getValue().intValue()));
        } else {
            throw new ExecutionException(String.format("Unable to multiply non numeric values `%s` and `%s`", left, right));
        }
    }
}
