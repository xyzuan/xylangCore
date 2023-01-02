package id.xyzprjkt.xylang.expression.operator;

import id.xyzprjkt.xylang.exception.ExecutionException;
import id.xyzprjkt.xylang.expression.Expression;
import id.xyzprjkt.xylang.expression.value.NumericValue;
import id.xyzprjkt.xylang.expression.value.Value;

public class ModuloOperator extends BinaryOperatorExpression {
    public ModuloOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> left = getLeft().evaluate();
        Value<?> right = getRight().evaluate();
        if (left instanceof NumericValue && right instanceof NumericValue) {
            return new NumericValue(((NumericValue) left).getValue() % ((NumericValue) right).getValue());
        } else {
            throw new ExecutionException(String.format("Unable to perform modulo for non numeric values `%s` and `%s`", left, right));
        }
    }
}
