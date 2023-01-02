package id.xyzprjkt.xylang.expression.operator;

import id.xyzprjkt.xylang.expression.Expression;
import id.xyzprjkt.xylang.expression.value.Value;

public class ClassInstanceOperator extends UnaryOperatorExpression {
    public ClassInstanceOperator(Expression value) {
        super(value);
    }

    @Override
    public Value<?> evaluate() {
        return getValue().evaluate(); // will return toString() value
    }
}

