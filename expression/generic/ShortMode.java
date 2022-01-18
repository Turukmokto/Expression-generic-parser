package expression.generic;

import expression.exceptions.IntegerDBZException;

public class ShortMode implements Operations<Short> {
    @Override
    public Short add(Short x, Short y) {
        return (short) (x + y);
    }

    @Override
    public Short subtract(Short x, Short y) {
        return (short) (x - y);
    }

    @Override
    public Short multiply(Short x, Short y) {
        return (short) (x * y);
    }

    @Override
    public Short divide(Short x, Short y) throws IntegerDBZException {
        if (y == 0) {
            throw new IntegerDBZException();
        }

        return (short) (x / y);
    }

    @Override
    public Short negate(Short x) {
        return (short) (-x);
    }

    @Override
    public Short mod(Short x, Short y) throws IntegerDBZException  {
        if (y == 0) {
            throw new IntegerDBZException();
        }

        return (short) (x % y);
    }

    @Override
    public boolean isPartOfNumber(char c) {
        return Character.isDigit(c);
    }

    @Override
    public Short getValue(int s) throws NumberFormatException {
        return (short) (s);
    }

    @Override
    public Short parseT(String s) throws NumberFormatException {
        return Short.parseShort(s);
    }
}