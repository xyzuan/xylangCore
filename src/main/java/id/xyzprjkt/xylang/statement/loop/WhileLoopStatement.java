package id.xyzprjkt.xylang.statement.loop;

import id.xyzprjkt.xylang.expression.Expression;
import lombok.RequiredArgsConstructor;
import id.xyzprjkt.xylang.expression.value.LogicalValue;
import id.xyzprjkt.xylang.expression.value.Value;

@RequiredArgsConstructor
public class WhileLoopStatement extends AbstractLoopStatement {
    private final Expression hasNext;

    @Override
    protected void init() {
    }

    @Override
    protected boolean hasNext() {
        Value<?> value = hasNext.evaluate();
        return value instanceof LogicalValue && ((LogicalValue) value).getValue();
    }

    @Override
    protected void preIncrement() {
    }

    @Override
    protected void postIncrement() {
    }
}
