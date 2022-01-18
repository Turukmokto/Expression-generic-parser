package expression.generic;

import expression.exceptions.IntegerDBZException;

public class LongMode implements Operations<Long> {
    @Override
    public Long add(Long x, Long y) {
        return x + y;
    }

    @Override
    public Long subtract(Long x, Long y) {
        return x - y;
    }

    @Override
    public Long multiply(Long x, Long y) {
        return x * y;
    }

    @Override
    public Long divide(Long x, Long y) throws IntegerDBZException {
        if (y == 0) {
            throw new IntegerDBZException();
        }
        return x / y;
    }

    @Override
    public Long negate(Long x) {
        return -x;
    }

    @Override
    public Long mod(Long x, Long y) throws IntegerDBZException {
        if (y == 0) {
            throw new IntegerDBZException();
        }
        return x % y;
    }

    @Override
    public boolean isPartOfNumber(char c) {
        return Character.isDigit(c) || c == '.';
    }

    @Override
    public Long getValue(int s) throws NumberFormatException {
        return (long) (s);
    }

    @Override
    public Long parseT(String s) throws NumberFormatException {
        return Long.parseLong(s);
    }
}