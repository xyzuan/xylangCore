package org.example.toylanguage.statement.loop;

import lombok.RequiredArgsConstructor;
import org.example.toylanguage.context.MemoryContext;
import org.example.toylanguage.exception.ExecutionException;
import org.example.toylanguage.expression.Expression;
import org.example.toylanguage.expression.VariableExpression;
import org.example.toylanguage.expression.value.IterableValue;
import org.example.toylanguage.expression.value.Value;

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
