package expression.exceptions;

public class IntegerDBZException extends CalculateException {
    public IntegerDBZException() {
        super("divide by zero");
    }
}