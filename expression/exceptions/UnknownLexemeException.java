package expression.exceptions;

public class UnknownLexemeException extends ParseException {
    public UnknownLexemeException(String expression, String lexeme, int index) {
        super("Found unknown lexeme '" + lexeme + "' at pos: " + Integer.toString(index + 1)
                + System.lineSeparator() + printPlace(expression, index, lexeme.length()));
    }
}