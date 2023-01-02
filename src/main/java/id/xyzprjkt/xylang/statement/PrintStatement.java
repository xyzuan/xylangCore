package id.xyzprjkt.xylang.statement;

import id.xyzprjkt.xylang.expression.Expression;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import id.xyzprjkt.xylang.expression.value.Value;

@RequiredArgsConstructor
@Getter
public class PrintStatement implements Statement {
    private final Expression expression;

    @Override
    public void execute() {
        Value<?> value = expression.evaluate();
        System.out.println(value);
    }
}
