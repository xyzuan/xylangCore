package id.xyzprjkt.xylang.expression.value;

import id.xyzprjkt.xylang.expression.Expression;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Value<T> implements Expression {
    @EqualsAndHashCode.Include
    private final T value;

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Value<?> evaluate() {
        return this;
    }
}
