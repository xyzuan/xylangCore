package id.xyzprjkt.xylang.statement;

import id.xyzprjkt.xylang.expression.Expression;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import id.xyzprjkt.xylang.context.ReturnContext;
import id.xyzprjkt.xylang.expression.value.Value;

@RequiredArgsConstructor
@Getter
public class ReturnStatement implements Statement {
    private final Expression expression;

    @Override
    public void execute() {
        Value<?> result = expression.evaluate();
        ReturnContext.getScope().invoke(result);
    }
}
