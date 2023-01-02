package id.xyzprjkt.xylang;

import id.xyzprjkt.xylang.context.MemoryContext;
import id.xyzprjkt.xylang.context.definition.DefinitionContext;
import id.xyzprjkt.xylang.statement.CompositeStatement;
import id.xyzprjkt.xylang.token.Token;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class xyLanguage {

    /**
     * ANSI Color Term
     */
    public static final String RESET = "\033[0m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String YELLOW_UNDERLINED = "\033[4;93m";
    public static final String WHITE_BOLD = "\033[1;97m";

    @SneakyThrows
    public void execute() {

        boolean exit = false;
        Scanner userInput = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

        do {
            LocalDateTime now = LocalDateTime.now();
            System.out.printf(GREEN_BOLD + "[%s]" + WHITE_BOLD + " xyLang>> ", dtf.format(now));
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
            System.out.println(YELLOW_UNDERLINED + "Realtime mode are under development" + RESET);
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
