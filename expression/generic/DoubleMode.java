package expression.generic;

import expression.exceptions.CalculateException;

import java.math.BigInteger;

public class DoubleMode implements Operations<Double> {
    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double divide(Double x, Double y) {
        return x / y;
    }

    @Override
    public Double negate(Double x) {
        return -x;
    }

    @Override
    public Double mod(Double x, Double y) throws CalculateException {
        return x % y;
    }

    @Override
    public boolean isPartOfNumber(char c) {
        return Character.isDigit(c) || c == '.';
    }

    @Override
    public Double getValue(int s) throws NumberFormatException {
        return (double) s;
    }

    @Override
    public Double parseT(String s) throws NumberFormatException {
        return Double.parseDouble(s);
    }
}