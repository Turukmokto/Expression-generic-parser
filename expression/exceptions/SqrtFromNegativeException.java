package expression.exceptions;

public class SqrtFromNegativeException extends CalculateException {
    public SqrtFromNegativeException() {
        super("Found attempt to use complex number");
    }
}