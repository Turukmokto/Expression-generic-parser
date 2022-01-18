package expression.exceptions;

public class OperandNotFoundException extends ParseException {
    public OperandNotFoundException(String expression, String operation, int index) {
        super(operation + " found, but operand not found at pos: " + Integer.toString(index + 1)
                + System.lineSeparator() + printPlace(expression, index, 1));
    }
}