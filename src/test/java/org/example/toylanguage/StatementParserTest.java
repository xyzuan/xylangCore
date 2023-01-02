package org.example.toylanguage;

import org.example.toylanguage.definition.StructureDefinition;
import org.example.toylanguage.expression.StructureExpression;
import org.example.toylanguage.expression.VariableExpression;
import org.example.toylanguage.expression.operator.AdditionOperator;
import org.example.toylanguage.expression.operator.GreaterThanOperator;
import org.example.toylanguage.expression.operator.StructureInstanceOperator;
import org.example.toylanguage.expression.value.NumericValue;
import org.example.toylanguage.expression.value.TextValue;
import org.example.toylanguage.statement.*;
import org.example.toylanguage.token.Token;
import org.example.toylanguage.token.TokenType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatementParserTest {

    @Test
    public void printTest() {
        List<Token> tokens = Arrays.asList(
                Token.builder().type(TokenType.Keyword).value("print").build(),
                Token.builder().type(TokenType.Text).value("Hello World").build()
        );
        StatementParser parser = new StatementParser(tokens);
        CompositeStatement statement = (CompositeStatement) parser.parse();

        List<Statement> statements = statement.getStatements2Execute();
        assertEquals(1, statements.size());

        assertEquals(PrintStatement.class, statements.get(0).getClass());
        PrintStatement printStatement = (PrintStatement) statements.get(0);

        assertEquals(TextValue.class, printStatement.getExpression().getClass());
        TextValue textValue = (TextValue) printStatement.getExpression();

        assertEquals("Hello World", textValue.getValue());
    }

    @Test
    public void testInput() {

        List<Token> tokens = Arrays.asList(
                Token.builder().type(TokenType.Keyword).value("input").build(),
                Token.builder().type(TokenType.Variable).value("a").build()
        );
        StatementParser parser = new StatementParser(tokens);
        CompositeStatement statement = (CompositeStatement) parser.parse();

        List<Statement> statements = statement.getStatements2Execute();
        assertEquals(1, statements.size());

        assertEquals(InputStatement.class, statements.get(0).getClass());
        InputStatement inputStatement = (InputStatement) statements.get(0);

        assertEquals("a", inputStatement.getName());
    }

    @Test
    public void testAssignment() {

        List<Token> tokens = Arrays.asList(
                Token.builder().type(TokenType.Variable).value("a").build(),
                Token.builder().type(TokenType.Operator).value("=").build(),
                Token.builder().type(TokenType.Numeric).value("2").build(),
                Token.builder().type(TokenType.Operator).value("+").build(),
                Token.builder().type(TokenType.Numeric).value("5").build()
        );
        StatementParser parser = new StatementParser(tokens);
        CompositeStatement statement = (CompositeStatement) parser.parse();

        List<Statement> statements = statement.getStatements2Execute();
        assertEquals(1, statements.size());

        assertEquals(AssignStatement.class, statements.get(0).getClass());
        AssignStatement assignmentStatement = (AssignStatement) statements.get(0);
        assertEquals("a", assignmentStatement.getName());

        assertEquals(AdditionOperator.class, assignmentStatement.getExpression().getClass());
        AdditionOperator operator = (AdditionOperator) assignmentStatement.getExpression();

        assertEquals(NumericValue.class, operator.getLeft().getClass());
        NumericValue left = (NumericValue) operator.getLeft();
        assertEquals(2, left.getValue());

        assertEquals(NumericValue.class, operator.getRight().getClass());
        NumericValue right = (NumericValue) operator.getRight();
        assertEquals(5, right.getValue());
    }

    @Test
    public void testCondition() {

        List<Token> tokens = Arrays.asList(
                Token.builder().type(TokenType.Keyword).value("if").build(),
                Token.builder().type(TokenType.Variable).value("a").build(),
                Token.builder().type(TokenType.Operator).value(">").build(),
                Token.builder().type(TokenType.Numeric).value("1").build(),
                Token.builder().type(TokenType.Keyword).value("then").build(),
                Token.builder().type(TokenType.Keyword).value("print").build(),
                Token.builder().type(TokenType.Text).value("a is greater than 1").build(),
                Token.builder().type(TokenType.Keyword).value("end").build()
        );
        StatementParser parser = new StatementParser(tokens);
        CompositeStatement statement = (CompositeStatement) parser.parse();

        List<Statement> statements = statement.getStatements2Execute();
        assertEquals(1, statements.size());

        assertEquals(ConditionStatement.class, statements.get(0).getClass());
        ConditionStatement conditionStatement = (ConditionStatement) statements.get(0);

        assertEquals(GreaterThanOperator.class, conditionStatement.getCondition().getClass());
        GreaterThanOperator condition = (GreaterThanOperator) conditionStatement.getCondition();

        assertEquals(VariableExpression.class, condition.getLeft().getClass());
        VariableExpression left = (VariableExpression) condition.getLeft();
        assertEquals("a", left.getName());

        NumericValue right = (NumericValue) condition.getRight();
        assertEquals(1, right.getValue());

        List<Statement> conditionStatements = conditionStatement.getStatements2Execute();
        assertEquals(1, conditionStatements.size());

        assertEquals(PrintStatement.class, conditionStatements.get(0).getClass());
        PrintStatement printStatement = (PrintStatement) conditionStatements.get(0);

        assertEquals(TextValue.class, printStatement.getExpression().getClass());
        TextValue printValue = (TextValue) printStatement.getExpression();
        assertEquals("a is greater than 1", printValue.getValue());
    }

    @Test
    public void testObject() {
        List<Token> tokens = Arrays.asList(
                Token.builder().type(TokenType.Keyword).value("struct").row(1).build(),
                Token.builder().type(TokenType.Variable).value("Person").row(1).build(),
                Token.builder().type(TokenType.GroupDivider).value("[").row(1).build(),
                Token.builder().type(TokenType.Variable).value("name").row(1).build(),
                Token.builder().type(TokenType.GroupDivider).value(",").row(1).build(),
                Token.builder().type(TokenType.Variable).value("age").row(1).build(),
                Token.builder().type(TokenType.GroupDivider).value("]").row(1).build(),
                Token.builder().type(TokenType.LineBreak).value("\n").row(1).build(),
                Token.builder().type(TokenType.Variable).value("person").row(2).build(),
                Token.builder().type(TokenType.Operator).value("=").row(2).build(),
                Token.builder().type(TokenType.Operator).value("new").row(2).build(),
                Token.builder().type(TokenType.Variable).value("Person").row(2).build(),
                Token.builder().type(TokenType.GroupDivider).value("[").row(2).build(),
                Token.builder().type(TokenType.Text).value("Robert").row(2).build(),
                Token.builder().type(TokenType.GroupDivider).value(",").row(2).build(),
                Token.builder().type(TokenType.Numeric).value("25").row(2).build(),
                Token.builder().type(TokenType.GroupDivider).value("]").row(2).build(),
                Token.builder().type(TokenType.LineBreak).value("\n").row(2).build(),
                Token.builder().type(TokenType.Keyword).value("print").row(3).build(),
                Token.builder().type(TokenType.Variable).value("person").row(3).build(),
                Token.builder().type(TokenType.Operator).value("::").row(3).build(),
                Token.builder().type(TokenType.Variable).value("name").row(3).build(),
                Token.builder().type(TokenType.Operator).value("+").row(3).build(),
                Token.builder().type(TokenType.Text).value(" is ").row(3).build(),
                Token.builder().type(TokenType.Operator).value("+").row(3).build(),
                Token.builder().type(TokenType.Variable).value("person").row(3).build(),
                Token.builder().type(TokenType.Operator).value("::").row(3).build(),
                Token.builder().type(TokenType.Variable).value("age").row(3).build(),
                Token.builder().type(TokenType.Operator).value("+").row(3).build(),
                Token.builder().type(TokenType.Text).value(" years old").row(3).build()
        );
        StatementParser parser = new StatementParser(tokens);
        CompositeStatement statement = (CompositeStatement) parser.parse();

        List<Statement> statements = statement.getStatements2Execute();
        assertEquals(2, statements.size());

        // 1st statement
        assertEquals(AssignStatement.class, statements.get(0).getClass());
        AssignStatement assignmentStatement = (AssignStatement) statements.get(0);
        assertEquals("person", assignmentStatement.getName());

        assertEquals(StructureInstanceOperator.class, assignmentStatement.getExpression().getClass());
        StructureInstanceOperator instanceOperator = (StructureInstanceOperator) assignmentStatement.getExpression();

        assertEquals(StructureExpression.class, instanceOperator.getValue().getClass());
        StructureExpression structure = (StructureExpression) instanceOperator.getValue();
        StructureDefinition definition = structure.getDefinition();

        assertEquals("Person", definition.getName());
        assertEquals(Arrays.asList("name", "age"), definition.getArguments());

        assertEquals("Robert", structure.getArgumentValue("name").getValue());
        assertEquals("25", structure.getArgumentValue("age").toString());

        // 2nd statement
        PrintStatement printStatement = (PrintStatement) statements.get(1);
        assertEquals(AdditionOperator.class, printStatement.getExpression().getClass());
    }

    @Test
    public void testComment() {
        List<Token> tokens = Arrays.asList(
                Token.builder().type(TokenType.Comment).value("# a = 5").build(),
                Token.builder().type(TokenType.LineBreak).value("\n").build(),
                Token.builder().type(TokenType.Variable).value("a").build(),
                Token.builder().type(TokenType.Operator).value("=").build(),
                Token.builder().type(TokenType.Numeric).value("5").build(),
                Token.builder().type(TokenType.Comment).value("# a is equal to 5").build()
        );
        StatementParser parser = new StatementParser(tokens);
        CompositeStatement statement = (CompositeStatement) parser.parse();

        List<Statement> statements = statement.getStatements2Execute();
        assertEquals(1, statements.size());

        assertEquals(AssignStatement.class, statements.get(0).getClass());
        AssignStatement assignStatement = (AssignStatement) statements.get(0);

        assertEquals("a", assignStatement.getName());
        assertEquals(NumericValue.class, assignStatement.getExpression().getClass());
        NumericValue numericValue = (NumericValue) assignStatement.getExpression();

        assertEquals(5, numericValue.getValue());
    }

}