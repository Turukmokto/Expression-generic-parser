package expression;

import expression.exceptions.CalculateException;
import expression.generic.Operations;

public abstract class AbstractUnaryOperation<T> implements ScalarTripleExpression<T> {
    private final ScalarTripleExpression<T> lexeme;
    protected final Operations<T> operations;

    public AbstractUnaryOperation(ScalarTripleExpression<T> x, Operations<T> y) {
        lexeme = x;
        operations = y;
    }

    public T evaluate(int x, int y, int z) throws CalculateException {
        return getResult(lexeme.evaluate(x, y, z));
    }

    protected abstract T getResult(T x) throws CalculateException;
}