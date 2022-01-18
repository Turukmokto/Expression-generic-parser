package expression.exceptions;

public class IntegerOverflowException extends CalculateException {
    public IntegerOverflowException() {
        super("overflow");
    }
}