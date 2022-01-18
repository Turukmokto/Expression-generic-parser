package expression;

import expression.exceptions.CalculateException;
import expression.generic.Operations;

public class Mod<T> extends AbstractBinaryOperation<T> {
    public Mod(ScalarTripleExpression<T> x, ScalarTripleExpression<T> y, Operations<T> z) {
        super(x, y, z);
    }

    @Override
    protected T getResult(T x, T y) throws CalculateException {
        return operations.mod(x, y);
    }
}