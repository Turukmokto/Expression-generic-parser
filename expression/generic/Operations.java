package expression.generic;

import expression.exceptions.CalculateException;

public interface Operations<T> {
    T add(T x, T y) throws CalculateException;
    T subtract(T x, T y) throws CalculateException;
    T multiply(T x, T y) throws CalculateException;
    T divide(T x, T y) throws CalculateException;
    T negate(T x) throws CalculateException;
    T mod(T x, T y) throws CalculateException;

    boolean isPartOfNumber(char c);
    T getValue(int s) throws NumberFormatException;
    T parseT(String s) throws NumberFormatException;
}