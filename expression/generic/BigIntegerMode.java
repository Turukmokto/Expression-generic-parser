package expression.generic;

import expression.exceptions.CalculateException;
import expression.exceptions.IntegerDBZException;

import java.math.BigInteger;

public class BigIntegerMode implements Operations<BigInteger> {
    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) throws IntegerDBZException {
        if (y.equals(BigInteger.ZERO)) {
            throw new IntegerDBZException();
        }

        return x.divide(y);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }


    @Override
    public BigInteger mod(BigInteger x, BigInteger y) throws CalculateException {
        if (y.equals(BigInteger.ZERO)) {
            throw new IntegerDBZException();
        }
        if (y.signum() < 0) {
            throw new CalculateException("modulus not positive");
        }

        return x.mod(y);
    }

    @Override
    public boolean isPartOfNumber(char c) {
        return Character.isDigit(c);
    }

    @Override
    public BigInteger getValue(int s) throws NumberFormatException {
        return BigInteger.valueOf(s);
    }

    @Override
    public BigInteger parseT(String s) throws NumberFormatException {
        return new BigInteger(s);
    }
}