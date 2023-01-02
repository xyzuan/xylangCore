package id.xyzprjkt.xylang.expression;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import id.xyzprjkt.xylang.expression.value.ArrayValue;
import id.xyzprjkt.xylang.expression.value.Value;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ArrayExpression implements Expression {
    private final List<Expression> values;

    @Override
    public Value<?> evaluate() {
        return new ArrayValue(this);
    }
}
