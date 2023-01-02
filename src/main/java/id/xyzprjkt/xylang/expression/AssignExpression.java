package id.xyzprjkt.xylang.expression;

import id.xyzprjkt.xylang.expression.value.Value;

public interface AssignExpression {
    void assign(Value<?> value);
}
