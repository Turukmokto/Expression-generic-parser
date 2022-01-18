package expression;

public class Const<T> implements ScalarTripleExpression<T> {
    private final T value;

    public Const(T x) {
        value = x;
    }

    @Override
    public T evaluate(int x, int y, int z) {
        return value;
    }
}