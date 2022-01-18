package expression;

import expression.generic.Operations;

public class Variable<T> implements ScalarTripleExpression<T> {
    private final String name;
    protected final Operations<T> operations;

    public Variable(String s, Operations<T> z) {
        name = s;
        operations = z;
    }

    @Override
    public T evaluate(int x, int y, int z) throws NumberFormatException {
        switch (name) {
            case "x":
                return operations.getValue(x);
            case "y":
                return operations.getValue(y);
            case "z":
                return operations.getValue(z);
            default:
                throw new AssertionError();
        }
    }
}