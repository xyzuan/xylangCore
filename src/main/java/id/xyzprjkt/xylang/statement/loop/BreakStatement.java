package id.xyzprjkt.xylang.statement.loop;

import id.xyzprjkt.xylang.context.BreakContext;
import id.xyzprjkt.xylang.statement.Statement;

public class BreakStatement implements Statement {
    @Override
    public void execute() {
        BreakContext.getScope().invoke();
    }
}
