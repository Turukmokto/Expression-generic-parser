package expression.generic;

import expression.exceptions.CalculateException;
import expression.exceptions.IntegerDBZException;
import expression.exceptions.IntegerOverflowException;

import java.math.BigInteger;

public class IntegerMode implements Operations<Integer> {
    private final boolean allowOverflow;

    public IntegerMode(boolean allow) {
        allowOverflow = allow;
    }

    @Override
    public Integer add(Integer x, Integer y) throws IntegerOverflowException {
        if (allowOverflow) {
            // x + y > Integer.MAX_VALUE
            if (x > 0 && y > 0 && y > Integer.MAX_VALUE - x) {
                throw new IntegerOverflowException();
            }

            // x + y < Integer.MIN_VALUE
            if (x < 0 && y < 0 && y < Integer.MIN_VALUE - x) {
                throw new IntegerOverflowException();
            }
        }

        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) throws IntegerOverflowException {
        if (allowOverflow) {
            // x - y > Integer.MAX_VALUE
            if (x >= 0 && y < 0 && x > Integer.MAX_VALUE + y) {
                throw new IntegerOverflowException();
            }

            // x - y < Integer.MIN_VALUE
            if (x < 0 && y >= 0 && x < Integer.MIN_VALUE + y) {
                throw new IntegerOverflowException();
            }
        }

        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) throws IntegerOverflowException {
        if (allowOverflow) {
            // x * y > Integer.MAX_VALUE
            if (x > 0 && y > 0 && y > Integer.MAX_VALUE / x) {
                throw new IntegerOverflowException();
            }

            // x * y < Integer.MIN_VALUE
            if (x > 0 && y < 0 && y < Integer.MIN_VALUE / x) {
                throw new IntegerOverflowException();
            }

            // x * y < Integer.MIN_VALUE
            if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
                throw new IntegerOverflowException();
            }

            // x * y > Integer.MAX_VALUE
            if (x < 0 && y < 0 && y < Integer.MAX_VALUE / x) {
                throw new IntegerOverflowException();
            }
        }

        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) throws CalculateException {
        // divide by zero
        if (y == 0) {
            throw new IntegerDBZException();
        }

        // x / y > Integer.MAX_VALUE
        if (y == -1 && x == Integer.MIN_VALUE && allowOverflow) {
            throw new IntegerOverflowException();
        }

        return x / y;
    }

    @Override
    public Integer negate(Integer x) throws IntegerOverflowException {
        // -x > Integer.MAX_VALUE
        if (x == Integer.MIN_VALUE && allowOverflow) {
            throw new IntegerOverflowException();
        }

        return -x;
    }


    @Override
    public Integer mod(Integer x, Integer y) throws CalculateException {
        if (y == 0) {
            throw new IntegerDBZException();
        }

        return x % y;
    }

    @Override
    public boolean isPartOfNumber(char c) {
        return Character.isDigit(c);
    }

    @Override
    public Integer getValue(int s) throws NumberFormatException {
        return (s);
    }

    @Override
    public Integer parseT(String s) throws NumberFormatException {
        return Integer.parseInt(s);
    }
}