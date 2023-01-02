package id.xyzprjkt.xylang.expression.operator;

import id.xyzprjkt.xylang.expression.AssignExpression;
import id.xyzprjkt.xylang.expression.Expression;
import id.xyzprjkt.xylang.expression.value.Value;

public class AssignmentOperator extends BinaryOperatorExpression {
    public AssignmentOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        if (getLeft() instanceof AssignExpression) {
            Value<?> right = getRight().evaluate();
            ((AssignExpression) getLeft()).assign(right);
            return getLeft().evaluate();
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
