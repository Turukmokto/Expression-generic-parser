package expression;

import expression.exceptions.CalculateException;
import expression.generic.Operations;

public abstract class AbstractBinaryOperation<T> implements ScalarTripleExpression<T> {
    private final ScalarTripleExpression<T> firstLexeme, secondLexeme;
    protected final Operations<T> operations;

    public AbstractBinaryOperation(ScalarTripleExpression<T> x, ScalarTripleExpression<T> y, Operations<T> z) {
        firstLexeme = x;
        secondLexeme = y;
        operations = z;
    }

    @Override
    public T evaluate(int x, int y, int z) throws CalculateException {
        return getResult(firstLexeme.evaluate(x, y, z), secondLexeme.evaluate(x, y, z));
    }

    protected abstract T getResult(T x, T y) throws CalculateException;
}