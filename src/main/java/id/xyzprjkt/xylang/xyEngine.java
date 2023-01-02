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

public class xyEngine {

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

        int index = params.toString().lastIndexOf('.');
        if(params.toString().substring(index + 1).equals("xy")) {
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
        } else {
            System.out.println("Only compatible with .xy file");
        }
    }

    @SneakyThrows
    public static void main(String[] args) {

        xyLoader loader = new xyLoader();

        for (int i = 0; i < 100; i++) {
            loader.animate(i + "");
            //simulate a piece of task
            Thread.sleep(10);
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("===========================");
        System.out.println("xyLang rev1.0-dev");
        System.out.println("Developed by xyzuan");
        System.out.println("===========================");

        xyEngine main = new xyEngine();
        if (args.length == 0) {
            System.out.println(YELLOW_UNDERLINED + "Realtime mode are under development" + RESET);
            main.execute();
        } else {
            main.execute(Path.of(args[0]));
        }
    }
}
