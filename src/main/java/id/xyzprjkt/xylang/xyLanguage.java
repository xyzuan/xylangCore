package id.xyzprjkt.xylang;

import id.xyzprjkt.xylang.context.MemoryContext;
import id.xyzprjkt.xylang.context.definition.DefinitionContext;
import id.xyzprjkt.xylang.statement.CompositeStatement;
import id.xyzprjkt.xylang.token.Token;
import lombok.SneakyThrows;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class xyLanguage {
    @SneakyThrows
    public void execute() {

        boolean exit = false;
        Scanner userInput = new Scanner(System.in);

        do {
            System.out.print("xyLang>> ");
            LexicalParser lexicalParser = new LexicalParser(userInput.nextLine());
            List<Token> tokens = lexicalParser.parse();

            DefinitionContext.pushScope(DefinitionContext.newScope());
            MemoryContext.pushScope(MemoryContext.newScope());
            try {
                CompositeStatement statement = new CompositeStatement();
                StatementParser.parse(tokens, statement);
                statement.execute();
            } finally {

            }
        } while (!exit);
    }

    public void execute(Path params) {
        String sources = params.toString();
        LexicalParser lexicalParser = new LexicalParser(sources);
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

        System.out.println("===========================");
        System.out.println("xyLang rev1.0-dev");
        System.out.println("Developed by xyzuan");
        System.out.println("===========================");

        xyLanguage main = new xyLanguage();
        if (Arrays.stream(args).allMatch(Objects::nonNull)) {
            System.err.println("Realtime mode are under development");
            main.execute();
        } else {
            main.execute(Path.of(args[0]));
        }
    }
}
