package id.xyzprjkt.xylang;

import id.xyzprjkt.xylang.context.MemoryContext;
import id.xyzprjkt.xylang.context.definition.DefinitionContext;
import id.xyzprjkt.xylang.statement.CompositeStatement;
import id.xyzprjkt.xylang.token.Token;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
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
            } finally {}
        } while (!exit);
    }

    @SneakyThrows
    public void execute(Path params) {
        String sources = Files.readString(params);
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

    @SneakyThrows
    public static void main(String[] args) {

        System.out.println("===========================");
        System.out.println("xyLang rev1.0-dev");
        System.out.println("Developed by xyzuan");
        System.out.println("===========================");

        xyLanguage main = new xyLanguage();
        if (args.length == 0) {
            System.err.println("Realtime mode are under development");
            main.execute();
        } else {
            int index = args[0].lastIndexOf('.');
            if(args[0].substring(index + 1).equals("xy")) {
                main.execute(Path.of(args[0]));
            } else {
                System.out.println("Only compatible with .xy file");
            }
        }
    }
}
