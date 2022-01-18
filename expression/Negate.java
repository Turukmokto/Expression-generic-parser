package expression;

import expression.exceptions.CalculateException;
import expression.generic.Operations;

public class Negate<T> extends AbstractUnaryOperation<T> {
    public Negate(ScalarTripleExpression<T> x, Operations<T> y) {
        super(x, y);
    }

    @Override
    protected T getResult(T x) throws CalculateException {
        return operations.negate(x);
    }
}