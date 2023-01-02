package id.xyzprjkt.xylang.expression.operator;

import id.xyzprjkt.xylang.expression.Expression;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class UnaryOperatorExpression implements OperatorExpression {
    private final Expression value;
}

