package id.xyzprjkt.xylang;

import id.xyzprjkt.xylang.token.Token;
import id.xyzprjkt.xylang.token.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LexicalParserTest {

    @Test
    public void testPrint() {
        String source = "ndelok \"Hello World\"";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(2, tokens.size());

        int count = 0;
        Assertions.assertEquals(TokenType.Keyword, tokens.get(count).getType());
        assertEquals("ndelok", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("Hello World", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());
    }

    @Test
    public void testInput() {

        String source = "nginput a";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(2, tokens.size());

        int count = 0;
        assertEquals(TokenType.Keyword, tokens.get(count).getType());
        assertEquals("nginput", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());
    }

    @Test
    public void testAssignment() {

        String source = "a = 2 + 5";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(5, tokens.size());

        int count = 0;
        assertEquals(TokenType.Variable, tokens.get(count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("=", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("2", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("+", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("5", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());
    }

    @Test
    public void testCondition() {

        String source = "nek a > 5\n" +
                        "    ndelok \"a is greater than 5\"\n" +
                        "nekngene a >= 1\n" +
                        "    ndelok \"a is greater than or equal to 1\"\n" +
                        "nekora\n" +
                        "    ndelok \"a is less than 1\"\n" +
                        "uwes";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(22, tokens.size());

        int count = 0;
        assertEquals(TokenType.Keyword, tokens.get(count).getType());
        assertEquals("nek", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals(">", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("5", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("ndelok", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("a is greater than 5", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("nekngene", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals(">=", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("1", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("ndelok", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("a is greater than or equal to 1", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("nekora", tokens.get(count).getValue());
        assertEquals(5, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(5, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("ndelok", tokens.get(count).getValue());
        assertEquals(6, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("a is less than 1", tokens.get(count).getValue());
        assertEquals(6, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(6, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("uwes", tokens.get(count).getValue());
        assertEquals(7, tokens.get(count).getRow());
    }

    @Test
    public void testClass() {

        String source = "class Person [ name, age ]\n" +
                        "uwes\n" +
                        "person = new Person[\"Randy Marsh\", 45]\n" +
                        "ndelok person :: name + \" is \" + person :: age + \" years old\"";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(32, tokens.size());

        int count = 0;
        assertEquals(TokenType.Keyword, tokens.get(count).getType());
        assertEquals("class", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("Person", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals("[", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("name", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals(",", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("age", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals("]", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("uwes", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("person", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("=", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("new", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("Person", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals("[", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("Randy Marsh", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals(",", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("45", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals("]", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("ndelok", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("person", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("::", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("name", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("+", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals(" is ", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("+", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("person", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("::", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("age", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("+", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals(" years old", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());
    }

    @Test
    public void testComment() {
        String source = "# a = 5\n" +
                        "a = 5 # a is equal to 5";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(6, tokens.size());

        int count = 0;
        assertEquals(TokenType.Comment, tokens.get(count).getType());
        assertEquals("# a = 5", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("=", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("5", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Comment, tokens.get(++count).getType());
        assertEquals("# a is equal to 5", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());
    }

}