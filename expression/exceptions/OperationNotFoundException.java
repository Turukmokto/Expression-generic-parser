package expression.exceptions;

public class OperationNotFoundException extends ParseException {
    public OperationNotFoundException(String expression, String operator, int index) {
        super("'" + operator + "' found, but operation not found at pos: " +
                Integer.toString(index + 1) + System.lineSeparator() + printPlace(expression, index, 1));
    }
}