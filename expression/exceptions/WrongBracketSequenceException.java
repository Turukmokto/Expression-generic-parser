package expression.exceptions;

public class WrongBracketSequenceException extends ParseException {
    public WrongBracketSequenceException(String expression, int index) {
        super("Found wrong bracket sequence, problem at pos: " + Integer.toString(index + 1)
                + System.lineSeparator() + printPlace(expression, index, 1));
    }
}