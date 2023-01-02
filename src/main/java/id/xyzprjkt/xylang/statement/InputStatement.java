package id.xyzprjkt.xylang.statement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import id.xyzprjkt.xylang.context.MemoryContext;
import id.xyzprjkt.xylang.expression.value.LogicalValue;
import id.xyzprjkt.xylang.expression.value.NumericValue;
import id.xyzprjkt.xylang.expression.value.TextValue;
import id.xyzprjkt.xylang.expression.value.Value;
import id.xyzprjkt.xylang.token.TokenType;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Getter
public class InputStatement implements Statement {
    private final String name;
    private final Supplier<String> consoleSupplier;

    @Override
    public void execute() {
        System.out.printf("enter \"%s\" >>> ", name.replace("_", " "));
        String line = consoleSupplier.get();

        Value<?> value;
        if (line.matches(TokenType.Numeric.getRegex())) {
            value = new NumericValue(Double.parseDouble(line));
        } else if (line.matches(TokenType.Logical.getRegex())) {
            value = new LogicalValue(Boolean.valueOf(line));
        } else {
            value = new TextValue(line);
        }

        MemoryContext.getScope().set(name, value);
    }
}
