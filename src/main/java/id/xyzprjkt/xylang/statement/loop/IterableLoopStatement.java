package id.xyzprjkt.xylang.statement.loop;

import id.xyzprjkt.xylang.expression.Expression;
import id.xyzprjkt.xylang.expression.VariableExpression;
import lombok.RequiredArgsConstructor;
import id.xyzprjkt.xylang.context.MemoryContext;
import id.xyzprjkt.xylang.exception.ExecutionException;
import id.xyzprjkt.xylang.expression.value.IterableValue;
import id.xyzprjkt.xylang.expression.value.Value;

import java.util.Iterator;

@RequiredArgsConstructor
public class IterableLoopStatement extends AbstractLoopStatement {
    private final VariableExpression variableExpression;
    private final Expression iterableExpression;

    private Iterator<Value<?>> iterator;

    @Override
    protected void init() {
        Value<?> value = iterableExpression.evaluate();
        if (!(value instanceof IterableValue))
            throw new ExecutionException(String.format("Unable to loop non IterableValue `%s`", value));
        this.iterator = ((IterableValue<?>) value).iterator();
    }

    @Override
    protected boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    protected void preIncrement() {
        MemoryContext.getScope().set(variableExpression.getName(), iterator.next());
    }

    @Override
    protected void postIncrement() {
    }
}
