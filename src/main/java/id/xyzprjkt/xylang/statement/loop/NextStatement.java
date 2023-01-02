package id.xyzprjkt.xylang.statement.loop;

import id.xyzprjkt.xylang.context.NextContext;
import id.xyzprjkt.xylang.statement.Statement;

public class NextStatement implements Statement {
    @Override
    public void execute() {
        NextContext.getScope().invoke();
    }
}
