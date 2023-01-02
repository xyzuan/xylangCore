package id.xyzprjkt.xylang.expression;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import id.xyzprjkt.xylang.context.MemoryContext;
import id.xyzprjkt.xylang.expression.value.Value;

@RequiredArgsConstructor
@Getter
public class VariableExpression implements Expression, AssignExpression {
    private final String name;

    @Override
    public Value<?> evaluate() {
        return MemoryContext.getScope().get(name);
    }

    @Override
    public void assign(Value<?> value) {
        MemoryContext.getScope().set(name, value);
    }
}
