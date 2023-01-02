package id.xyzprjkt.xylang.statement;

import id.xyzprjkt.xylang.expression.Expression;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExpressionStatement implements Statement {
    private final Expression expression;

    @Override
    public void execute() {
        expression.evaluate();
    }
}
