package expression;

import expression.exceptions.CalculateException;
import expression.generic.Operations;

public class Add<T> extends AbstractBinaryOperation<T> {
    public Add(ScalarTripleExpression<T> x, ScalarTripleExpression<T> y, Operations<T> z) {
        super(x, y, z);
    }

    @Override
    protected T getResult(T x, T y) throws CalculateException {
        return operations.add(x, y);
    }
}