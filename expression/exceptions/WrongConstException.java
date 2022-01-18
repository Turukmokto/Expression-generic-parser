package expression.exceptions;

public class WrongConstException extends ParseException {
    public WrongConstException(String expression, String number, int index) {
        super("Const '" + number + "' is too big for int at pos: " + Integer.toString(index + 1)
                + System.lineSeparator() + printPlace(expression, index, number.length()));
    }
}