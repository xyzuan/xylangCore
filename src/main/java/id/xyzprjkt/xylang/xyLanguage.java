package id.xyzprjkt.xylang;

import id.xyzprjkt.xylang.context.MemoryContext;
import id.xyzprjkt.xylang.context.definition.DefinitionContext;
import id.xyzprjkt.xylang.statement.CompositeStatement;
import id.xyzprjkt.xylang.token.Token;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class xyLanguage {
    @SneakyThrows
    public void execute(Path path) {
        String source = Files.readString(path);
        LexicalParser lexicalParser = new LexicalParser(source);
        List<Token> tokens = lexicalParser.parse();

        DefinitionContext.pushScope(DefinitionContext.newScope());
        MemoryContext.pushScope(MemoryContext.newScope());
        try {
            CompositeStatement statement = new CompositeStatement();
            StatementParser.parse(tokens, statement);
            statement.execute();
        } finally {
            DefinitionContext.endScope();
            MemoryContext.endScope();
        }
    }

    public static void main(String[] args) {
        xyLanguage main = new xyLanguage();

        System.out.println("xylang rev.1.0.beta\n");

        main.execute(Path.of(args[0]));
    }
}
