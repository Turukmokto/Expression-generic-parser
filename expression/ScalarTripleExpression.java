package expression;

import expression.exceptions.CalculateException;

public interface ScalarTripleExpression<T> {
    T evaluate(int x, int y, int z) throws CalculateException;
}